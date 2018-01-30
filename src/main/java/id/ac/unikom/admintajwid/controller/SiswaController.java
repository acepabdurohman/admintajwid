package id.ac.unikom.admintajwid.controller;

import id.ac.unikom.admintajwid.dto.SiswaLogin;
import id.ac.unikom.admintajwid.dto.SiswaScore;
import id.ac.unikom.admintajwid.exception.DataNotFoundException;
import id.ac.unikom.admintajwid.model.Kelas;
import id.ac.unikom.admintajwid.model.Siswa;
import id.ac.unikom.admintajwid.model.SiswaKelas;
import id.ac.unikom.admintajwid.response.ResponseTemplate;
import id.ac.unikom.admintajwid.service.KelasService;
import id.ac.unikom.admintajwid.service.QuizService;
import id.ac.unikom.admintajwid.service.SiswaKelasService;
import id.ac.unikom.admintajwid.service.SiswaService;
import id.ac.unikom.admintajwid.util.MenuDropdownList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@Controller
public class SiswaController {

    @Autowired
    private SiswaService siswaService;
    @Autowired
    private KelasService kelasService;
    @Autowired
    private SiswaKelasService siswaKelasService;
    @Autowired
    private MenuDropdownList menuDropdownList;
    @Autowired
    private QuizService quizService;

    private Kelas kelas;

    @GetMapping("/admin/siswa/kelas")
    public ModelAndView getAll(@RequestParam("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        kelas = kelasService.findById(id);
        List<SiswaKelas> siswaKelases = siswaKelasService.findByIdKelas(kelas.getId());
        modelAndView.addObject("siswaKelases", siswaKelases);
        modelAndView.addObject("kls", kelas);
        modelAndView.addObject("kelases", menuDropdownList.findAllKelas());
        modelAndView.addObject("materis", menuDropdownList.findAllMateri());
        modelAndView.setViewName("/siswa/list");
        return modelAndView;
    }

    @GetMapping("/admin/siswa/create/kelas")
    public ModelAndView create(@RequestParam("id") Integer idKelas) {
        ModelAndView modelAndView = new ModelAndView();
        kelas = kelasService.findById(idKelas);
        Siswa siswa = new Siswa();
        modelAndView.addObject("siswa", siswa);
        modelAndView.addObject("currentKelas", kelas);
        modelAndView.addObject("kelases", menuDropdownList.findAllKelas());
        modelAndView.addObject("materis", menuDropdownList.findAllMateri());
        modelAndView.setViewName("/siswa/create");
        return modelAndView;
    }

    @PostMapping("/admin/siswa/save")
    public String save(@ModelAttribute("kelas") @Valid Siswa siswa, RedirectAttributes attributes) {
        siswaService.save(siswa, kelas);
        attributes.addFlashAttribute("saveResult", "success");
        return "redirect:/admin/siswa/kelas?id=" + kelas.getId();
    }

    @PostMapping("/admin/siswa/kelas/save")
    public String save(@ModelAttribute("siswaKelas") @Valid SiswaKelas siswaKelas, RedirectAttributes attributes) {
        System.out.println(siswaKelas);
        siswaKelasService.save(siswaKelas);
        attributes.addFlashAttribute("saveResult", "success");
        return "redirect:/admin/siswa/kelas?id=" + siswaKelas.getKelas().getId();
    }

    @GetMapping("/admin/siswa/kelas/{action}")
    public String findById(@PathVariable("action") String action, @RequestParam("idSiswaKelas") Integer idSiswaKelas, ModelMap modelMap,
                                 RedirectAttributes attributes) {
        if (action.equals("edit")) {
            SiswaKelas siswaKelas = siswaKelasService.findById(idSiswaKelas);
            modelMap.addAttribute("siswaKelas", siswaKelas);
            modelMap.addAttribute("kelasList", kelasService.findAll());
            modelMap.addAttribute("kelases", menuDropdownList.findAllKelas());
            modelMap.addAttribute("materis", menuDropdownList.findAllMateri());
            return "/siswa/edit";
        } else {
            SiswaKelas siswaKelas = siswaKelasService.findById(idSiswaKelas);
            Siswa siswa = siswaKelas.getSiswa();
            siswaService.delete(siswa);
            attributes.addFlashAttribute("deleteResult", "success");
            return "redirect:/admin/siswa/kelas?id=" + kelas.getId();
        }
    }

    @PostMapping(value = "/siswa/api/authentication", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseTemplate getLoginSiswaKelas(@RequestBody SiswaLogin siswaLogin) throws DataNotFoundException{
        Siswa siswa = siswaService.findByUsernameAndPassword(siswaLogin.getUsername(), siswaLogin.getPassword());
        if(siswa == null){
            throw new DataNotFoundException();
        }
        return new ResponseTemplate(200, "Success", siswa);
    }

    @PostMapping(value = "/siswa/api/score")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseTemplate saveScore(@RequestBody SiswaScore siswaScore) throws ParseException{
        quizService.save(siswaScore);
        return new ResponseTemplate(200, "Success", null);
    }
}

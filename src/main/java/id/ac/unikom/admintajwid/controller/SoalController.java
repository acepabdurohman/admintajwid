package id.ac.unikom.admintajwid.controller;

import id.ac.unikom.admintajwid.dto.MateriList;
import id.ac.unikom.admintajwid.exception.DataNotFoundException;
import id.ac.unikom.admintajwid.model.Materi;
import id.ac.unikom.admintajwid.model.Soal;
import id.ac.unikom.admintajwid.response.ResponseTemplate;
import id.ac.unikom.admintajwid.service.KelasService;
import id.ac.unikom.admintajwid.service.MateriService;
import id.ac.unikom.admintajwid.service.SoalService;
import id.ac.unikom.admintajwid.util.MenuDropdownList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SoalController {
    @Autowired
    private SoalService soalService;
    @Autowired
    private KelasService kelasService;
    @Autowired
    private MateriService materiService;
    @Autowired
    private MenuDropdownList menuDropdownList;

    private Materi materi;

    @GetMapping(value = "/admin/soal")
    public ModelAndView getAllSoal(@RequestParam("idMateri") Integer idMateri) {
        ModelAndView modelAndView = new ModelAndView();
        List<Soal> soals = soalService.findByIdMateri(idMateri);
        materi = materiService.findById(idMateri);
        modelAndView.addObject("soals", soals);
        modelAndView.addObject("mtr", materi);
        modelAndView.addObject("materis", menuDropdownList.findAllMateri());
        modelAndView.addObject("kelases", menuDropdownList.findAllKelas());
        modelAndView.setViewName("soal/list");
        return modelAndView;
    }

    @GetMapping(value = "/admin/soal/create")
    public ModelAndView create(@RequestParam("idMateri") Integer idMateri) {
        materi = materiService.findById(idMateri);
        ModelAndView modelAndView = new ModelAndView();
        Soal soal = new Soal();
        modelAndView.addObject("mtr", materi);
        modelAndView.addObject("soal", soal);
        modelAndView.addObject("materis", menuDropdownList.findAllMateri());
        modelAndView.addObject("kelases", menuDropdownList.findAllKelas());
        modelAndView.setViewName("/soal/create");
        return modelAndView;
    }

    @GetMapping(value = "/admin/soal/{action}/{id}")
    public String findByIdSoal(@PathVariable("action") String action, @PathVariable("id") Integer id,
                               RedirectAttributes redirectAttributes, Model model) {
        Soal soal = soalService.findById(id);
        if (action.equals("edit")) {
            materi = materiService.findById(soal.getMateri().getId());
            model.addAttribute("soal", soal);
            model.addAttribute("materis", menuDropdownList.findAllMateri());
            model.addAttribute("kelases", menuDropdownList.findAllKelas());
            return "/soal/edit";
        } else if (action.equals("delete")) {
            soalService.delete(id);
            redirectAttributes.addFlashAttribute("deleteResult", "success");
            return "redirect:/admin/soal?idMateri=" + materi.getId();
        }
        return null;
    }

    @PostMapping("/admin/soal/save")
    public String save(@ModelAttribute("soal") @Valid Soal soal, @RequestParam("filePertanyaan") MultipartFile filePertanyaan,
                       @RequestParam("fileJawaban1") MultipartFile fileJawaban1,
                       @RequestParam("fileJawaban2") MultipartFile fileJawaban2,
                       @RequestParam("fileJawaban3") MultipartFile fileJawaban3,
                       @RequestParam("fileJawaban4") MultipartFile fileJawaban4,
                       RedirectAttributes redirectAttributes) throws Exception {
        soalService.save(soal, materi, filePertanyaan, fileJawaban1, fileJawaban2, fileJawaban3, fileJawaban4);
        redirectAttributes.addFlashAttribute("saveResult", "success");
        return "redirect:/admin/soal/?idMateri=" + materi.getId();
    }

    @GetMapping(value = "/soal/api/lists")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseTemplate findListsRandom(@RequestParam("idMateri") Integer idMateri) throws DataNotFoundException {
        List<Soal> soals = soalService.findListsByIdMateriRandom(idMateri);
        if (soals.isEmpty()) {
            throw new DataNotFoundException();
        }
        return new ResponseTemplate(200, "Success", soals);
    }

    @GetMapping(value = "/soal/api/materi/lists")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseTemplate findAllMateri() throws DataNotFoundException{
        List<MateriList> materis = materiService.findOnlyIdAndName();
        if(materis.isEmpty()){
            throw new DataNotFoundException();
        }
        return new ResponseTemplate(200, "Success", materis);
    }
}

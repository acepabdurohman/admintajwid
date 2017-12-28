package id.ac.unikom.admintajwid.controller;

import id.ac.unikom.admintajwid.exception.DataNotFoundException;
import id.ac.unikom.admintajwid.model.Soal;
import id.ac.unikom.admintajwid.response.ResponseTemplate;
import id.ac.unikom.admintajwid.service.SoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SoalController {
    @Autowired
    private SoalService soalService;

    @GetMapping(value = "/admin/soal/{action}/{id}")
    public String findByIdSoal(@PathVariable("action") String action, @PathVariable("id") Integer id,
                               RedirectAttributes redirectAttributes, Model model){
        if(action.equals("edit")){
            Soal soal = soalService.findById(id);
            model.addAttribute("soal", soal);
            return "/soal/edit";
        }
        else if(action.equals("delete")){
            soalService.delete(id);
            redirectAttributes.addFlashAttribute("deleteResult", "success");
            return "redirect:/index";
        }
        return null;
    }

    @GetMapping(value = "/admin/soal/create")
    public ModelAndView linkToCreateForm(){
        ModelAndView modelAndView = new ModelAndView();
        Soal soal = new Soal();
        modelAndView.addObject("soal", soal);
        modelAndView.setViewName("/soal/create");
        return modelAndView;
    }

    @PostMapping("/admin/soal/save")
    public String save(@ModelAttribute("soal") Soal soal, @RequestParam("filePertanyaan") MultipartFile filePertanyaan,
                       @RequestParam("fileJawaban1") MultipartFile fileJawaban1,
                       @RequestParam("fileJawaban2") MultipartFile fileJawaban2,
                       @RequestParam("fileJawaban3") MultipartFile fileJawaban3,
                       @RequestParam("fileJawaban4") MultipartFile fileJawaban4,
                       RedirectAttributes redirectAttributes) throws Exception{
        soalService.save(soal, filePertanyaan, fileJawaban1, fileJawaban2, fileJawaban3, fileJawaban4);
        redirectAttributes.addFlashAttribute("saveResult", "success");
        return "redirect:/index";
    }

    @GetMapping(value = "/soal/api/lists")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseTemplate findListsRandom() throws DataNotFoundException{
        List<Soal> soals = soalService.findListsRandom();
        if(soals.isEmpty()){
            throw new DataNotFoundException();
        }
        return new ResponseTemplate(200, "Success", soals);
    }
}

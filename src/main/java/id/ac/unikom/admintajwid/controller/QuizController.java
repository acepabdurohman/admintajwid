package id.ac.unikom.admintajwid.controller;

import id.ac.unikom.admintajwid.model.Kelas;
import id.ac.unikom.admintajwid.model.Quiz;
import id.ac.unikom.admintajwid.service.KelasService;
import id.ac.unikom.admintajwid.service.QuizService;
import id.ac.unikom.admintajwid.util.MenuDropdownList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class QuizController {

    @Autowired
    private QuizService quizService;
    @Autowired
    private KelasService kelasService;
    @Autowired
    private MenuDropdownList menuDropdownList;

    @GetMapping("/admin/quiz/kelas")
    private ModelAndView findAll(@RequestParam("id") Integer id){
        Kelas kelas = kelasService.findById(id);
        List<Quiz> quizzes = quizService.findByIdKelasAndStatusSiswa(kelas, true);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("kelases", menuDropdownList.findAllKelas());
        modelAndView.addObject("materis", menuDropdownList.findAllMateri());
        modelAndView.addObject("quizzes", quizzes);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/admin/quiz/delete")
    private String delete(@RequestParam("id") Integer id, RedirectAttributes attributes){
        Quiz quiz = quizService.findOne(id);
        Integer idKelas = quiz.getSiswaKelas().getKelas().getId();
        quizService.delete(quiz);
        attributes.addFlashAttribute("deleteResult", "success");
        return "redirect:/admin/quiz/kelas?id=" + idKelas;
    }
}

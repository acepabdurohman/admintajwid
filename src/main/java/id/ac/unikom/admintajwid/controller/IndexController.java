package id.ac.unikom.admintajwid.controller;

import id.ac.unikom.admintajwid.model.Kelas;
import id.ac.unikom.admintajwid.model.Quiz;
import id.ac.unikom.admintajwid.model.User;
import id.ac.unikom.admintajwid.service.KelasService;
import id.ac.unikom.admintajwid.service.QuizService;
import id.ac.unikom.admintajwid.service.SoalService;
import id.ac.unikom.admintajwid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private KelasService kelasService;

    @GetMapping(value = {"/", "/index"})
    public String showIndex() {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //User user = userService.findUserByUsername(authentication.getName());
        //userService.loadUserByUsername(authentication.getName());
        //modelAndView.addObject("username", "Welcome " + user.getFirstName());
        List<Kelas> kelasList = kelasService.findAll();
        Integer firstId = null;
        for(Kelas kelas : kelasList){
            firstId = kelas.getId();
            break;
        }
        return "redirect:/admin/quiz/kelas?id=" + firstId;
    }

}

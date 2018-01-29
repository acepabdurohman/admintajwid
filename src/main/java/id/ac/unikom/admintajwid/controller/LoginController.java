package id.ac.unikom.admintajwid.controller;

import id.ac.unikom.admintajwid.model.User;
import id.ac.unikom.admintajwid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/login")
    public ModelAndView login(){
        userService.save();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value = "/lupa-password")
    public ModelAndView viewLupaPassword(){
        User user = new User();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("lupa-password");
        return modelAndView;
    }

    @PostMapping(value = "/email")
    public String sendEmail(@ModelAttribute("user") User user, RedirectAttributes attributes){
        User currentUser = userService.sendEmail(user);
        if(currentUser == null){
            attributes.addFlashAttribute("result", "failed");
            return "redirect:/lupa-password";
        }
        return "redirect:/";
    }
}

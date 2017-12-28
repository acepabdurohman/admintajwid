package id.ac.unikom.admintajwid.controller;

import id.ac.unikom.admintajwid.model.User;
import id.ac.unikom.admintajwid.service.SoalService;
import id.ac.unikom.admintajwid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Autowired
    private SoalService soalService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //User user = userService.findUserByUsername(authentication.getName());
        //userService.loadUserByUsername(authentication.getName());
        //modelAndView.addObject("username", "Welcome " + user.getFirstName());
        modelAndView.addObject("soals", soalService.findAll());
        modelAndView.setViewName("index");
        return modelAndView;
    }

}

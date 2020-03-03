package spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import spr.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView userPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("user", "Spring Security Tutorial");
        model.addObject("message", "Welcome Page !");
        model.setViewName("userPage");
        return model;
    }
}

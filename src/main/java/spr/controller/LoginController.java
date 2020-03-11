package spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spr.model.Role;
import spr.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String getLoginPage(Authentication authentication, ModelMap model, HttpServletRequest request) {
        if (authentication != null) {
            for (GrantedAuthority r : authentication.getAuthorities()) {
                if (r.getAuthority().equalsIgnoreCase("admin")) {
                    return "redirect:/admin/all";
                } else if (r.getAuthority().equalsIgnoreCase("user")) {
                    return "redirect:/user";
                } else return "login_page";
            }
        }
//        if (request.getParameterMap().containsKey("error")) {
//            model.addAttribute("error", true);
//        }
        return "login_page";
    }

}

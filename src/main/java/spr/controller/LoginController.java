package spr.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String getLoginPage(Authentication authentication) {
        if (authentication != null) {
            for (GrantedAuthority r : authentication.getAuthorities()) {
                if (r.getAuthority().equalsIgnoreCase("admin")) {
                    return "redirect:/admin/all";
                } else if (r.getAuthority().equalsIgnoreCase("user")) {
                    return "redirect:/user";
                } else return "login_page";
            }
        }
        return "login_page";
    }

}

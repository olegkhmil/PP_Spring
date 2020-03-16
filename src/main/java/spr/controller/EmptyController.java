package spr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class EmptyController {
    @GetMapping
    public String toLoginPage() {
        return "redirect:/login";
    }
}

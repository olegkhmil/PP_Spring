package spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spr.model.User;
import spr.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    public String getAllUsers(Model model) {
        try {
            List<User> usersFromDB = userService.getAllUsers();
            if (usersFromDB != null) {
                model.addAttribute("usersFromDB", usersFromDB);
                return "all_users_page";
            } else {
                model.addAttribute("result", "DB ERROR");
                return "result_page";
            }
        } catch (RuntimeException e) {
            model.addAttribute("result", "DB ERROR some kind of DBException");
            return "result_page";
        }
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id, Model model) {
        try {
            if (userService.deleteUser(id)) {
                model.addAttribute("usersFromDB", userService.getAllUsers());
                return "redirect:/allUsers";
            } else {
                model.addAttribute("result", "DB ERROR");
                return "result_page";
            }
        } catch (NumberFormatException e) {
            model.addAttribute("result", "NumberFormat ERROR");
            return "result_page";
        }
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("id") Long id, Model model) {
        try {
            User userFromDB = userService.getUserById(id);
            if (userFromDB != null) {
                model.addAttribute("userFromDB", userFromDB);
                return "update_page";
            } else {
                model.addAttribute("result", "DB ERROR");
                return "result_page";
            }
        } catch (NumberFormatException e) {
            model.addAttribute("result", "NumberFormat ERROR");
            return "result_page";
        }
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") Long id,
                             @RequestParam("name") String name,
                             @RequestParam("age") int age,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password,
                             @RequestParam("role") String role, Model model) {//@ModelAttribute @Valid  User user

        if (userService.updateUser(id, name, age, email, password, role)) {
            return "redirect:/allUsers";
        } else {
            model.addAttribute("result", "DB ERROR or email already exists");
            return "result_page";
        }
    }

    @GetMapping("/add")
    public String addUser() {
        return "add_page";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("age") int age,
                          @RequestParam("email") String email,
                          @RequestParam("password") String password,
                          @RequestParam("role") String role, Model model) {
        if (userService.addUser(name, age, email, password, role)) {
            return "redirect:/allUsers";
        } else {
            model.addAttribute("result", "DB ERROR or email already exists");
            return "result_page";
        }
    }

}

package spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spr.model.Role;
import spr.model.State;
import spr.model.User;
import spr.service.RoleService;
import spr.service.UserService;

import java.util.HashSet;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private org.springframework.security.crypto.password.PasswordEncoder pe;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, org.springframework.security.crypto.password.PasswordEncoder pe) {
        this.userService = userService;
        this.roleService = roleService;
        this.pe = pe;
    }

    @GetMapping("/all")
    public String getUsersPage(org.springframework.ui.ModelMap model) {
        model.addAttribute("usersFromDB", userService.getAllUsers());
        return "allUsers_page";
    }

    @GetMapping("/add")
    public String getAddPage() {
        return "add_page";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("age") int age,
                          @RequestParam("email") String email,
                          @RequestParam("password") String password,
                          @RequestParam("option1") String r1,
                          org.springframework.ui.Model model) {

        String[] roles = r1.split(",");
        java.util.Set<Role> roleSet = new HashSet<>();
        for (String s : roles) {
            roleSet.add(roleService.getRoleById(Integer.parseInt(s)));
        }

        User user = new User(name, age, email, password, roleSet, State.ACTIVE);
        if (userService.addUser(user)) {
            return "redirect:/admin/all";
        } else {
            model.addAttribute("result", "DB ERROR or user already exists");
            return "result_page";
        }
    }

    @GetMapping("/update")
    public String updateUser(@RequestParam("id") Long id, Model model) {
        try {
            User userFromDB = userService.getUserById(id);
            if (userFromDB != null) {
                model.addAttribute("userFromDB", userFromDB);
                model.addAttribute("rolesFromDB", roleService.getAllRoles());
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
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("oldPass") String oldPass,
                             @RequestParam("newPass") String newPass,
                             @RequestParam("option1") String r1,
                             Model model) {
        java.util.Set<Role> roleSet = new HashSet<>();
        String[] roles = r1.split(",");
        for (String s : roles) {
            roleSet.add(roleService.getRoleById(Integer.parseInt(s)));
        }
        user.setRoles(roleSet);
        if (newPass.equals("")) {
            user.setHash_password(oldPass);
        } else user.setHash_password(pe.encode(newPass));

        if (userService.updateUser(user)) {
            return "redirect:/admin/all";
        } else {
            model.addAttribute("result", "login already exists");
            return "result_page";
        }

    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/all";
    }

}

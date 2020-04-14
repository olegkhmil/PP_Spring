package spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import spr.model.Role;
import spr.model.State;
import spr.model.User;
import spr.service.RoleService;
import spr.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder pe;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder pe) {
        this.userService = userService;
        this.roleService = roleService;
        this.pe = pe;
    }

    @GetMapping("/all")
    public String getUsersPage() {
//        model.addAttribute("usersFromDB", userService.getAllUsers());
//        model.addAttribute("rolesFromDB", roleService.getAllRoles());
        return "allUsers_page";
    }

    @GetMapping("/add")
    public String getAddPage() {
        return "add_page";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("email") String mail,
                          @RequestParam("password") String password,
                          @RequestParam("age") int age,
                          @RequestParam("option1") String r1,
                          Model model) {

        String[] roles = r1.split(",");
        java.util.Set<Role> roleSet = new HashSet<>();
        for (String s : roles) {
            roleSet.add(roleService.getRoleById(Integer.parseInt(s)));
        }

        User user = new User(name, age, mail, password, roleSet, State.ACTIVE);
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
                model.addAttribute("usersFromDB", userService.getAllUsers());

                return "allUsers_page";
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
        Set<Role> roleSet = new HashSet<>();
        String[] roles = r1.split(",");
        for (String s : roles) {
            roleSet.add(roleService.getRoleById(Integer.parseInt(s)));
        }
        user.setRoles(roleSet);
        if (newPass.equals("")) {
            user.setPassword(oldPass);
        } else user.setPassword(pe.encode(newPass));

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

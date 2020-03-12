package spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spr.model.Role;
import spr.model.State;
import spr.model.User;
import spr.service.RoleService;
import spr.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/all")
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
                return "redirect:/admin/all";
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
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam("oldPass") String oldPass,
                             @RequestParam("role_name") String role, Model model) {// @Valid  User user
        Set<Role> roleSet = new HashSet<>();
        Role role1;
        if (role.contains("DEL") && user.getRoles().size() == 2) {
            roleSet = new HashSet<>(roleService.getAllRoles());
            role1 = roleService.getRoleByName(role.substring(4));
            roleSet.remove(role1);
            user.setRoles(roleSet);
        } else if (role.contains("DEL") && user.getRoles().size() < 2) {
            for (Role r : user.getRoles()) {
                String s = r.getRole_name().contains("ADMIN") ? "ADMIN" : "USER";
                if (s.equals(role.substring(4))) {
                    roleSet.add(roleService.getRoleByName(s));
                    user.setState(State.BANNED);
                    user.setRoles(roleSet);
                } else {
                    roleSet.add(roleService.getRoleByName(s));
                    user.setRoles(roleSet);
                }
            }
        }
        if (!role.contains("DEL")) {
            roleSet.add(roleService.getRoleByName(role));
            for (Role r : user.getRoles()) {
                String s = r.getRole_name().contains("ADMIN") ? "ADMIN" : "USER";
                roleSet.add(roleService.getRoleByName(s));
            }
            user.setRoles(roleSet);
        }

        if (!oldPass.equals(user.getHash_password())) {
            user.setHash_password(passwordEncoder.encode(user.getHash_password()));
        }
        if (userService.updateUser(user)) {
            return "redirect:/admin/all";
        } else {
            model.addAttribute("result", "DB ERROR or user already exists");
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
                          @ModelAttribute("role") Role role, Model model) {
        Set<Role> roleSet = new HashSet<>();
        Role role1 = roleService.getRoleByName(role.getRole_name());
        if (role1 != null) {
            roleSet.add(role1);
        } else {
            model.addAttribute("result", "DB ERROR or role don't exists");
            return "result_page";
        }

        User user = new User(name, age, email, password, roleSet, State.ACTIVE);
        if (userService.addUser(user)) {
            return "redirect:/admin/all";
        } else {
            model.addAttribute("result", "DB ERROR or email already exists");
            return "result_page";
        }
    }
}

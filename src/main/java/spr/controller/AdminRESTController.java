package spr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import spr.model.Role;
import spr.model.State;
import spr.model.User;
import spr.service.RoleService;
import spr.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/rest")
public class AdminRESTController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder pe;

    @GetMapping("/all")
    public List<User> getAll() {
        System.out.println(userService.getAllUsers().toString());
        return userService.getAllUsers();
    }

    @GetMapping("/userById/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable("id") Long id){
        User user = userService.getUserById(id);
        System.out.println(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/user/{userName}")
    public User getUserByName(@PathVariable("userName") String userName) {
        return userService.getUserByName(userName);
    }

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        Set<Role> roleSet = new HashSet<>();

        user.setState(State.ACTIVE);
        for (Role s : user.getRoles()) {
            roleSet.add(roleService.getRoleById(Integer.parseInt(s.getName())));
        }
        user.setRoles(roleSet);
        if (userService.addUser(user)) {
           User user1 = userService.getUserByName(user.getName());
            System.out.println(user1.toString());
            return new ResponseEntity<>(user1, HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {

        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        Set<Role> roleSet = new HashSet<>();

        user.setState(State.ACTIVE);
        for (Role s : user.getRoles()) {
            roleSet.add(roleService.getRoleById(Integer.parseInt(s.getName())));
        }
        user.setRoles(roleSet);
        if (user.getPassword().equals("")) {
            user.setPassword(userService.getUserById(user.getId()).getPassword());
        } else user.setPassword(pe.encode(user.getPassword()));

        if (userService.updateUser(user)) {
            User user1 = userService.getUserById(user.getId());
            System.out.println(user1);
            return new ResponseEntity<>(user1, HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
}

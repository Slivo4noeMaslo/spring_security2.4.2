package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/allUsers";
    }

    @GetMapping("/editUser/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/edit";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "ROLE_ADMIN", required = false) String role_admin) {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_USER"));
        if(role_admin != null) {
            roles.add(new Role("ROLE_ADMIN"));
        }
        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "admin/new";
    }

    @PostMapping("/new")
    public String createUser(@ModelAttribute("user") User user,
                             @RequestParam(value = "ROLE_ADMIN", required = false) String role_admin) {
        Set<Role> roles = new HashSet<>();
        roles.add(new Role("ROLE_USER"));
        if(role_admin != null) {
            roles.add(new Role("ROLE_ADMIN"));
        }
        user.setRoles(roles);
        userService.addUser(user);
        return "redirect:/admin";
    }
}

package com.kunikhin.kata313.controllers;

import com.kunikhin.kata313.entities.User;
import com.kunikhin.kata313.services.RoleService;
import com.kunikhin.kata313.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String getAllUsers(@AuthenticationPrincipal User user,Model model) {
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/all";
    }

    @GetMapping("/test")
    public String testPage(@AuthenticationPrincipal Object user, Model model) {
        model.addAttribute("principal", user);
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/admin_bootstrap";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/showUser";
    }


    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/new";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit (Model model, @PathVariable("id") long id ){
        model.addAttribute("user",userService.getUserById(id));
        return "admin/edit";
    }

    @PatchMapping("/{id}")
    public String update (@ModelAttribute ("user") User updateUser, @PathVariable ("id") long id) {
        userService.updateUser(id, updateUser);
        return "redirect:/admin";
    }


    @DeleteMapping("/{id}")
    public String delete (@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

}

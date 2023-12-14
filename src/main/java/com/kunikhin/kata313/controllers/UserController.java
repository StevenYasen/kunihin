package com.kunikhin.kata313.controllers;

import com.kunikhin.kata313.dao.UserDao;
import com.kunikhin.kata313.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserDao userDao;
    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping()
    public String myPage(Model model, Principal principal) {
        User user = userDao.loadUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user/myPage";
    }


}
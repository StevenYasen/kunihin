package com.kunikhin.kata313.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping(value = "/")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(value = "/logout")
    public String getLogout() {
        return "login";
    }
}

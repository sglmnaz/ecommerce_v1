package com.synclab.ecommerce.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login/form")
public class LoginController {

    @GetMapping
    public String getLogin(Model model){
        return "login";
    }
    
}

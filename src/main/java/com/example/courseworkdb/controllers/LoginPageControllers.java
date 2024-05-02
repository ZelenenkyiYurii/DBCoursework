package com.example.courseworkdb.controllers;

import com.example.courseworkdb.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginPageControllers {
    final LoginService loginService;

    public LoginPageControllers(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("")
    public String showLoginPage(){
        return "views/login";
    }

    @PostMapping("")
    public String login(@RequestParam String email, @RequestParam String password){
        return loginService.login(email,password);
    }
}

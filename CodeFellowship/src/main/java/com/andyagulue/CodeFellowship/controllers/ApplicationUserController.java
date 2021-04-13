package com.andyagulue.CodeFellowship.controllers;

import com.andyagulue.CodeFellowship.models.ApplicationUser;
import com.andyagulue.CodeFellowship.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.GeneratedValue;

@Controller
public class ApplicationUserController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @PostMapping("/applicationUser")
    public RedirectView createUser(String username, String password){
        password = passwordEncoder.encode(password);
        System.out.println("password = " + password);
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setPassword(password);
        applicationUser.setUsername(username);

        applicationUserRepository.save(applicationUser);
        return new RedirectView("/applicationUser");
         //TODO: create users
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "login.html";
    }
}

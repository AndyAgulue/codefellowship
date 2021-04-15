package com.andyagulue.CodeFellowship.controllers;

import com.andyagulue.CodeFellowship.models.ApplicationUser;
import com.andyagulue.CodeFellowship.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;


import javax.servlet.http.HttpServletRequest;

@Controller
public class ApplicationUserController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public RedirectView createUser(String username, String password, HttpServletRequest request){
        ApplicationUser applicationUser = new ApplicationUser();
        password = passwordEncoder.encode(password);
        applicationUser.setPassword(password);
        applicationUser.setUsername(username);
        applicationUserRepository.save(applicationUser);

        // creates users
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //===========user signed in=========
        return new RedirectView("/applicationUser");



    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "login.html";
    }

    @GetMapping("/signup")
    public String showSignupPage(){
       return "signup.html";
    }

    @GetMapping("/userProfile")
    public RedirectView getUser(HttpServletRequest request){
        ApplicationUser user = applicationUserRepository.findByUsername(request.getUserPrincipal().getName());
        return new RedirectView("/users/" + user.getId());
    }

    @GetMapping("users")
    public String getUsers(Model m, HttpServletRequest request){
        m.addAttribute("user", request.getUserPrincipal().getName());
        m.addAttribute("users", applicationUserRepository.findAll());
        return "users";
    }

    @GetMapping("/users/{id}")
    public String UserDetail(@PathVariable Long id, Model m, HttpServletRequest request){
        m.addAttribute("user", request.getUserPrincipal().getName());
        ApplicationUser subject = applicationUserRepository.getOne(id);
        m.addAttribute("subject", subject);
        return "userDetails.html";
    }


}

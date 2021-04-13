package com.andyagulue.CodeFellowship.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class CodeFellowshipController {
    @GetMapping("/")
    public String showCodeFellowshipHome(Principal p){ // Principal == the user
        //System.out.println("p = " + p);
        if(p != null){
            //System.out.println("p.getName() = " + p.getName());
        }
        return "index";
    }

    @GetMapping("/applicationUser")
    public String showFellows(Principal p, Model m){ // principal is like Model m. it represents the logged in user
        //System.out.println("p.getName() = " + p.getName());

//        m.addAttribute("user", p.getName());
        return "applicationUser";
    }
}

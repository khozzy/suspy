package com.pwr.teamfinder.controller;


import com.pwr.teamfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignupController {

    @RequestMapping("/signup")
    public String signup(){
        return "signup";
    }
}

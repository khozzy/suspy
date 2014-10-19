package com.pwr.teamfinder.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping("/error1")
    public String home(){
        return "404";
    }
}

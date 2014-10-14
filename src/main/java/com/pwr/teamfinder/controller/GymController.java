package com.pwr.teamfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/gym")
public class GymController {

    @RequestMapping(value = "/greetingSportObject", method = RequestMethod.GET)
    public String greeting(
            final @RequestParam(value = "name", required = false, defaultValue = "Sport Object") String name,
            final Model model) {

        model.addAttribute("name", name);

        return "greeting";
    }
}

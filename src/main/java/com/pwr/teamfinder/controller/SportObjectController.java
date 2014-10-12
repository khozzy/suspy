package com.pwr.teamfinder.controller;

import com.pwr.teamfinder.service.SportObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SportObjectController {

    @Autowired
    SportObjectService sportObjectService;

    @RequestMapping(value = "/greetingSportObject", method = RequestMethod.GET)
    public String greeting(
            final @RequestParam(value = "name", required = false, defaultValue = "Sport Object") String name,
            final Model model) {

        sportObjectService.someMethod();

        model.addAttribute("name", name);

        return "greeting";
    }
}

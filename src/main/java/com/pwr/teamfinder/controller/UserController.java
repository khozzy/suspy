package com.pwr.teamfinder.controller;

import com.pwr.teamfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greeting(
            final @RequestParam(value = "name", required = false, defaultValue = "World") String name,
            final Model model) {

        userService.someMethod();

        model.addAttribute("name", name);

        return "greeting";
    }
}

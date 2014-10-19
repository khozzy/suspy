package com.pwr.teamfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TeamController {

    @RequestMapping(value = "/greetingTeam", method = RequestMethod.GET)
    public String greetingTeam(
            final @RequestParam(value = "name", required = false, defaultValue = "Team") String name,
            final Model model) {

        model.addAttribute("name", name);

        return "greeting";
    }
}

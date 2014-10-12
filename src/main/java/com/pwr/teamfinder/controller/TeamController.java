package com.pwr.teamfinder.controller;

import com.pwr.teamfinder.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TeamController {

    @Autowired
    TeamService teamService;

    @RequestMapping(value = "/greetingTeam", method = RequestMethod.GET)
    public String greeting(
            final @RequestParam(value = "name", required = false, defaultValue = "Team") String name,
            final Model model) {

        teamService.someMethod();

        model.addAttribute("name", name);

        return "greeting";
    }
}

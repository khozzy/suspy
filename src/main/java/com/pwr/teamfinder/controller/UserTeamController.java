package com.pwr.teamfinder.controller;

import com.pwr.teamfinder.service.UserTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserTeamController {

    @Autowired
    UserTeamService userTeamService;

    @RequestMapping(value = "/greetingUserTeam", method = RequestMethod.GET)
    public String greeting(
            final @RequestParam(value = "name", required = false, defaultValue = "User Team") String name,
            final Model model) {

        userTeamService.someMethod();

        model.addAttribute("name", name);

        return "greeting";
    }
}

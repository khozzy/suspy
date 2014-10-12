package com.pwr.teamfinder.controller;

import com.pwr.teamfinder.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FriendController {

    @Autowired
    FriendService friendService;

    @RequestMapping(value = "/greetingFriend", method = RequestMethod.GET)
    public String greeting(
            final @RequestParam(value = "name", required = false, defaultValue = "Friend") String name,
            final Model model) {

        friendService.someMethod();

        model.addAttribute("name", name);

        return "greeting";
    }
}

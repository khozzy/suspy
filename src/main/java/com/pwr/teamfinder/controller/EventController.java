package com.pwr.teamfinder.controller;

import com.pwr.teamfinder.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventService eventService;

    @RequestMapping(value = "/greetingEvent", method = RequestMethod.GET)
    public String greeting(
            final @RequestParam(value = "name", required = false, defaultValue = "Event") String name,
            final Model model) {

        eventService.someMethod();

        model.addAttribute("name", name);

        return "greeting";
    }
}

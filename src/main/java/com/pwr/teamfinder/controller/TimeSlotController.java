package com.pwr.teamfinder.controller;

import com.pwr.teamfinder.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TimeSlotController {

    @Autowired
    TimeSlotService timeSlotService;

    @RequestMapping(value = "/greetingTimeSlot", method = RequestMethod.GET)
    public String greeting(
            final @RequestParam(value = "name", required = false, defaultValue = "Time Slot") String name,
            final Model model) {

        timeSlotService.someMethod();

        model.addAttribute("name", name);

        return "greeting";
    }
}

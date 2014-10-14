package com.pwr.teamfinder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/timeslot")
public class TimeSlotController {

    @RequestMapping(value = "/greetingTimeSlot", method = RequestMethod.GET)
    public String greeting(
            final @RequestParam(value = "name", required = false, defaultValue = "Time Slot") String name,
            final Model model) {

        model.addAttribute("name", name);

        return "greeting";
    }
}

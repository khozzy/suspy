package com.pwr.teamfinder.controller;

import com.pwr.teamfinder.service.mail.MailSender;
import com.pwr.teamfinder.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;

@Controller
public class EventController {

    @Autowired
    EventService eventService;

    @Autowired
    MailSender mailSender;

    @RequestMapping(value = "/greetingEvent", method = RequestMethod.GET)
    public String greeting(
            final @RequestParam(value = "name", required = false, defaultValue = "Event") String name,
            final Model model) throws MessagingException {

        eventService.someMethod();

        model.addAttribute("name", name);

        mailSender.send("To","Subject","Body");

        return "greeting";
    }
}

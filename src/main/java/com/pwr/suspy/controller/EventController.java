package com.pwr.suspy.controller;

import com.pwr.suspy.domain.Event;
import com.pwr.suspy.dto.NewEventForm;
import com.pwr.suspy.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/new")
    public String newEvent(Model model) {
        model.addAttribute(new NewEventForm());
        return "newEvent";
    }

    @RequestMapping(value = "/{eventId}")
    public String event(@PathVariable("eventId") String eventId, Model model) {
        return "event";
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(
            @RequestParam("query") String query,
            Model model) {

        List<Event> events = eventService.findByNameContaining(query);
        model.addAttribute("eventsFound", events);

        return "eventsSearch";
    }
}

package com.pwr.suspy.controller;

import com.pwr.suspy.domain.Event;
import com.pwr.suspy.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("event")
public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Page<Event> search(@RequestParam("query") String query) {
        logger.info("looking for events like " + query);
        Page<Event> events = eventService.findByNameContaining(query, null);
        logger.info("found " + events.getContent().size());
        return events;
    }
}

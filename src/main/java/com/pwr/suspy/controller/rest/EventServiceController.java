package com.pwr.suspy.controller.rest;


import com.pwr.suspy.domain.Event;
import com.pwr.suspy.exception.UserAlreadyExistsException;
import com.pwr.suspy.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/events")
public class EventServiceController {

    private static final Logger logger = LoggerFactory.getLogger(EventServiceController.class);

    private EventService eventService;

    @Autowired
    public EventServiceController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(method = RequestMethod.GET, headers = "accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public Page<Event> getEvents(
            @RequestParam(value = "query", defaultValue = "Suspy is the best") String query,
            @RequestParam(value = "pageNum", defaultValue = "0") Long pageNum,
            @RequestParam(value = "numOfResults", defaultValue = "10") Long numOfResults)
            {

        return eventService.findEvents(
                query,
                new PageRequest(
                        pageNum.intValue(),
                        numOfResults.intValue(),
                        new Sort(Sort.Direction.ASC, "name")));
    }

    @RequestMapping(value = "/{eventID}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<Event> getEvent(
            @PathVariable("eventID") Long eventID)  {

        if (eventService.exists(eventID)) {
            return new ResponseEntity<>(eventService.findById(eventID), new HttpHeaders(), HttpStatus.OK);
        }

        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<String> createEvent(
            @RequestBody Event event) {

        event = eventService.createNewEvent(event);
        return new ResponseEntity<>("Event " + event.getName() + " created.", new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{eventID}", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> updateEvent(
            @PathVariable("eventID") Long eventID,
            @RequestBody Event event){

        //userService.update(userID, user);
        return new ResponseEntity<>("Event " + event.getName() + " updated.", new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{eventID}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteEvent(
            @PathVariable("eventID") Long eventID)
            {

        eventService.delete(eventID);
        Event event = eventService.findById(eventID);
        return new ResponseEntity<>("Event " + event.getName() + " deleted.", new HttpHeaders(), HttpStatus.GONE);
    }

}

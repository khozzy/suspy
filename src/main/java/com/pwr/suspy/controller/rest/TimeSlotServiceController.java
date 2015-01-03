package com.pwr.suspy.controller.rest;


import com.pwr.suspy.domain.TimeSlot;
import com.pwr.suspy.service.TimeSlotService;
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
@RequestMapping("/service/timeslots")
public class TimeSlotServiceController {

    private static final Logger logger = LoggerFactory.getLogger(TimeSlotServiceController.class);

    private TimeSlotService timeSlotService;

    @Autowired
    public TimeSlotServiceController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @RequestMapping(method = RequestMethod.GET, headers = "accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public Page<TimeSlot> getTimeSlots(
            @RequestParam(value = "pageNum", defaultValue = "0") Long pageNum,
            @RequestParam(value = "numOfResults", defaultValue = "10") Long numOfResults)
            {

        return timeSlotService.findTimeSlots(
                new PageRequest(
                        pageNum.intValue(),
                        numOfResults.intValue(),
                        new Sort(Sort.Direction.ASC, "price")));
    }

    @RequestMapping(value = "/{timeSlotID}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<TimeSlot> getTimeSlot(
            @PathVariable("timeSlotID") Long timeSlotID)  {

        if (timeSlotService.exists(timeSlotID)) {
            return new ResponseEntity<>(timeSlotService.findById(timeSlotID), new HttpHeaders(), HttpStatus.OK);
        }

        return new ResponseEntity<>(new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<String> createTimeSlot(
            @RequestBody TimeSlot timeSlot){

        timeSlot = timeSlotService.createNewTimeSlot(timeSlot);
        return new ResponseEntity<>("TimeSlot " + timeSlot.getId() + " created.", new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{timeSlotID}", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> updateTimeSlot(
            @PathVariable("timeSlotID") Long timeSlotID,
            @RequestBody TimeSlot timeSlot){

        //userService.update(userID, user);
        return new ResponseEntity<>("TimeSlot " + timeSlot.getId() + " updated.", new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{timeSlotID}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteTimeSlot(
            @PathVariable("timeSlotID") Long timeSlotID)
            {

        timeSlotService.delete(timeSlotID);
        TimeSlot timeSlot = timeSlotService.findById(timeSlotID);
        return new ResponseEntity<>("Timeslot " + timeSlot.getId() + " deleted.", new HttpHeaders(), HttpStatus.GONE);
    }

}

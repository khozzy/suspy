package com.pwr.teamfinder.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwr.teamfinder.domain.User;
import com.pwr.teamfinder.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/service")
public class ServiceController {

    private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);

    private UserService userService;

    @Autowired
    public ServiceController(UserService userService) {
        this.userService = userService;
    }

    //Przykład zwracania JSONa - zwraca listę użytkowników serwisu
    @RequestMapping(value = "/users",
            method = RequestMethod.GET,
            headers = "Accept=application/json")
    public String returnUsers(
            @RequestParam(value="pageNum", defaultValue="1") String pageNum,
            @RequestParam(value="numOfResults", defaultValue="5") String numOfResults)
            throws JsonProcessingException {

       // Collection<User> users = userService.findAll(
                //new PageRequest(pageNum.parseInt(), numOfResults.parseInt(), new Sort(Sort.Direction.ASC, "name")));
               // new PageRequest(1, 1)); // nie działa:(
        Collection<User> users = userService.findAll();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(users);
    }
}

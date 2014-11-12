package com.pwr.suspy.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.pwr.suspy.domain.User;
import com.pwr.suspy.exception.UserAlreadyExistsException;
import com.pwr.suspy.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/service")
public class RootServiceController {

    private static final Logger logger = LoggerFactory.getLogger(RootServiceController.class);

    private UserService userService;

    @Autowired
    public RootServiceController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "forgotPassword/{userEmail}", method = RequestMethod.POST)
    public ResponseEntity<String> forgotPassword(@PathVariable("userEmail") String userEmail) {
        userService.emailExist(userEmail);
        //userService.forgotPassword(userEmail);
        return new ResponseEntity<>("Reset password link send to " + userEmail + ".",new HttpHeaders(),HttpStatus.FOUND);
    }

    @RequestMapping(value = "resetPassword/{resetPasswordCode}", method = RequestMethod.POST)
    public ResponseEntity<String> resetPassword(@PathVariable("userEmail") String userEmail) {
        userService.emailExist(userEmail);
        return new ResponseEntity<>("Reset password link send to " + userEmail + ".",new HttpHeaders(),HttpStatus.FOUND);
    }

}

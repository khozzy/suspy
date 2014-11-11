package com.pwr.teamfinder.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.pwr.teamfinder.domain.User;
import com.pwr.teamfinder.exception.UserAlreadyExistsException;
import com.pwr.teamfinder.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/service/users")
public class UserServiceController {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceController.class);

    private UserService userService;

    @Autowired
    public UserServiceController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET,
            headers = "Accept=application/json")
    public Collection<User> getUsers(
            @RequestParam(value="pageNum", defaultValue="1") String pageNum,
            @RequestParam(value="numOfResults", defaultValue="5") String numOfResults)
            throws JsonProcessingException {

        Collection<User> users = userService.findAll(
                new PageRequest(Integer.parseInt(pageNum), Integer.parseInt(numOfResults), new Sort(Sort.Direction.ASC, "name")));//why not working?:(
        //Collection<User> users = userService.findAll();
        return users;
    }

    @RequestMapping(method = RequestMethod.PUT, headers = "Content-Type=application/json")
    public User createUser(@RequestBody User user) throws JsonProcessingException, UserAlreadyExistsException {
        userService.signUp(user);
        return user;
    }

    @RequestMapping(value = "/{userID}", method = RequestMethod.GET, headers = "Accept=application/json")
    public User getUser(@PathVariable("userID") String userID) throws JsonProcessingException {
        return userService.findById(Long.parseLong(userID));
    }

    @RequestMapping(value = "/{userID}", method = RequestMethod.POST, headers = "Content-Type=application/json")
    public User updateUser(@PathVariable("userID") String userID, @RequestBody User user) throws JsonProcessingException {
        //userService.update(userID, user);
        return user;
    }

    @RequestMapping(value = "/{userID}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("userID") String userID) throws JsonProcessingException {
        userService.delete(Long.parseLong(userID));
        return "userDeleted";
    }
}

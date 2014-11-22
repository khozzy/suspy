package com.pwr.suspy.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.pwr.suspy.domain.User;
import com.pwr.suspy.exception.UserAlreadyExistsException;
import com.pwr.suspy.exception.UserAlreadyObservedException;
import com.pwr.suspy.service.UserService;
import com.pwr.suspy.util.MyUtil;
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
@RequestMapping("/service/users")
public class UserServiceController {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceController.class);

    private UserService userService;

    @Autowired
    public UserServiceController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseStatus(HttpStatus.OK)
    public Page<User> getUsers(
            @RequestParam(value="pageNum", defaultValue="0") Long pageNum,
            @RequestParam(value="numOfResults", defaultValue="5") Long numOfResults)
            throws JsonProcessingException {

        Page<User> page = userService.findAll(
                new PageRequest(pageNum.intValue(), numOfResults.intValue(), new Sort(Sort.Direction.ASC, "name")));
        return page;
    }

    @RequestMapping(value = "/{userID}", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<User> getUser(@PathVariable("userID") Long userID) throws JsonProcessingException {
        if (userService.exists(userID))
            return new ResponseEntity<>(userService.findById(userID),new HttpHeaders(),HttpStatus.FOUND);
        else
            return new ResponseEntity<>(new HttpHeaders(),HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<String> createUser(@RequestBody User user) throws JsonProcessingException, UserAlreadyExistsException {
        user = userService.createNewUser(user);
        return new ResponseEntity<>("User " + user.getEmail() + " created.",new HttpHeaders(),HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{userID}", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> updateUser(@PathVariable("userID") Long userID, @RequestBody User user) throws JsonProcessingException {
        //userService.update(userID, user);
        return new ResponseEntity<>("User " + user.getEmail() + " updated.",new HttpHeaders(),HttpStatus.OK);
    }

    @RequestMapping(value = "/{userID}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable("userID") Long userID) throws JsonProcessingException {
        userService.delete(userID);
        User user = userService.findById(userID);
        return new ResponseEntity<>("User " + user.getEmail() + " deleted.",new HttpHeaders(),HttpStatus.GONE);
    }

    @RequestMapping(value = "/{verificationCode}/verify", method = RequestMethod.GET)
    public ResponseEntity<String> verify(@PathVariable("verificationCode") String verificationCode) {
        userService.verifyUser(verificationCode);
        return new ResponseEntity<>("User " + MyUtil.getSessionUser().getEmail() + " verified.",new HttpHeaders(),HttpStatus.OK);
    }

    @RequestMapping(value = "/resendVerificationEmail", method = RequestMethod.GET)
    public ResponseEntity<String> resendVerificationEmail()
    {
        userService.resendVerificationEmail();

        return new ResponseEntity<>("Verification email resent.",new HttpHeaders(),HttpStatus.OK);
    }

    @RequestMapping(value = "/{userAid}/observe/{userBid}", method = RequestMethod.GET)
    public ResponseEntity<String> observe(@PathVariable("userAid") long userAid,
                                          @PathVariable("userBid") long userBid) {
        User userA = userService.findById(userAid);
        User userB = userService.findById(userBid);
        try {
            userService.observe(userA, userB);
        }
        catch(UserAlreadyObservedException e){
            return new ResponseEntity<>("User " + userA.getEmail() + " already observes user "+
                    userB.getEmail() + "verified.",new HttpHeaders(),HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>("User " + userA.getEmail() + " now observes user "+
                userB.getEmail() + ".",new HttpHeaders(),HttpStatus.OK);
    }

}

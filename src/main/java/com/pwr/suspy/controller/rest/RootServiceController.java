package com.pwr.suspy.controller.rest;

import com.pwr.suspy.domain.User;
import com.pwr.suspy.exception.UserNotExistsException;
import com.pwr.suspy.service.PlaceService;
import com.pwr.suspy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/service")
public class RootServiceController {

    private UserService userService;
    private PlaceService placeService;
    @Autowired
    public RootServiceController(UserService userService, PlaceService placeService) {
        this.userService = userService;
        this.placeService = placeService;
    }

    @RequestMapping(value = "/forgotPassword/{userEmail}", method = RequestMethod.POST)
    public ResponseEntity<String> forgotPassword(@PathVariable("userEmail") String userEmail)
            throws UserNotExistsException {

        if (!userService.emailExist(userEmail)) {
            throw new UserNotExistsException("User not exists");
        }

        userService.forgotPassword(userEmail);
        return new ResponseEntity<>("Reset password link send to " + userEmail + ".",new HttpHeaders(),HttpStatus.OK);
    }

    @RequestMapping(value = "/resetPassword/{resetPasswordCode}", method = RequestMethod.POST)
    public ResponseEntity<String> resetPassword(
            @PathVariable("resetPasswordCode") String resetPasswordCode,
            @RequestParam(value = "newPassword") String newPassword) {

        Optional<User> existing = userService.findByResetPasswordCode(resetPasswordCode);

        if (!existing.isPresent()) {
            return new ResponseEntity<>("Improper reset password code",new HttpHeaders(),HttpStatus.NOT_FOUND);
        } else {
            userService.resetPassword(resetPasswordCode,newPassword);
        }

        return new ResponseEntity<>("Password changed.",new HttpHeaders(),HttpStatus.OK);
    }


}

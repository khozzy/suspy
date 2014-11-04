package com.pwr.teamfinder.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwr.teamfinder.domain.User;
import com.pwr.teamfinder.dto.ForgotPasswordForm;
import com.pwr.teamfinder.dto.ResetPasswordForm;
import com.pwr.teamfinder.dto.SignupForm;
import com.pwr.teamfinder.exception.UserAlreadyExistsException;
import com.pwr.teamfinder.service.UserService;
import com.pwr.teamfinder.util.MyUtil;
import com.pwr.teamfinder.validators.ForgotPasswordFormValidator;
import com.pwr.teamfinder.validators.ResetPasswordFormValidator;
import com.pwr.teamfinder.validators.SignupFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
    @RequestMapping(value = "/users", method = RequestMethod.GET,
            headers = "Accept=application/json")
    public String returnUsers() throws JsonProcessingException {

        Collection<User> users = userService.getAll();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(users);
    }
}

package com.pwr.teamfinder.controller;

import com.pwr.teamfinder.domain.Role;
import com.pwr.teamfinder.exception.UserAlreadyExistsException;
import com.pwr.teamfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/create/user", method = RequestMethod.POST)
    public String createUser(
            final @RequestParam(value = "name", required = true) String name,
            final @RequestParam(value = "surname", required = true) String surname,
            final @RequestParam(value = "email", required = true) String email,
            final @RequestParam(value = "password", required = true) String password,
            final @RequestParam(value = "role", required = false, defaultValue = "SPORTSMAN") Role role,
            final @RequestParam(value = "city", required = true) String city,
            final @RequestParam(value = "houseNumber", required = false) String houseNo,
            final @RequestParam(value = "street", required = false) String street,
            final @RequestParam(value = "about", required = false) String about,
            final Model model) throws UserAlreadyExistsException {

       // User user = userServiceImpl.signup();

        //model.addAttribute("id", user.getId());

        return "create/user";
    }
}

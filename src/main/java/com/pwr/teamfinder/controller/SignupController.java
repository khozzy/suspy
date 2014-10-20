package com.pwr.teamfinder.controller;


import com.pwr.teamfinder.domain.User;
import com.pwr.teamfinder.dto.SignupForm;
import com.pwr.teamfinder.exception.UserAlreadyExistsException;
import com.pwr.teamfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignupController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/signup")
    public String signupForm(Model model){

        model.addAttribute(new SignupForm());

        return "signup";
    }

    @RequestMapping(value="/signup", method=RequestMethod.POST)
    public String signupSubmit(@ModelAttribute SignupForm signupForm, Model model) throws UserAlreadyExistsException {

        model.addAttribute("signupForm", signupForm);

        User user = userService.createNewUser(
                signupForm.getName(),
                signupForm.getSurname(),
                signupForm.getEmail(),
                signupForm.getPassword(),
                signupForm.getRole(),
                signupForm.getCity(),
                signupForm.getHouseNumber(),
                signupForm.getStreet(),
                signupForm.getAbout());

        model.addAttribute("id", user.getId());
        return "create/user";
    }

}

package com.pwr.teamfinder.controller;


import com.pwr.teamfinder.domain.User;
import com.pwr.teamfinder.dto.SignupForm;
import com.pwr.teamfinder.exception.UserAlreadyExistsException;
import com.pwr.teamfinder.service.UserServiceImpl;
import com.pwr.teamfinder.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SignupController {

    UserServiceImpl userServiceImpl;

    @Autowired
    public SignupController(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }

    @RequestMapping(value = "/signup")
    public String signup(Model model){

        model.addAttribute(new SignupForm());

        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@ModelAttribute @Valid SignupForm signupForm, BindingResult result,
                         Model model, RedirectAttributes redirectAttributes) throws UserAlreadyExistsException {

        if(result.hasErrors()){
            return "signup";
        }

        User user = userServiceImpl.signup(signupForm);

        //model.addAttribute("id", user.getId());

        MyUtil.flash(redirectAttributes,"success","signupSuccessMessage");

        return "redirect:/";
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public String userAlreadyExistsException(){
        return "signup";
    }

}

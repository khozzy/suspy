package com.pwr.teamfinder.controller;


import com.pwr.teamfinder.domain.User;
import com.pwr.teamfinder.dto.SignupForm;
import com.pwr.teamfinder.exception.UserAlreadyExistsException;
import com.pwr.teamfinder.service.UserService;
import com.pwr.teamfinder.util.MyUtil;
import com.pwr.teamfinder.validators.SignupFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class SignupController {

    @Autowired
    UserService userService;

    @Autowired
    SignupFormValidator signupFormValidator;

    private static final Logger logger = LoggerFactory.getLogger(SignupController.class);

    @InitBinder("signupForm")
    protected void initSignupBinder(WebDataBinder binder) {
        binder.setValidator(signupFormValidator);
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

            for(ObjectError err : result.getAllErrors()){

                logger.info(String.valueOf(err.toString()));

            }
            return "signup";
            //tu najlepiej returnować JSONa używająć Jackson API
            //http://www.concretepage.com/spring-4/spring-4-rest-web-service-json-example-tomcat
        }

        User user = userService.signup(signupForm);

        //model.addAttribute("id", user.getId());

        MyUtil.flash(redirectAttributes,"success","signupSuccessMessage");

        return "redirect:/";
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public String userAlreadyExistsException(){
        return "signup";
    }

}

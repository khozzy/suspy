package com.pwr.teamfinder.controller;


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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class RootController {

    private static final Logger logger = LoggerFactory.getLogger(RootController.class);

    private UserService userService;
    private SignupFormValidator signupFormValidator;
    private ForgotPasswordFormValidator forgotPasswordFormValidator;
    private ResetPasswordFormValidator resetPasswordFormValidator;

    @Autowired
    public RootController(UserService userService,
                          SignupFormValidator signupFormValidator,
                          ForgotPasswordFormValidator forgotPasswordFormValidator,
                          ResetPasswordFormValidator resetPasswordFormValidator) {
        this.userService = userService;
        this.signupFormValidator = signupFormValidator;
        this.forgotPasswordFormValidator = forgotPasswordFormValidator;
        this.resetPasswordFormValidator = resetPasswordFormValidator;
    }

        /*
    SIGNUP
     */

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
    public String signup(@ModelAttribute @Valid SignupForm signupForm,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes) throws UserAlreadyExistsException {

        if(result.hasErrors()){

            for(ObjectError err : result.getAllErrors()) logger.info(String.valueOf(err.toString()));

            return "signup";
            //tu najlepiej returnować JSONa używająć Jackson API
            //http://www.concretepage.com/spring-4/spring-4-rest-web-service-json-example-tomcat
        }

        User user = userService.signup(signupForm);

        //model.addAttribute("id", user.getId());

        MyUtil.flash(redirectAttributes,"success","signupSuccessMessage");

        return "redirect:/";
    }

    /*
    FORGOT PASSWORD
     */

    @InitBinder("forgotPasswordForm")
    protected void initForgotPasswordBinder(WebDataBinder binder) {
        binder.setValidator(forgotPasswordFormValidator);
    }

    @RequestMapping(value = "/forgot-password")
    public String forgotPassword(Model model){

        model.addAttribute(new ForgotPasswordForm());

        return "forgot-password";
    }

    @RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
    public String forgotPassword(
            @ModelAttribute("forgotPasswordForm") @Valid ForgotPasswordForm forgotPasswordForm,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors())
            return "forgot-password";

        userService.forgotPassword(forgotPasswordForm);
        MyUtil.flash(redirectAttributes, "info", "checkMailResetPassword");

        return "redirect:/";
    }

        /*
    RESET PASSWORD
     */

    @InitBinder("resetPasswordForm")
    protected void initResetPasswordBinder(WebDataBinder binder) {
        binder.setValidator(resetPasswordFormValidator);
    }

    @RequestMapping(value = "/reset-password/{resetPasswordCode}")
    public String resetPassword(@PathVariable("resetPasswordCode") String resetPasswordCode,
                                RedirectAttributes redirectAttributes,
                                Model model) {

        Optional<User> existing = userService.findByResetPasswordCode(resetPasswordCode);

        if(!existing.isPresent()){
            MyUtil.flash(redirectAttributes, "danger", "resetPasswordCodeNotValid");
            return "redirect:/";
        }

        model.addAttribute(new ResetPasswordForm());
        return "reset-password";

    }

    @RequestMapping(value = "/reset-password/{resetPasswordCode}",
            method = RequestMethod.POST)
    public String resetPassword(
            @PathVariable("resetPasswordCode") String resetPasswordCode,
            @ModelAttribute("resetPasswordForm") @Valid ResetPasswordForm resetPasswordForm,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        Optional<User> existing = userService.findByResetPasswordCode(resetPasswordCode);

        if(!existing.isPresent()){
            MyUtil.flash(redirectAttributes, "danger", "resetPasswordCodeNotValid");
            return "redirect:/";
        }

        if (result.hasErrors())
            return "reset-password";

        userService.resetPassword(resetPasswordCode,resetPasswordForm);

        MyUtil.flash(redirectAttributes, "success", "passwordChanged");

        return "redirect:/login";
    }
}

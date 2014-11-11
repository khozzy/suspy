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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
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

    @InitBinder("signupForm")
    protected void initSignupBinder(WebDataBinder binder) {
        binder.setValidator(signupFormValidator);
    }

    @InitBinder("resetPasswordForm")
    protected void initResetPasswordBinder(WebDataBinder binder) {
        binder.setValidator(resetPasswordFormValidator);
    }

    @InitBinder("forgotPasswordForm")
    protected void initForgotPasswordBinder(WebDataBinder binder) {
        binder.setValidator(forgotPasswordFormValidator);
    }

    @RequestMapping(value = "/signup")
    public String signUp(Model model)
    {

        model.addAttribute(new SignupForm());

        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@ModelAttribute @Valid SignupForm signupForm,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes) throws UserAlreadyExistsException
    {

        if (result.hasErrors()) {

            for (ObjectError err : result.getAllErrors()) logger.info(String.valueOf(err.toString()));

            return "signup";
            //tu najlepiej returnować JSONa używająć Jackson API
            //http://www.concretepage.com/spring-4/spring-4-rest-web-service-json-example-tomcat
        }

        User user = userService.signUp(signupForm);

        //model.addAttribute("id", user.getId());

        MyUtil.flash(redirectAttributes, "success", "signupSuccessMessage");

        return "redirect:/";
    }

    @RequestMapping(value = "/signupJson")
    public String signUpJson(Model model)
    {
        model.addAttribute(new SignupForm());

        return "signup";
    }


    //TUTAJ DO ZAGLĄDNIĘCIA (Problem z CSRF - dodaje token do headera ale
    //nadal forbidden, chyba, że nie dodaje tego tokena do headera
    @RequestMapping(value = "/signupJson", method = RequestMethod.POST)
    public String signUpJson(@ModelAttribute @Valid SignupForm signupForm,
                         BindingResult result,
                         Model model,
                         RedirectAttributes redirectAttributes,
                         HttpServletRequest request)
            throws UserAlreadyExistsException, JsonProcessingException {

        if (result.hasErrors()) {

            for (ObjectError err : result.getAllErrors()) logger.info(String.valueOf(err.toString()));

            return "signup";
        }

        User user = userService.convertSignUpFormToUser(signupForm);

        RestTemplate restTemplate = new RestTemplate();

        HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
        CsrfToken csrfToken=httpSessionCsrfTokenRepository.loadToken(request);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");

        //headers.add(csrfToken.getHeaderName(), csrfToken.getToken());
        //coś nie działa:( dlatego wyłączyłem w WebSecurityConfig csrf protection
        //trzeba pomyślec nad metodą autoryzacji dostępu do serwisów - albo używamy csrf
        //albo czegoś stąd https://stormpath.com/blog/how-secure-api-tips-rest-json-developers/

        ObjectMapper objectMapper = new ObjectMapper();
        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(user),headers);
        logger.info(entity.toString());
        restTemplate.put("http://localhost:8080/service/users", entity, String.class);
        //User user = userService.signup(user);

        MyUtil.flash(redirectAttributes, "success", "signupSuccessMessage");

        return "redirect:/";
    }

    @RequestMapping(value = "/forgot-password")
    public String forgotPassword(Model model)
    {

        model.addAttribute(new ForgotPasswordForm());

        return "forgot-password";
    }

    @RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
    public String forgotPassword(
            @ModelAttribute("forgotPasswordForm") @Valid ForgotPasswordForm forgotPasswordForm,
            BindingResult result,
            RedirectAttributes redirectAttributes)
    {

        if (result.hasErrors())
            return "forgot-password";

        userService.forgotPassword(forgotPasswordForm);
        MyUtil.flash(redirectAttributes, "info", "checkMailResetPassword");

        return "redirect:/";
    }

    @RequestMapping(value = "/reset-password/{resetPasswordCode}")
    public String resetPassword(@PathVariable("resetPasswordCode") String resetPasswordCode,
                                RedirectAttributes redirectAttributes,
                                Model model)
    {

        Optional<User> existing = userService.findByResetPasswordCode(resetPasswordCode);

        if (!existing.isPresent()) {
            MyUtil.flash(redirectAttributes, "danger", "resetPasswordCodeNotValid");
            return "redirect:/";
        }

        model.addAttribute(new ResetPasswordForm());
        return "reset-password";

    }

    @RequestMapping(value = "/reset-password/{resetPasswordCode}", method = RequestMethod.POST)
    public String resetPassword(
            @PathVariable("resetPasswordCode") String resetPasswordCode,
            @ModelAttribute("resetPasswordForm") @Valid ResetPasswordForm resetPasswordForm,
            BindingResult result,
            RedirectAttributes redirectAttributes)
    {

        Optional<User> existing = userService.findByResetPasswordCode(resetPasswordCode);

        if (!existing.isPresent()) {
            MyUtil.flash(redirectAttributes, "danger", "resetPasswordCodeNotValid");
            return "redirect:/";
        }

        if (result.hasErrors())
            return "reset-password";

        userService.resetPassword(resetPasswordCode, resetPasswordForm);

        MyUtil.flash(redirectAttributes, "success", "passwordChanged");

        return "redirect:/login";
    }
}

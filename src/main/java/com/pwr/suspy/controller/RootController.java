package com.pwr.suspy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwr.suspy.domain.User;
import com.pwr.suspy.dto.AddTimeSlotForm;
import com.pwr.suspy.dto.ForgotPasswordForm;
import com.pwr.suspy.dto.HomepageSearchForm;
import com.pwr.suspy.dto.NewEventForm;
import com.pwr.suspy.dto.ResetPasswordForm;
import com.pwr.suspy.dto.SignupForm;
import com.pwr.suspy.exception.UserAlreadyExistsException;
import com.pwr.suspy.service.PlaceService;
import com.pwr.suspy.service.TimeSlotService;
import com.pwr.suspy.service.UserService;
import com.pwr.suspy.util.MyUtil;
import com.pwr.suspy.validators.ForgotPasswordFormValidator;
import com.pwr.suspy.validators.ResetPasswordFormValidator;
import com.pwr.suspy.validators.SignupFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Optional;

@Controller
public class RootController {

    private static final Logger logger = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PlaceService placeService; //DLATESTOW;

    @Autowired
    private TimeSlotService timeSlotService;

    @Autowired
    private SignupFormValidator signupFormValidator;

    @Autowired
    private ForgotPasswordFormValidator forgotPasswordFormValidator;

    @Autowired
    private ResetPasswordFormValidator resetPasswordFormValidator;

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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage(Model model) {
        model.addAttribute("homePageSearch", new HomepageSearchForm());
        return "home";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String indexPage(@ModelAttribute("homePageSearch") @Valid HomepageSearchForm searchForm,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {

        logger.info("Got result from search form..");
        logger.info("looking for: " + searchForm.getSearchText());
        logger.info("target: " + searchForm.getSearchTarget());

        if (result.hasErrors()) {
            logger.warn("shit happend");
        } else {
            logger.info("no errors");
        }

        return "home";
    }

    @RequestMapping(value = "/signup")
    public String signUp(Model model) {
        model.addAttribute(new SignupForm());
        return "signup";
    }

    @RequestMapping(value = "/new-event")
    public String newEvent(Model model) {
        model.addAttribute(new NewEventForm());
        return "new-event";
    }

    @RequestMapping(value = "/event/{eventId}")
    public String event(@PathVariable("eventId") String eventId, Model model) {
        return "event";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@ModelAttribute @Valid SignupForm signupForm,
                         BindingResult result,
                         RedirectAttributes redirectAttributes) throws UserAlreadyExistsException {

        if (result.hasErrors()) {
            for (ObjectError err : result.getAllErrors()) logger.info(String.valueOf(err.toString()));
            return "signup";
        }

        final User user = userService.convertSignUpFormToUser(signupForm);
        userService.createNewUser(user);

        MyUtil.flash(redirectAttributes, "success", "signupSuccessMessage");

        return "redirect:/";
    }

    @RequestMapping(value = "/signupJson")
    public String signUpJson(Model model) {
        model.addAttribute(new SignupForm());
        return "signup";
    }

    @RequestMapping(value = "/signupJson", method = RequestMethod.POST)
    public String signUpJson(@ModelAttribute @Valid SignupForm signupForm,
                             BindingResult result,
                             RedirectAttributes redirectAttributes)
            throws UserAlreadyExistsException, JsonProcessingException {

        if (result.hasErrors()) {
            for (ObjectError err : result.getAllErrors()) {
                logger.info(String.valueOf(err.toString()));
            }

            return "signup";
        }

        User user = userService.convertSignUpFormToUser(signupForm);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");

        ObjectMapper objectMapper = new ObjectMapper();
        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(user),headers);
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.put(MyUtil.hostUrl() + "/service/users", entity, String.class);

        MyUtil.flash(redirectAttributes, "success", "signupSuccessMessage");

        return "redirect:/";
    }

    @RequestMapping(value = "/forgot-password")
    public String forgotPassword(Model model) {
        model.addAttribute(new ForgotPasswordForm());
        return "forgot-password";
    }

    @RequestMapping(value = "/forgot-password", method = RequestMethod.POST)
    public String forgotPassword(
            @ModelAttribute("forgotPasswordForm") @Valid ForgotPasswordForm forgotPasswordForm,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {return "forgot-password";}

        userService.forgotPassword(forgotPasswordForm.getEmail());
        MyUtil.flash(redirectAttributes, "info", "checkMailResetPassword");

        return "redirect:/";
    }

    @RequestMapping(value = "/reset-password/{resetPasswordCode}")
    public String resetPassword(@PathVariable("resetPasswordCode") String resetPasswordCode,
                                RedirectAttributes redirectAttributes,
                                Model model) {

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
            RedirectAttributes redirectAttributes) {

        Optional<User> existing = userService.findByResetPasswordCode(resetPasswordCode);

        if (!existing.isPresent()) {
            MyUtil.flash(redirectAttributes, "danger", "resetPasswordCodeNotValid");
            return "redirect:/";
        }

        if (result.hasErrors()) {return "reset-password";}

        userService.resetPassword(resetPasswordCode, resetPasswordForm.getPassword().trim());

        MyUtil.flash(redirectAttributes, "success", "passwordChanged");

        return "redirect:/login";
    }

    @RequestMapping(value = "/addTimeSlot", method = RequestMethod.GET)
    public String addTimeSlot(Model model) {
        model.addAttribute(new AddTimeSlotForm());
        return "addTimeSlot";
    }

    @RequestMapping(value = "/addTimeSlot", method = RequestMethod.POST)
    public String addTimeSlot(@ModelAttribute("addTimeSlotForm") @Valid AddTimeSlotForm addTimeSlotForm,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
             MyUtil.flash(redirectAttributes, "failure", "errorTryAgain");
            return "addTimeSlot";
        }

        logger.info(addTimeSlotForm.toString());

        try {
            timeSlotService.createNewTimeSlot(timeSlotService.getTimeSlot(addTimeSlotForm));
        } catch (ParseException ex) {
            MyUtil.flash(redirectAttributes, "failure", "Parsing error Try Again");
            return "addTimeSlot";
        } catch (NumberFormatException ex) {
            MyUtil.flash(redirectAttributes, "failure", "Not a number");
            return "addTimeSlot";
        }

        MyUtil.flash(redirectAttributes, "success", "timeSlot.added");

        return "redirect:/";
    }
}

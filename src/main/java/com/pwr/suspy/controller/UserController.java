package com.pwr.suspy.controller;

import com.pwr.suspy.domain.User;
import com.pwr.suspy.dto.UserEditForm;
import com.pwr.suspy.exception.UserAlreadyObservedException;
import com.pwr.suspy.service.UserService;
import com.pwr.suspy.util.MyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/{verificationCode}/verify")
    public String verify(@PathVariable("verificationCode") String verificationCode,
                         RedirectAttributes redirectAttributes,
                         HttpServletRequest request) {

        userService.verifyUser(verificationCode);
        MyUtil.flash(redirectAttributes, "success", "verificationSuccess");
        //request.logout();

        return "redirect:/";

    }

    @RequestMapping("/resend-verification-email")
    public String resendVerificationEmail(RedirectAttributes redirectAttributes) {

        userService.resendVerificationEmail();
        MyUtil.flash(redirectAttributes, "success", "verificationEmailResendSuccess");

        return "redirect:/";
    }

    @RequestMapping
    public String showUsersList()
    {
        return "usersList";
    }

    @RequestMapping("/{userID}")
    public String showUserProfile(
            @PathVariable("userID") Long userID,
            Model model){

        /*RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> entity = restTemplate.getForEntity(
                MyUtil.hostUrl() + "/service/users/" + userID, User.class);
        
        User user = entity.getBody();
        */
        
        User user = userService.findById(userID);

        if (MyUtil.getSessionUser()!= null) {
            boolean observed = userService.findById(MyUtil.getSessionUser()
                    .getId()).getObserved().contains(userService.findById(user.getId()));
            logger.info(MyUtil.getSessionUser().getId().toString());
            logger.info(String.valueOf(observed));
            model.addAttribute("observed",observed);
        }

        user = userService.checkPermissions(user);
        model.addAttribute(user);

        return "user";
    }

    @RequestMapping(value = "/{userId}/edit")
    public String edit(@PathVariable("userId") Long userId, Model model) {

        User user = userService.findById(userId);
        UserEditForm form = new UserEditForm();
        form.setName(user.getName());
        form.setRoles(user.getRoles());
        model.addAttribute(form);

        return "user-edit";

    }

    @RequestMapping(value = "/{userId}/edit", method = RequestMethod.POST)
    public String edit(@PathVariable("userId") Long userID,
                       @ModelAttribute("userEditForm") @Valid UserEditForm userEditForm,
                       BindingResult result, RedirectAttributes redirectAttributes,
                       HttpServletRequest request) throws ServletException {

        if (result.hasErrors())
            return "user-edit";

        userService.updateUser(userID, userEditForm);
        MyUtil.flash(redirectAttributes, "success", "editSuccessful");
        request.logout();

        return "redirect:/users/"+userID;
    }

    @RequestMapping(value = "/{userId}/change-password")
    public String changePassword(@PathVariable("userId") Long userId, Model model) {
        return "change-password";
    }

    @RequestMapping(value = "/{userId}/change-email")
    public String changeEmail(@PathVariable("userId") Long userId, Model model) {
        return "change-email";
    }

    @RequestMapping(value = "/{userId}/advanced")
    public String userAdvanced(@PathVariable("userId") Long userId, Model model) {
        return "userAdvanced";
    }

    @RequestMapping("/{userID}/startObserving")
    public String startObserving(@PathVariable("userID") Long userID,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {

        User userA = userService.findById(MyUtil.getSessionUser().getId());
        User userB = userService.findById(userID);

        try {
            userService.observe(userA,userB);
        } catch (UserAlreadyObservedException e) {
            MyUtil.flash(redirectAttributes, "danger", "userAlreadyObserved");
            return "redirect:/users/"+userID;
        }

        return "redirect:/users/"+userID;
    }

    @RequestMapping("/{userID}/stopObserving")
    public String stopObserving(@PathVariable("userID") Long userID,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        User userA = userService.findById(MyUtil.getSessionUser().getId());
        User userB = userService.findById(userID);

        userService.stopObserving(userA,userB);

        return "redirect:/users/"+userID;
    }

}

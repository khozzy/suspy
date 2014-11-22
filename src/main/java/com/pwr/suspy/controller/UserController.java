package com.pwr.suspy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwr.suspy.domain.User;
import com.pwr.suspy.dto.UserEditForm;
import com.pwr.suspy.service.UserService;
import com.pwr.suspy.util.MyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public String resendVerificationEmail(RedirectAttributes redirectAttributes)
    {

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
            @PathVariable("userID") String userID,
            Model model){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> entity = restTemplate.getForEntity(
                MyUtil.hostUrl() + "/service/users/" + userID, User.class);
        User user = entity.getBody();
        user = userService.checkPermissions(user);
        model.addAttribute(user);
        return "user";
    }

    @RequestMapping(value = "/{userId}/edit")
    public String edit(@PathVariable("userId") long userId, Model model) {

        User user = userService.findById(userId);
        UserEditForm form = new UserEditForm();
        form.setName(user.getName());
        form.setRoles(user.getRoles());
        model.addAttribute(form);

        return "user-edit";

    }

    @RequestMapping(value = "/{userId}/edit", method = RequestMethod.POST)
    public String edit(@PathVariable("userId") long userId,
                       @ModelAttribute("userEditForm") @Valid UserEditForm userEditForm,
                       BindingResult result, RedirectAttributes redirectAttributes,
                       HttpServletRequest request) throws ServletException {

        if (result.hasErrors())
            return "user-edit";

        userService.updateUser(userId, userEditForm);
        MyUtil.flash(redirectAttributes, "success", "editSuccessful");
        request.logout();

        return "redirect:/";
    }



}

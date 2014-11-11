package com.pwr.teamfinder.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pwr.teamfinder.domain.User;
import com.pwr.teamfinder.service.UserService;
import com.pwr.teamfinder.util.MyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

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
                         HttpServletRequest request)
    {

        userService.verify(verificationCode);
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
            @PathVariable("userID") String userID)
    {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> user = restTemplate.getForEntity(
                MyUtil.hostUrl() + "/service/users/" + userID,User.class);

        logger.info(user.toString());

        return "user";
    }

}

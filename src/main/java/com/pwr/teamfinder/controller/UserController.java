package com.pwr.teamfinder.controller;

import com.pwr.teamfinder.service.UserService;
import com.pwr.teamfinder.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/{verificationCode}/verify")
    public String verify(@PathVariable("verificationCode") String verificationCode,
                         RedirectAttributes redirectAttributes,
                         HttpServletRequest request) throws ServletException {

        userService.verify(verificationCode);
        MyUtil.flash(redirectAttributes, "success", "verificationSuccess");
        //request.logout();

        return "redirect:/";

    }

    @RequestMapping("/resend-verification-email")
    public String resendVerificationEmail(RedirectAttributes redirectAttributes)
            throws ServletException {

        userService.resendVerificationEmail();
        MyUtil.flash(redirectAttributes, "success", "verificationEmailResendSuccess");

        return "redirect:/";
    }
}

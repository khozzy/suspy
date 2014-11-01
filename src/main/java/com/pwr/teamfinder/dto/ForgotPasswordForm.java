package com.pwr.teamfinder.dto;


import com.pwr.teamfinder.domain.User;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ForgotPasswordForm {

    @NotBlank(message = "{email.error.blank}")
    @Size(max = User.EMAIL_MAX, message = "{email.error.size}")
    @Pattern(regexp = User.EMAIL_PATTERN, message = "{email.error.pattern}")
    @Email(message = "{email.error.pattern}")
    private String email = "";//dlaczego to tak?

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
package com.pwr.teamfinder.dto;

import com.pwr.teamfinder.domain.User;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;

public class ResetPasswordForm {

    @NotBlank(message = "{password.error.blank}")
    @Size(min=User.PASSWORD_MIN, max= User.PASSWORD_MAX, message="{password.error.size}")
    private String password = "";

    @NotBlank(message = "{password.error.blank}")
    @Size(min=User.PASSWORD_MIN, max= User.PASSWORD_MAX, message="{password.error.size}")
    private String retypePassword = "";

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRetypePassword() {
        return retypePassword;
    }

    public void setRetypePassword(String retypePassword) {
        this.retypePassword = retypePassword;
    }

}

package com.pwr.suspy.dto;

import com.pwr.suspy.domain.Role;
import com.pwr.suspy.domain.User;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

public class UserEditForm {

    @NotBlank(message = "{name.error.blank}")
    @Size(min = User.NAME_MIN, max = User.NAME_MAX, message = "{name.error.size}")
    @Pattern(regexp = User.REQUIRED_PATTERN, message = "{name.error.pattern}")
    private String name = "";

    private Set<Role> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}

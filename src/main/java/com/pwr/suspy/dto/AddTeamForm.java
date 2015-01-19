package com.pwr.suspy.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by Tomasz on 2015-01-19.
 */
public class AddTeamForm {
    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.pwr.suspy.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class HomepageSearchForm {

    @NotBlank
    @NotNull
    private String searchText;

    @NotBlank
    @NotNull
    private String searchTarget = "event";

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getSearchTarget() {
        return searchTarget;
    }

    public void setSearchTarget(String searchTarget) {
        this.searchTarget = searchTarget;
    }
}

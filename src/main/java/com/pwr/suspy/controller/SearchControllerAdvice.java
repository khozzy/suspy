package com.pwr.suspy.controller;

import com.pwr.suspy.dto.HomepageSearchForm;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class SearchControllerAdvice {

    @ModelAttribute("homePageSearch")
    public HomepageSearchForm homePageSearch() {
        return new HomepageSearchForm();
    }
}

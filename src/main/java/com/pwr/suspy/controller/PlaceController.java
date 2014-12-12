package com.pwr.suspy.controller;


import com.pwr.suspy.domain.Place;
import com.pwr.suspy.dto.AddPlaceForm;
import com.pwr.suspy.service.PlaceService;
import com.pwr.suspy.util.MyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("place")
public class PlaceController {

    private static final Logger logger = LoggerFactory.getLogger(PlaceController.class);

    @Autowired
    private PlaceService placeService;

    @RequestMapping(value = "/list")
    public String showUsersList()
    {
        return "placesList";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addPlace(Model model) {
        model.addAttribute("addPlaceForm", new AddPlaceForm());
        return "addPlace";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPlace(@ModelAttribute("addPlaceForm") @Valid AddPlaceForm addPlaceForm,
                           BindingResult result,
                           RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            MyUtil.flash(redirectAttributes, "failure", "errorTryAgain");
            return "addPlace";
        }
        logger.info(addPlaceForm.toString());
        final Place place = placeService.getPlace(addPlaceForm, MyUtil.getSessionUser());
        MyUtil.flash(redirectAttributes, "success", "place.added");
        return "redirect:/";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Page<Place> search(@RequestParam("query") String query) {
        logger.info("looking for places like " + query);
        Page<Place> places = placeService.findByNameContaining(query, null);
        logger.info("found " + places.getContent().size());
        return places;
    }
}

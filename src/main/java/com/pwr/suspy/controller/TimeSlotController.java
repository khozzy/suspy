package com.pwr.suspy.controller;

import com.pwr.suspy.dto.AddTimeSlotForm;
import com.pwr.suspy.service.TimeSlotService;
import com.pwr.suspy.util.MyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.ParseException;

@Controller
@RequestMapping("/places/{placeId}/timeslots/new")
public class TimeSlotController {

    private static final Logger logger = LoggerFactory.getLogger(PlaceController.class);

    @Autowired
    private TimeSlotService timeSlotService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String addTimeSlot(Model model) {
        model.addAttribute(new AddTimeSlotForm());
        return "newTimeSlot";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addTimeSlot(@ModelAttribute("addTimeSlotForm") @Valid AddTimeSlotForm addTimeSlotForm,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            MyUtil.flash(redirectAttributes, "failure", "errorTryAgain");
            return "newTimeSlot";
        }

        logger.info(addTimeSlotForm.toString());

        try {
            timeSlotService.createNewTimeSlot(timeSlotService.getTimeSlot(addTimeSlotForm));
        } catch (ParseException ex) {
            MyUtil.flash(redirectAttributes, "failure", "Parsing error Try Again");
            return "newTimeSlot";
        } catch (NumberFormatException ex) {
            MyUtil.flash(redirectAttributes, "failure", "Not a number");
            return "newTimeSlot";
        }

        MyUtil.flash(redirectAttributes, "success", "timeSlot.added");

        return "redirect:/";
    }

}

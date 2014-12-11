package com.pwr.suspy.controller;

import com.pwr.suspy.domain.TimeSlot;
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

/**
 * Created by NecroMac on 2014-12-11.
 */

@Controller
@RequestMapping("timeslot")
public class TimeSlotController
{
    private static final Logger logger = LoggerFactory.getLogger(PlaceController.class);

    private TimeSlotService timeSlotService;
    @Autowired
    public TimeSlotController(TimeSlotService placeService) {
        this.timeSlotService = timeSlotService;
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addTimeSlot(Model model) {
        model.addAttribute("addTimeSlot", new AddTimeSlotForm());
        return "addTimeSlot";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addTimeSlot(@ModelAttribute("addTimeSlotForm") @Valid AddTimeSlotForm addTimeSlotForm,
                           BindingResult result,
                           RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            MyUtil.flash(redirectAttributes, "failure", "errorTryAgain");
            return "addTimeSlot";
        }

        logger.info(addTimeSlotForm.toString());
        try {
            final TimeSlot timeSlot = timeSlotService.getTimeSlot(addTimeSlotForm);
            timeSlotService.createNewTimeSlot(timeSlot);
        } catch (ParseException ex) {
            MyUtil.flash(redirectAttributes, "failure", "errorTryAgain");
            return "addTimeSlot";
        }
        MyUtil.flash(redirectAttributes, "success", "timeSlot.added");
        return "redirect:/";
    }

}

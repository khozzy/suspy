package com.pwr.suspy.controller;

import com.pwr.suspy.domain.Place;
import com.pwr.suspy.domain.TimeSlot;
import com.pwr.suspy.dto.AddPlaceForm;
import com.pwr.suspy.dto.EditPlaceForm;
import com.pwr.suspy.dto.EditTimeSlotForm;
import com.pwr.suspy.service.PlaceService;
import com.pwr.suspy.service.TimeSlotService;
import com.pwr.suspy.util.MyUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.security.pkcs.ParsingException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("place")
public class PlaceController {

    private static final Logger logger = LoggerFactory.getLogger(PlaceController.class);

    @Autowired
    private PlaceService placeService;

    @Autowired
    private TimeSlotService timeSlotService;

//    @RequestMapping(value = "/list") //OBSOLETE
//    public String showPlacesList()
//    {
//        return "redirect:/place/search?query=";
//    }

    @RequestMapping(value = "/mylist")
    public String ShowMyPlacesList(Model model)
    {
        List<Place> places = placeService.findByOwner(MyUtil.getSessionUser());
        model.addAttribute("placesFound", places);
        return "placesMyList";
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
    public String search(
            @RequestParam("query") String query,
            Model model) {

        List<Place> places = placeService.findByNameContaining(query);
        model.addAttribute("placesFound", places);

        return "placesSearch";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.GET)
    public String editPlace(
            @RequestParam("id") String s_id,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            long id = Long.parseLong(s_id);
            Place place = placeService.findById(id);
            if(MyUtil.getSessionUser().getId() != place.getOwner().getId())
            {
                MyUtil.flash(redirectAttributes, "failure", "noPermissions");
                return "redirect:/error";
            }
            model.addAttribute("editedPlace", place);
            EditPlaceForm editPlaceForm = new EditPlaceForm();
            model.addAttribute("editPlaceForm", editPlaceForm);

            return "placesEdit";
        }catch (NumberFormatException ex)
        {
            logger.info("ID is not a number");
            return "redirect:/error";
        }
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String editPlace(@ModelAttribute("editPlaceForm") @Valid EditPlaceForm editPlaceForm,
                            @RequestParam("id") String s_id,
                RedirectAttributes redirectAttributes) {
        try {
            long id = Long.parseLong(s_id);
            Place place = placeService.findById(id);

            if(MyUtil.getSessionUser().getId() != place.getOwner().getId())
            {
                MyUtil.flash(redirectAttributes, "failure", "noPermissions");
                return "redirect:/error";
            }
            placeService.editPlace(editPlaceForm,place);
            MyUtil.flash(redirectAttributes, "success", "place.edit.success");

            return "redirect:/place/mylist";
        }catch (NumberFormatException ex)
        {
            MyUtil.flash(redirectAttributes, "failure", "errorTryAgain");
            logger.info("ID is not a number");
            return "redirect:/error";
        }
    }

    @RequestMapping(value = "/timeslot/mylist",method = RequestMethod.GET)
    public String myPlaceListOfTimeSlots(
            @RequestParam("id") String s_id,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            long id = Long.parseLong(s_id);
            Place place = placeService.findById(id);
//            if(MyUtil.getSessionUser().getId() != place.getOwner().getId())
//            {
//                MyUtil.flash(redirectAttributes, "failure", "noPermissions");
//                return "redirect:/place/mylist";
//            }
            model.addAttribute("loggedUser",MyUtil.getSessionUser());
            model.addAttribute("editedPlace", place);
            List<TimeSlot> timeSlots = timeSlotService.findByPlace(place);//placeService.findByOwner(MyUtil.getSessionUser());
            model.addAttribute("timeSlotsFound", timeSlots);
            return "placesTimeslotMyList";
        }catch (NumberFormatException ex)
        {
            logger.info("ID is not a number");
            return "redirect:/place/mylist";
        }
    }
    @RequestMapping(value = "/timeslots",method = RequestMethod.GET)
    public String placeListOfTimeSlots(
            @RequestParam("id") String s_id,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            long id = Long.parseLong(s_id);
            Place place = placeService.findById(id);
            model.addAttribute("editedPlace", place);
            List<TimeSlot> timeSlots = timeSlotService.findByPlace(place);//placeService.findByOwner(MyUtil.getSessionUser());
            model.addAttribute("timeSlotsFound", timeSlots);
            return "placesTimeslotList";
        }catch (NumberFormatException ex)
        {
            logger.info("ID is not a number");
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/timeslot/edit",method = RequestMethod.GET)
    public String editTimeslot(
            @RequestParam("id") String s_id,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            long id = Long.parseLong(s_id);
            TimeSlot timeSlot = timeSlotService.findById(id);

            if(MyUtil.getSessionUser().getId() != timeSlot.getPlace().getOwner().getId())
            {
                MyUtil.flash(redirectAttributes, "failure", "noPermissions");
                return "redirect:/";
            }

            EditTimeSlotForm editTimeSlotForm = new EditTimeSlotForm();
            model.addAttribute("editTimeSlotForm", editTimeSlotForm);
            model.addAttribute("timeslot",timeSlot);
            return "editTimeslot";
        }catch (NumberFormatException ex)
        {
            logger.info("ID is not a number");
            return "redirect:/";
        }
    }
    @RequestMapping(value = "/timeslot/edit",method = RequestMethod.POST)
    public String editTimeslot(@ModelAttribute("editTimeSlotForm") @Valid EditTimeSlotForm editTimeSlotForm,
                            @RequestParam("id") String s_id,
                            RedirectAttributes redirectAttributes) {
        logger.info("Edit timeslot 01");
        try {
            long id = Long.parseLong(s_id);
            TimeSlot timeSlot = timeSlotService.findById(id);
            if(MyUtil.getSessionUser().getId() != timeSlot.getPlace().getOwner().getId())
            {
                MyUtil.flash(redirectAttributes, "failure", "noPermissions");
                return "redirect:/error";
            }
            logger.info("Edit timeslot 02:" + editTimeSlotForm.getPrice() + " " +editTimeSlotForm.getDate_from() + " "
                    + editTimeSlotForm.getHour_from() +" " + editTimeSlotForm.getDate_to() + " " + editTimeSlotForm.getHour_to());
            timeSlotService.editTimeSlot(editTimeSlotForm,timeSlot);
            if(!NumberUtils.isNumber(editTimeSlotForm.getPrice())
                && (editTimeSlotForm.getDate_from().isEmpty() || editTimeSlotForm.getHour_from().isEmpty())
                && (editTimeSlotForm.getDate_to().isEmpty() || editTimeSlotForm.getHour_to().isEmpty())) {
                MyUtil.flash(redirectAttributes, "failure", "place.edit.timeslot.noChange");
            }else {
                MyUtil.flash(redirectAttributes, "success", "place.edit.timeslot.success");
            }
            return "redirect:/place/timeslot/mylist?id="+timeSlot.getPlace().getId();
        }catch (NumberFormatException ex)
        {
            logger.info("ID is not a number");
            return "redirect:/error";
        }
    }

    @RequestMapping(value = "/timeslot/add",method = RequestMethod.GET)
    public String addTimeslot(
            @RequestParam("id") String s_id,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            long id = Long.parseLong(s_id);
            TimeSlot timeSlot = timeSlotService.findById(id);

            if(MyUtil.getSessionUser().getId() != timeSlot.getPlace().getOwner().getId())
            {
                MyUtil.flash(redirectAttributes, "failure", "noPermissions");
                return "redirect:/";
            }
            Place place = placeService.findById(id);

            EditTimeSlotForm editTimeSlotForm = new EditTimeSlotForm();
            model.addAttribute("editTimeSlotForm", editTimeSlotForm);
            model.addAttribute("place",place);
            return "editTimeslot";
        }catch (NumberFormatException ex)
        {
            logger.info("ID is not a number");
            return "redirect:/";
        }
    }
}

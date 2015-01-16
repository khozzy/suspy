package com.pwr.suspy.controller;

import com.pwr.suspy.domain.Team;
import com.pwr.suspy.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Tomasz on 2015-01-13.
 */
@Controller
@RequestMapping("teams")
public class TeamController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    private TeamService teamService;

    @RequestMapping(value = "/new")
    public String newTeam(Model model) {
        return "newTeam";
    }

    @RequestMapping(value = "/manage")
    public String showMyPlacesList(Model model) {
        //List<Event> events = eventService.findAll();
        //model.addAttribute("eventsFound", events);
        return "teamsManage";
    }

    @RequestMapping(value = "/{teamID}")
    public String showTeamProfile(@PathVariable("teamID") Long teamID,

                                  Model model)
    {
        Team team = teamService.findById(teamID);
        model.addAttribute("team", team);
        return("team");
    }

//    public String search(
//            @RequestParam("query") String query,
//            Model model) {
//
//        List<Team> teams = teamService.findByNameContaining(query);
//        model.addAttribute("eventsFound", teams);
//
//        return "eventsSearch";
//    }
}

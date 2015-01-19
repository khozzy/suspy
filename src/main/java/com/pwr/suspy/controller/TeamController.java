package com.pwr.suspy.controller;

import com.pwr.suspy.domain.Team;
import com.pwr.suspy.domain.User;
import com.pwr.suspy.dto.AddTeamForm;
import com.pwr.suspy.service.TeamService;
import com.pwr.suspy.service.UserService;
import com.pwr.suspy.util.MyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newTeam(Model model) {
        model.addAttribute("addTeamForm", new AddTeamForm());
        return "newTeam";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String newTeam(@ModelAttribute("addTeamForm") @Valid AddTeamForm addTeamForm,
                          BindingResult result,
                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors() || addTeamForm.getName().isEmpty()) {
            MyUtil.flash(redirectAttributes, "failure", "errorTryAgain");
            return "newTeam";
        }

        logger.info(addTeamForm.toString());
        final Team team = teamService.createNewTeam(addTeamForm.getName());
        MyUtil.flash(redirectAttributes, "success", "team.added");
        return "redirect:/";
    }

    @RequestMapping(value = "/manage")
    public String showMyTeams(Model model) {
        //List<Event> events = eventService.findAll();
        //model.addAttribute("eventsFound", events);
        return "teamsManage";
    }

    @RequestMapping(value = "/{teamID}")
    public String showTeamProfile(@PathVariable("teamID") Long teamID,
                                  Model model,
                                  RedirectAttributes redirectAttributes)
    {
        Team team = teamService.findById(teamID);
        if(team == null || !team.isDeleted()) {
            model.addAttribute("team", team);
            return ("team");
        } else {
            MyUtil.flash(redirectAttributes, "danger", "team.error");
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/{teamID}/join")
    public String joinTeam(@PathVariable("teamID") Long teamID,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        Team team = teamService.findById(teamID);
        User user = userService.findById(MyUtil.getSessionUser().getId());

        if (team == null || user.getTeams().contains(team)) {
            MyUtil.flash(redirectAttributes, "danger", "team.error");
            return "redirect:/";
        }
        MyUtil.flash(redirectAttributes, "success", "team.join");
        teamService.addUserToTeam(team, user);
        return "redirect:/teams/" + teamID;
    }

    @RequestMapping(value = "/{teamID}/leave")
    public String leaveTeam(@PathVariable("teamID") Long teamID,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        Team team = teamService.findById(teamID);
        User user = userService.findById(MyUtil.getSessionUser().getId());

        if (team == null || !user.getTeams().contains(team)) {
            MyUtil.flash(redirectAttributes, "danger", "team.error");
            return "redirect:/";
        }

        if (teamService.removeUserFromTeam(team, user)) {
            MyUtil.flash(redirectAttributes, "success", "team.leave");
            return "redirect:/teams/" + teamID;
        } else {
            MyUtil.flash(redirectAttributes, "danger", "team.error");
            return "redirect://";
        }
    }

    @RequestMapping(value = "/{teamID}/delete")
    public String deleteTeam(@PathVariable("teamID") Long teamID,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        Team team = teamService.findById(teamID);
        User user = userService.findById(MyUtil.getSessionUser().getId());

        if (team == null && team.getLeader().getId() != user.getId()) {
            MyUtil.flash(redirectAttributes, "danger", "team.error");
            return "redirect:/";
        }

        teamService.removeTeam(team);
        MyUtil.flash(redirectAttributes, "success", "team.deleted");
        return "redirect:/teams/manage";
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

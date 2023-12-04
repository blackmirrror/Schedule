package ru.blackmirrror.schedule.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.blackmirrror.schedule.models.*;
import ru.blackmirrror.schedule.services.DirectionService;
import ru.blackmirrror.schedule.services.GroupService;
import ru.blackmirrror.schedule.services.SubjectService;
import ru.blackmirrror.schedule.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class ControlController {

    private final GroupService groupService;
    private final DirectionService directionService;
    private final UserService userService;
    private final SubjectService subjectService;
    @GetMapping("/control")
    public String control(Principal principal,
                          Model model) {
        List<Group> groups = groupService.findAll();
        List<Direction> directions = directionService.findAll();
        List<Subject> subjects = subjectService.findAll();
        User user = userService.getUserByPrincipal(principal);

        model.addAttribute("groups", groups);
        model.addAttribute("directions", directions);
        model.addAttribute("subjects", subjects);
        model.addAttribute("user", user);
        return "control";
    }

    @PostMapping("/control/createDirection")
    public String createDirection(Direction direction) {
        directionService.createDirection(direction);
        return "redirect:/control";
    }

    @PostMapping("/control/createGroup")
    public String createGroup(
            @RequestParam("directionId") Long directionId,
            Group group) {
        Direction direction = directionService.findById(directionId);
        group.setDirection(direction);
        groupService.createGroup(group);
        return "redirect:/control";
    }

    @PostMapping("/control/createSubject")
    public String createSubject(
            Subject subject) {
        subjectService.createSubject(subject);
        return "redirect:/control";
    }
}

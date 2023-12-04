package ru.blackmirrror.schedule.controllers;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.blackmirrror.schedule.models.Group;
import ru.blackmirrror.schedule.models.Role;
import ru.blackmirrror.schedule.models.Subject;
import ru.blackmirrror.schedule.models.User;
import ru.blackmirrror.schedule.services.GroupService;
import ru.blackmirrror.schedule.services.SubjectService;
import ru.blackmirrror.schedule.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final GroupService groupService;
    private final SubjectService subjectService;

    @GetMapping("/login")
    public String login(Principal principal, Model model) {
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/registrationEndStudent")
    public String registrationEndStudent(
            @RequestParam Long userId,
            @RequestParam("groupId") Long groupId, Model model) {
        Group group = groupService.findById(groupId);
        User user = userService.getUserById(userId);
        userService.setGroupStudent(user, group);
//        user.setChecked(false);

        return "redirect:/profile";
    }

    @PostMapping("/registrationEndTeacher")
    public String registrationEndTeacher(
            @RequestParam Long userId,
            @RequestParam(value = "selectedSubjects", required = false) List<Long> selectedSubjects,
            Model model) {
        User user = userService.getUserById(userId);
        userService.setSubjectsTeacher(userId, selectedSubjects);
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String profile(Principal principal,
                          Model model) {
        User user = userService.getUserByPrincipal(principal);
        List<Group> groups = groupService.findAll();
        List<Subject> subjects = subjectService.findAll();

        model.addAttribute("user", user);
        model.addAttribute("groups", groups);
        model.addAttribute("subjects", subjects);
        return "profile";
    }

    @GetMapping("/registration")
    public String registration(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "registration";
    }


    @PostMapping("/registration")
    public String createUser(@RequestParam("role") String role, User user, Model model) {
        if ("STUDENT".equals(role)) {
            user.getRoles().add(Role.STUDENT);
        } else if ("TEACHER".equals(role)) {
            user.getRoles().add(Role.TEACHER);
        }
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует");
            return "registration";
        }
        return "redirect:/login";
    }
}

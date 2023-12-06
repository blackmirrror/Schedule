package ru.blackmirrror.schedule.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.blackmirrror.schedule.models.*;
import ru.blackmirrror.schedule.services.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ScheduleController {

    private final UserService userService;
    private final GroupService groupService;
    private final SubjectService subjectService;
    private final ClassroomService classroomService;
    private final ScheduleService scheduleService;
    private final String TAG_ALL_RIGHT = "ok";

    @GetMapping("/")
    public String base(Principal principal, Model model) {
        return schedule(principal, model);
    }

    @GetMapping("/schedule")
    public String schedule(
            Principal principal,
            Model model
    ) {
        User user = userService.getUserByPrincipal(principal);
        List<Group> groups = groupService.findAll();
        List<Subject> subjects = subjectService.findAll();
        List<User> teachers = userService.getTeachers();
        List<Classroom> classrooms = classroomService.findAll();
        TextFormatter textFormatter = new TextFormatter();

        List<Schedule> schedules;
        if (user.isStudent()) {
            schedules = scheduleService.findAllByGroupAndSemester(user.getGroup(), 5);
            model.addAttribute("schedules", schedules);
        }
        if (user.isTeacher()) {
            schedules = scheduleService.findAllByTeacherAndSemester(user, 5);
            model.addAttribute("schedules", schedules);
        }

        model.addAttribute("user", user);
        model.addAttribute("groups", groups);
        model.addAttribute("subjects", subjects);
        model.addAttribute("teachers", teachers);
        model.addAttribute("classrooms", classrooms);
        model.addAttribute("textFormatter", textFormatter);
        return "schedule";
    }

    @PostMapping("/schedule/create")
    public String createSchedule(
            Principal principal,
            @RequestParam("groupId") Long groupId,
            @RequestParam("subjectId") Long subjectId,
            @RequestParam("teacherId") Long teacherId,
            @RequestParam("classroomId") Long classroomId,
            Schedule schedule,
            Model model) {

        Group group = groupService.findById(groupId);
        Subject subject = subjectService.findById(subjectId);
        User teacher = userService.getUserById(teacherId);
        Classroom classroom = classroomService.findById(classroomId);

        schedule.setClassroom(classroom);
        schedule.setTeacher(teacher);
        schedule.setSubject(subject);
        schedule.setGroup(group);

        String message = checkFields(schedule);
        if (message.equals(TAG_ALL_RIGHT)) {
            scheduleService.createSchedule(schedule);
            return "redirect:/schedule";
        }
        else {
            model.addAttribute("errorMessage", message);
            return schedule(principal, model);
        }
    }

    private String checkFields(Schedule schedule) {
        if (schedule.getPair() < 1 || schedule.getPair() > 7)
            return "Введите номер пары от 1 до 7";
        else if (schedule.getSemester() < 1 || schedule.getSemester() > 8)
            return "Введите номер семестра от 1 до 8";
        else if (schedule.getDayOfWeek() < 1 || schedule.getDayOfWeek() > 6)
            return "Введите день недели от 1 до 6";
        else if (scheduleService.findScheduleByClassroom(
                schedule.getPair(),
                schedule.getDayOfWeek(),
                schedule.getClassroom()
        ) != null) return "Аудитория занята в это время";
        else if (scheduleService.findScheduleByGroup(
                schedule.getPair(),
                schedule.getDayOfWeek(),
                schedule.getGroup()
        ) != null) return "Группа занята в это время";
        else if (scheduleService.findScheduleByTeacher(
                schedule.getPair(),
                schedule.getDayOfWeek(),
                schedule.getTeacher()
        ) != null) return "Преподаватель занят в это время";
        else if (!schedule.getTeacher().getSubjects().contains(schedule.getSubject()))
            return "Преподаватель не ведет данную дисциплину";
        else
            return TAG_ALL_RIGHT;
    }

    @GetMapping("/scheduleSome")
    public String scheduleSome(
            @RequestParam(name = "groupId", required = false) Long groupId,
            @RequestParam(name = "teacherId", required = false) Long teacherId,
            Principal principal,
            Model model
    ) {
        if (groupId != null) {
            Group group = groupService.findById(groupId);
            List<Schedule> schedules = scheduleService.findAllByGroupAndSemester(group, 5);
            model.addAttribute("schedules", schedules);
            model.addAttribute("group", group);
        }
        else if (teacherId != null) {
            User teacher = userService.getUserById(teacherId);
            List<Schedule> schedules = scheduleService.findAllByTeacherAndSemester(teacher, 5);
            model.addAttribute("schedules", schedules);
            model.addAttribute("teacher", teacher);
        }

        User user = userService.getUserByPrincipal(principal);
        List<Group> groups = groupService.findAll();
        List<User> teachers = userService.getTeachers();
        TextFormatter textFormatter = new TextFormatter();

        model.addAttribute("user", user);
        model.addAttribute("groups", groups);
        model.addAttribute("teachers", teachers);
        model.addAttribute("textFormatter", textFormatter);
        return "scheduleSome";
    }
}

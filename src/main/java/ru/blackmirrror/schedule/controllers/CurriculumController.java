package ru.blackmirrror.schedule.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.blackmirrror.schedule.models.Curriculum;
import ru.blackmirrror.schedule.models.Direction;
import ru.blackmirrror.schedule.models.Subject;
import ru.blackmirrror.schedule.services.CurriculumService;
import ru.blackmirrror.schedule.services.DirectionService;
import ru.blackmirrror.schedule.services.SubjectService;
import ru.blackmirrror.schedule.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CurriculumController {

    private final UserService userService;
    private final CurriculumService curriculumService;
    private final DirectionService directionService;
    private final SubjectService subjectService;

    @GetMapping("/curriculum")
    public String curriculum(Principal principal, Model model) {
        List<Curriculum> curriculums = curriculumService.findAll();
        List<Direction> directions = directionService.findAll();
        List<Subject> subjects = subjectService.findAll();

        model.addAttribute("user",userService.getUserByPrincipal(principal));
        model.addAttribute("curriculums", curriculums);
        model.addAttribute("directions", directions);
        model.addAttribute("subjects", subjects);
        return "curriculum";
    }

    @PostMapping("/curriculum/create")
    public String createCurriculum(
            @RequestParam("directionId") Long directionId,
            @RequestParam("subjectId") Long subjectId,
            Curriculum curriculum) {
        Direction direction = directionService.findById(directionId);
        Subject subject = subjectService.findById(subjectId);

        curriculum.setDirection(direction);
        curriculum.setSubject(subject);
        curriculumService.createCurriculum(curriculum);
        return "redirect:/curriculum";
    }
}

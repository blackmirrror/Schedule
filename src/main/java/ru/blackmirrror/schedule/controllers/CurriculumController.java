package ru.blackmirrror.schedule.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.blackmirrror.schedule.services.UserService;

import java.security.Principal;

@Controller
@Slf4j
@RequiredArgsConstructor
public class CurriculumController {

    private final UserService userService;

    @GetMapping("/")
    public String products(Principal principal, Model model) {
        model.addAttribute("user",userService.getUserByPrincipal(principal));
        return "profile";
    }
}

package ru.shutov.rateuteacher.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    @GetMapping()
    public String profilePage() {
        return "profile/moderation";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "profile/admin";
    }
}

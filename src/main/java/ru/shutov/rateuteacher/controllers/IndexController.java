package ru.shutov.rateuteacher.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.shutov.rateuteacher.entities.Admin;
import ru.shutov.rateuteacher.entities.Survey;
import ru.shutov.rateuteacher.enums.Role;
import ru.shutov.rateuteacher.services.AdminService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final AdminService adminService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("survey", new Survey());
        return "index/index";
    }

    @GetMapping("/faq")
    public String faq() {
        return "index/faq";
    }

    @GetMapping("/contacts")
    public String contacts(Model model) {
        List<Admin> admins = adminService.getAdmins();
        model.addAttribute("admins",
                admins.stream().filter(admin -> admin.getRole() == Role.ROLE_ADMIN).toList());
        model.addAttribute("moderators",
                admins.stream().filter(admin -> admin.getRole() == Role.ROLE_MODERATOR).toList());
        return "index/contacts";
    }
}

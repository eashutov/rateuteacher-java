package ru.shutov.rateuteacher.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.shutov.rateuteacher.entities.Survey;

@Controller
@RequiredArgsConstructor
public class IndexController {

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
        // TODO: get contacts from service
        return "index/contacts";
    }
}

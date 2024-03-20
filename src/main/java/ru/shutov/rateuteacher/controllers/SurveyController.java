package ru.shutov.rateuteacher.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shutov.rateuteacher.entities.Survey;

@Controller
@RequiredArgsConstructor
@RequestMapping("/survey")
public class SurveyController {

    @GetMapping()
    public String survey(@ModelAttribute Survey survey, Model model) {
        // проверить существует ли опрос с указанным кодом
        // проверить пройден ли он этим юзером (куки)
        // получить из сервиса опрос и отправить на представление
        // нужно отправить его полностью, вместе с вопросами, чтобы потом их отобразить
        return "survey/survey";
    }

    @PostMapping("/publish")
    public String publish(@ModelAttribute Survey survey, Model model) {
        // скорее всего неправильно
        // нужно разобраться в каком виде выводится форма на страницу survey
        // и потом исходя из этого как-то получать ответы (разобраться как)
        return "redirect:/survey/redirect";
    }

    @GetMapping("/redirect")
    public String redirect() {
        return "survey/redirect";
    }

}

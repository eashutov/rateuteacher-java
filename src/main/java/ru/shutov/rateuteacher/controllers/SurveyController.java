package ru.shutov.rateuteacher.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.shutov.rateuteacher.dto.SurveyFormDto;
import ru.shutov.rateuteacher.entities.Survey;
import ru.shutov.rateuteacher.request.SurveyRequest;
import ru.shutov.rateuteacher.services.SurveyService;


@Controller
@RequiredArgsConstructor
@RequestMapping("/survey")
public class SurveyController {
    private final SurveyService surveyService;

    @GetMapping()
    public String survey(@ModelAttribute SurveyRequest surveyRequest,
                         Model model,
                         HttpServletRequest request) {
        String code = surveyRequest.getCode();

        if (!surveyService.validateSurvey(request, code)) {
            return "redirect:/?wrong";
        }

        Survey survey = surveyService.getSurvey(code);
        System.err.println(survey.getId());
        SurveyFormDto form = surveyService.getSurveyForm(survey);
        model.addAttribute("form", form);
        model.addAttribute("survey", survey);

        return "survey/survey";
    }

    @PostMapping("/publish")
    public String publish(@ModelAttribute("form") SurveyFormDto answers, HttpServletResponse response) {
        surveyService.saveAnswers(answers);
        surveyService.setSurveyCompleted(response, answers.getCode());
        return "redirect:/survey/redirect";
    }

    @GetMapping("/redirect")
    public String redirect() {
        return "survey/redirect";
    }

}

package ru.shutov.rateuteacher.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.shutov.rateuteacher.dto.RatingDto;
import ru.shutov.rateuteacher.request.RatingRequest;
import ru.shutov.rateuteacher.services.QuestionnaireService;
import ru.shutov.rateuteacher.services.RatingService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ratings")
public class RatingController {
    private final RatingService ratingService;
    private final QuestionnaireService questionnaireService;

    @GetMapping()
    public String ratings(Model model) {
        model.addAttribute("standards", questionnaireService.getStandards());
        return "index/ratings";
    }

    @GetMapping("/get")
    @ResponseBody
    public List<RatingDto> getRatings(RatingRequest request) {
        return ratingService.getAverageRatingsByRequest(request);
    }
}

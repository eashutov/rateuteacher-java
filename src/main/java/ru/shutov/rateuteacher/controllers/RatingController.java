package ru.shutov.rateuteacher.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.shutov.rateuteacher.dto.ExtendedRatingDto;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ratings")
public class RatingController {
    @GetMapping()
    public String ratings() {
        return "index/ratings";
    }

    // TODO: метод, для возвращения рейтингов по критериям (переделать страницу)
    @GetMapping("/get")
    @ResponseBody
    public ExtendedRatingDto getRatings() {
        return null;
    }
}

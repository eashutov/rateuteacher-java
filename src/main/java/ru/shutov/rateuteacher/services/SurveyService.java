package ru.shutov.rateuteacher.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shutov.rateuteacher.dto.SurveyFormDto;
import ru.shutov.rateuteacher.entities.*;
import ru.shutov.rateuteacher.repositories.AnswerRepository;
import ru.shutov.rateuteacher.repositories.QuestionRepository;
import ru.shutov.rateuteacher.repositories.RatingRepository;
import ru.shutov.rateuteacher.repositories.SurveyRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveyService {
    private static final int WEEK_IN_SECONDS = 604800;
    private final SurveyRepository surveyRepository;
    private final RatingRepository ratingRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public boolean validateSurvey(HttpServletRequest request, String code) {
        return !isSurveyCompleted(request, code) && isSurveyExists(code);
    }

    private boolean isSurveyCompleted(HttpServletRequest request, String code) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return false;
        }
        return Arrays.stream(cookies).anyMatch(cookie -> cookie.getName().equals(code));
    }

    private boolean isSurveyExists(String code) {
        return surveyRepository.findByCodeAndUsagesGreaterThan(code, 0).isPresent();
    }

    // TODO: тут можно вставить кэширование (подумать над временем жизни)
    public Survey getSurvey(String code) {
        return surveyRepository.findByCode(code);
    }

    public SurveyFormDto getSurveyForm(Survey survey) {
        Map<UUID, Integer> answersMap = new HashMap<>();
        for (Part part : survey.getQuestionnaire().getParts()) {
            for (Question question : part.getQuestions()) {
                answersMap.put(question.getId(), null);
            }
        }
        return new SurveyFormDto(null, null, answersMap);
    }

    @Transactional
    public void saveAnswers(SurveyFormDto answers) {
        Survey survey = surveyRepository.getReferenceById(answers.getSurveyId());

        Rating rating = Rating.builder()
                .survey(survey)
                .completionDate(LocalDate.now())
                .build();

        ratingRepository.save(rating);

        Set<Answer> answerSet = answers.getAnswers().entrySet().stream()
                .map(entry -> Answer.builder()
                        .score(entry.getValue())
                        .question(questionRepository.getReferenceById(entry.getKey()))
                        .rating(rating)
                        .build())
                .collect(Collectors.toSet());

        answerRepository.saveAll(answerSet);

        rating.setAnswers(answerSet);
        survey.setUsages(survey.getUsages() - 1);
    }

    public void setSurveyCompleted(HttpServletResponse response, String code) {
        Cookie cookie = new Cookie(code, null);
        cookie.setPath("/survey");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(WEEK_IN_SECONDS);
        response.addCookie(cookie);
    }
}

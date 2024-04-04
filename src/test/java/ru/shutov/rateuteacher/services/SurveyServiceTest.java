package ru.shutov.rateuteacher.services;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.shutov.rateuteacher.entities.Survey;
import ru.shutov.rateuteacher.repositories.SurveyRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SurveyServiceTest {
    private static final String DEFAULT_CODE = "abcdef";

    @InjectMocks
    private SurveyService surveyService;

    @Mock
    private SurveyRepository surveyRepository;

    @Test
    public void validateShouldReturnFalseBecauseSurveyCompleted() {
        Cookie[] cookies = new Cookie[] {
                new Cookie(DEFAULT_CODE, "")
        };

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getCookies()).thenReturn(cookies);

        assertFalse(surveyService.validateSurvey(request, DEFAULT_CODE));
    }

    @Test
    public void validateShouldReturnFalseBecauseSurveyIsNotExists() {
        Cookie[] cookies = new Cookie[] {};

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getCookies()).thenReturn(cookies);
        when(surveyRepository.findByCodeAndUsagesGreaterThan(DEFAULT_CODE, 0))
                .thenReturn(Optional.empty());

        assertFalse(surveyService.validateSurvey(request, DEFAULT_CODE));
    }

    @Test
    public void validateShouldReturnTrue() {
        Cookie[] cookies = new Cookie[] {};
        Survey survey = Survey.builder()
                .usages(1)
                .code(DEFAULT_CODE)
                .build();

        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getCookies()).thenReturn(cookies);
        when(surveyRepository.findByCodeAndUsagesGreaterThan(DEFAULT_CODE, 0))
                .thenReturn(Optional.of(survey));

        assertTrue(surveyService.validateSurvey(request, DEFAULT_CODE));
    }
}

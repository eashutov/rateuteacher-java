package ru.shutov.rateuteacher.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shutov.rateuteacher.entities.Questionnaire;
import ru.shutov.rateuteacher.repositories.QuestionnaireRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionnaireService {
    private final QuestionnaireRepository questionnaireRepository;

    public List<String> getStandards() {
        List<Questionnaire> questionnaires = questionnaireRepository.findAll();
        return questionnaires.stream().map(Questionnaire::getStandard).distinct().toList();
    }

}

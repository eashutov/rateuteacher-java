package ru.shutov.rateuteacher.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shutov.rateuteacher.dto.AverageScoreDto;
import ru.shutov.rateuteacher.dto.RatingDto;
import ru.shutov.rateuteacher.entities.Answer;
import ru.shutov.rateuteacher.entities.Person;
import ru.shutov.rateuteacher.entities.Rating;
import ru.shutov.rateuteacher.repositories.RatingRepository;
import ru.shutov.rateuteacher.request.RatingRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RatingService {
    private static final int MINIMUM_COMPLETION_YEAR = 2000;
    private final RatingRepository ratingRepository;

    public List<RatingDto> getAverageRatingsByRequest(RatingRequest request) {
        List<Rating> ratings = ratingRepository.findAll();
        return ratings.stream()
                .filter(rating -> validate(rating, request))
                .collect(Collectors.groupingBy(rating -> rating.getSurvey().getTeacher().getPerson()))
                .entrySet().stream()
                .map(entry -> {
                    Person person = entry.getKey();
                    List<Rating> teacherRatings = entry.getValue();

                    Map<String, Double> averageScoresByPart = teacherRatings.stream()
                            .flatMap(rating -> calculateAverageRatings(rating).stream())
                            .collect(Collectors.groupingBy(AverageScoreDto::getPart,
                                    Collectors.averagingDouble(AverageScoreDto::getAverageScore)));

                    List<AverageScoreDto> averageRatings = averageScoresByPart.entrySet().stream()
                            .map(e -> new AverageScoreDto(e.getKey(), e.getValue()))
                            .toList();

                    return RatingDto.builder()
                            .lastName(person.getLastName())
                            .firstName(person.getFirstName())
                            .patronymic(person.getPatronymic())
                            .standard(teacherRatings.get(0).getSurvey().getQuestionnaire().getStandard())
                            .discipline(teacherRatings.get(0).getSurvey().getDiscipline().getName())
                            .ratings(averageRatings)
                            .build();
                })
                .toList();
    }

    private List<AverageScoreDto> calculateAverageRatings(Rating rating) {
        Set<Answer> answers = rating.getAnswers();

        Map<String, Double> averageScores = answers.stream()
                .collect(Collectors.groupingBy(
                        answer -> answer.getQuestion().getPart().getName(),
                        Collectors.averagingInt(Answer::getScore)
                ));

        return averageScores.entrySet().stream()
                .map(entry -> new AverageScoreDto(entry.getKey(), entry.getValue()))
                .toList();
    }

    private boolean validate(Rating rating, RatingRequest request) {
        String standard = rating.getSurvey().getQuestionnaire().getStandard();

        if (!standard.equals(request.getStandard())) {
            return false;
        }

        Integer requestCompletionYear = request.getCompletionYear();
        if (requestCompletionYear != null && requestCompletionYear >= MINIMUM_COMPLETION_YEAR
                && requestCompletionYear <= LocalDate.now().getYear()) {
            LocalDate completionDate = rating.getCompletionDate();
            if (completionDate.getYear() != requestCompletionYear) {
                return false;
            }
        }

        Person person = rating.getSurvey().getTeacher().getPerson();

        if (request.getFirstName() != null) {
            String firstName = person.getFirstName();
            if (!request.getFirstName().equals(firstName)) {
                return false;
            }
        }

        if (request.getLastName() != null) {
            String lastName = person.getLastName();
            if (!request.getLastName().equals(lastName)) {
                return false;
            }
        }

        if (request.getPatronymic() != null) {
            String patronymic = person.getPatronymic();
            if (!request.getPatronymic().equals(patronymic)) {
                return false;
            }
        }

        return true;
    }
}

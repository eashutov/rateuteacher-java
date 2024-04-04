package ru.shutov.rateuteacher.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shutov.rateuteacher.entities.Survey;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, UUID> {
    Optional<Survey> findByCodeAndUsagesGreaterThan(String code, int usages);

    @EntityGraph(value = "survey-teacher-questions")
    Survey findByCode(String code);
}

package ru.shutov.rateuteacher.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class SurveyFormDto {
    private UUID surveyId;
    private String code;
    private Map<UUID, Integer> answers;
}

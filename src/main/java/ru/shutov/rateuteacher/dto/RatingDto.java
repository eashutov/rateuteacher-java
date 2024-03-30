package ru.shutov.rateuteacher.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto {
    private String lastName;
    private String firstName;
    private String patronymic;
    private String discipline;
    private String standard;
    private List<AverageScoreDto> ratings;
}

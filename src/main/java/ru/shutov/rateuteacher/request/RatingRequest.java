package ru.shutov.rateuteacher.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingRequest {
    private Integer completionYear;
    private String standard;
    private String lastName;
    private String firstName;
    private String patronymic;
}

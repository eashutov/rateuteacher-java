package ru.shutov.rateuteacher.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Questionnaire {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "standard")
    private String standard;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "questionnaire")
    private Set<Question> questions;

    @OneToMany(mappedBy = "questionnaire")
    private Set<Survey> surveys;
}

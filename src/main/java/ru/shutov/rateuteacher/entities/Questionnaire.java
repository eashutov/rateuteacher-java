package ru.shutov.rateuteacher.entities;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "standard")
    private String standard;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "questionnaire")
    private Set<Part> parts;

    @OneToMany(mappedBy = "questionnaire")
    private Set<Survey> surveys;
}

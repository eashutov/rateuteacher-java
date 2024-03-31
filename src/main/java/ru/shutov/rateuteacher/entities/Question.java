package ru.shutov.rateuteacher.entities;

import jakarta.persistence.*;
import lombok.*;
import ru.shutov.rateuteacher.enums.QuestionType;

import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Question {
    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "questionnaire", referencedColumnName = "id")
    private Questionnaire questionnaire;

    @Column(name = "part")
    private String part;

    @Column(name = "question")
    private String question;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @OneToMany(mappedBy = "question")
    private Set<Answer> answers;
}

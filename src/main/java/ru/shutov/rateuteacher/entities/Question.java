package ru.shutov.rateuteacher.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.shutov.rateuteacher.enums.QuestionType;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    private Set<Answer> answers = new HashSet<>();
}

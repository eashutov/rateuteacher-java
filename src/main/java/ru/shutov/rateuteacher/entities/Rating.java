package ru.shutov.rateuteacher.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@NamedEntityGraph(
        name = "extended-rating",
        attributeNodes = {
                @NamedAttributeNode(value = "survey", subgraph = "survey-teacher-questionnaire-discipline"),
                @NamedAttributeNode(value = "answers", subgraph = "answer-question"),
        },
        subgraphs = {
                @NamedSubgraph(name = "answer-question", attributeNodes = {
                        @NamedAttributeNode(value = "question")
                }),
                @NamedSubgraph(name = "survey-teacher-questionnaire-discipline", attributeNodes = {
                        @NamedAttributeNode(value = "teacher", subgraph = "teacher-person"),
                        @NamedAttributeNode(value = "questionnaire"),
                        @NamedAttributeNode(value = "discipline")
                }),
                @NamedSubgraph(name = "teacher-person", attributeNodes = {
                        @NamedAttributeNode(value = "person")
                })
        }
)
public class Rating {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "completion_date")
    @Temporal(TemporalType.DATE)
    private LocalDate completionDate;

    @ManyToOne
    @JoinColumn(name = "survey", referencedColumnName = "id")
    private Survey survey;

    @OneToMany(mappedBy = "rating")
    private Set<Answer> answers;
}

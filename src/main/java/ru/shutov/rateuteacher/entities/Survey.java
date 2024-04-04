package ru.shutov.rateuteacher.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
        name = "survey-teacher-questions",
        attributeNodes = {
                @NamedAttributeNode(value = "questionnaire", subgraph = "questionnaire-parts"),
                @NamedAttributeNode(value = "teacher", subgraph = "teacher-person"),
                @NamedAttributeNode(value = "discipline")
        },
        subgraphs = {
                @NamedSubgraph(name = "questionnaire-parts", attributeNodes = {
                        @NamedAttributeNode(value = "parts", subgraph = "part-questions")
                }),
                @NamedSubgraph(name = "teacher-person", attributeNodes = {
                        @NamedAttributeNode(value = "person", subgraph = "person-department")
                }),
                @NamedSubgraph(name = "person-department", attributeNodes = {
                        @NamedAttributeNode(value = "department")
                }),
                @NamedSubgraph(name = "part-questions", attributeNodes = {
                        @NamedAttributeNode(value = "questions")
                })
        }
)
public class Survey {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "code")
    private String code;

    @Column(name = "usages")
    @Min(1)
    private int usages;

    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private LocalDate creationDate;

    @Column(name = "study_group")
    private String studyGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher", referencedColumnName = "id")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin", referencedColumnName = "id")
    private Admin admin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionnaire", referencedColumnName = "id")
    private Questionnaire questionnaire;

    @OneToMany(mappedBy = "survey")
    private Set<Rating> ratings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discipline", referencedColumnName = "id")
    private Discipline discipline;
}

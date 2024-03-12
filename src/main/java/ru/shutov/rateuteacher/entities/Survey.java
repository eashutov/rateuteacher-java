package ru.shutov.rateuteacher.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Survey {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "code")
    private String code;

    @Column(name = "usages")
    @Min(1)
    private int usages;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "study_group")
    private String studyGroup;

    @ManyToOne
    @JoinColumn(name = "teacher", referencedColumnName = "id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "admin", referencedColumnName = "id")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "questionnaire", referencedColumnName = "id")
    private Questionnaire questionnaire;

    @OneToMany(mappedBy = "survey")
    private Set<Rating> ratings = new HashSet<>();
}

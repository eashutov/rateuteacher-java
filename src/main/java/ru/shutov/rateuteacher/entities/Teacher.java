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
public class Teacher {
    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "person", referencedColumnName = "id")
    private Person person;

    @Column(name = "experience")
    private int experience;

    @ManyToMany
    @JoinTable(
            name = "teacher_discipline",
            joinColumns = { @JoinColumn(name = "teacher") },
            inverseJoinColumns = { @JoinColumn(name = "discipline") }
    )
    private Set<Discipline> disciplines;

    @OneToMany(mappedBy = "teacher")
    private Set<Survey> surveys;
}

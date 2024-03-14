package ru.shutov.rateuteacher.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
    private Set<Discipline> disciplines = new HashSet<>();

    @OneToMany(mappedBy = "teacher")
    private Set<Survey> surveys = new HashSet<>();
}

package ru.shutov.rateuteacher.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Person {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "patronymic")
    private String patronymic;

    @ManyToOne
    @JoinColumn(name = "department", referencedColumnName = "id")
    private Department department;

    @OneToMany(mappedBy = "person")
    private Set<Teacher> teachers = new HashSet<>();

    @OneToMany(mappedBy = "person")
    private Set<Admin> admins;

    public String getFullName() {
        return lastName + " " + firstName + " " + patronymic;
    }
}

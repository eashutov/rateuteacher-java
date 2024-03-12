package ru.shutov.rateuteacher.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.shutov.rateuteacher.enums.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Admin {
    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "person", referencedColumnName = "id")
    private Person person;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    // TODO: что делать с этими полями?
    private String login;
    private String password;

    @OneToOne
    @JoinColumn(name = "contact", referencedColumnName = "id")
    private Contact contact;

    @OneToMany(mappedBy = "admin")
    private Set<Survey> surveys = new HashSet<>();
}

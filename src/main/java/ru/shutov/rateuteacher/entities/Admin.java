package ru.shutov.rateuteacher.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import ru.shutov.rateuteacher.enums.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
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

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "office")
    private String office;

    @Column(name = "office_visible")
    private boolean officeVisible;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "email_visible")
    private boolean emailVisible;

    @Column(name = "phone")
    private String phone;

    @Column(name = "phone_visible")
    private boolean phoneVisible;

    @Column(name = "photo")
    private String photo;

    @OneToMany(mappedBy = "admin")
    private Set<Survey> surveys;
}

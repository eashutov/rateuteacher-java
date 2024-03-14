package ru.shutov.rateuteacher.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Contact {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "office")
    private String office;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "photo")
    private String photo;

    @OneToOne(mappedBy = "contact")
    private Admin admin;
}

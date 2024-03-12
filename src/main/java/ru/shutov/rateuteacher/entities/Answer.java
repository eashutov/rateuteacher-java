package ru.shutov.rateuteacher.entities;

import jakarta.persistence.*;
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
public class Answer {
    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "rating", referencedColumnName = "id")
    private Rating rating;

    @ManyToOne
    @JoinColumn(name = "question", referencedColumnName = "id")
    private Question question;

    @Column(name = "answer")
    private String answer;
}

package ru.shutov.rateuteacher.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Answer {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "rating", referencedColumnName = "id")
    private Rating rating;

    @ManyToOne
    @JoinColumn(name = "question", referencedColumnName = "id")
    private Question question;

    @Column(name = "score")
    private int score;
}

package ru.shutov.rateuteacher.entities;

import jakarta.persistence.*;
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
public class Rating {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "completion_date")
    private Date completionDate;

    @ManyToOne
    @JoinColumn(name = "survey", referencedColumnName = "id")
    private Survey survey;

    @OneToMany(mappedBy = "rating")
    private Set<Answer> answers = new HashSet<>();
}

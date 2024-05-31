package dev.potgon.spanishIdioms.api.idiom;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "idiom")
public class Idiom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "completed")
    private boolean completed;
    @Column(name = "turn")
    private int turn;
    @Column(name = "times_guessed")
    private int timesGuessed;

}

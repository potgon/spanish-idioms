package dev.potgon.spanishIdioms.api.guess;


import dev.potgon.spanishIdioms.api.idiom.Idiom;
import dev.potgon.spanishIdioms.api.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "guess")
public class Guess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Idiom idiom;
    @Column(name = "text")
    private String guessText;
    @Column(name = "rating")
    private int rating;

    public Guess(User user, Idiom idiom, String guessText) {
        this.user = user;
        this.idiom = idiom;
        this.guessText = guessText;
    }
}

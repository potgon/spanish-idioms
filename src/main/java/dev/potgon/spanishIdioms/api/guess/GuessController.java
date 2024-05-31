package dev.potgon.spanishIdioms.api.guess;

import dev.potgon.spanishIdioms.api.gpt.GPTService;
import dev.potgon.spanishIdioms.api.idiom.Idiom;
import dev.potgon.spanishIdioms.api.idiom.IdiomService;
import dev.potgon.spanishIdioms.api.user.User;
import dev.potgon.spanishIdioms.api.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${API}/guess")
@RequiredArgsConstructor
public class GuessController {

    private final GuessService guessService;
    private final GPTService gptService;
    private final UserService userService;
    private final IdiomService idiomService;

    @PostMapping
    public ResponseEntity<Guess> makeGuess(@RequestParam String username, @RequestParam Integer idiomId, @RequestParam String guessText) {
        Optional<User> userOptional = userService.getUserByUsername(username);
        Optional<Idiom> idiomOptional = idiomService.findById(idiomId);

        if (idiomOptional.isEmpty() || userOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User user = userOptional.get();
        Idiom idiom = idiomOptional.get();
        Guess guess = new Guess(user, idiom, guessText);
        String ratingString;

        try {
            ratingString = gptService.generate(idiom.getName(), guessText);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }

        int rating;
        try {
            rating = Integer.parseInt(ratingString.trim());
        } catch (NumberFormatException e) {
            rating = 0;
        }

        guess.setRating(rating);

        user.setScore(user.getScore() + rating);
        userService.saveUser(user);

        guessService.saveGuess(guess);

        idiomService.incrementGuessCountAndCheckCompletion(idiom);

        return ResponseEntity.ok(guess);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<Guess>> getGuesses(@PathVariable String username) {
        List<Guess> guesses = guessService.getGuessesByUser(username);
        return ResponseEntity.ok(guesses);
    }

}

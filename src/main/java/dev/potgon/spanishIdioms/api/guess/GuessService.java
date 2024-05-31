package dev.potgon.spanishIdioms.api.guess;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuessService {

    private final GuessRepository guessRepository;

    public void saveGuess(Guess guess) {
        guessRepository.save(guess);
    }

    public List<Guess> getGuessesByUser(String username) {
        return guessRepository.findAllByUserUsername(username);
    }

}

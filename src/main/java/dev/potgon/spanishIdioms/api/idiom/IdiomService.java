package dev.potgon.spanishIdioms.api.idiom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IdiomService {

    private final IdiomRepository idiomRepository;

    public Optional<Idiom> findById(Integer id) {
        return idiomRepository.findById(id);
    }

    public Idiom getDailyIdiom() {
        return idiomRepository.findFirstByCompletedFalseOrderByTurnAsc();
    }

    public List<Idiom> getAllIdioms() {
        return idiomRepository.findAll();
    }

    public void incrementGuessCountAndCheckCompletion(Idiom idiom) {
        idiom.setTimesGuessed(idiom.getTimesGuessed() + 1);
        if (idiom.getTimesGuessed() >= 2) {
            idiom.setCompleted(true);
        }
        idiomRepository.save(idiom);
    }
}

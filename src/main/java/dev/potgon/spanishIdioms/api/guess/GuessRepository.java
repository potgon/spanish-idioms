package dev.potgon.spanishIdioms.api.guess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuessRepository extends JpaRepository<Guess,Integer> {

    List<Guess> findAllByUserUsername(String username);
}

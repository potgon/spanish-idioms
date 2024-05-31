package dev.potgon.spanishIdioms.api.idiom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdiomRepository extends JpaRepository<Idiom, Integer> {
    Idiom findFirstByCompletedFalseOrderByTurnAsc();
}

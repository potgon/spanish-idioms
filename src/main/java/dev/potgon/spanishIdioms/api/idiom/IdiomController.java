package dev.potgon.spanishIdioms.api.idiom;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${API}/idioms")
public class IdiomController {

    private final IdiomService idiomService;

    @GetMapping("/daily")
    public ResponseEntity<Idiom> getDailyIdiom() {
        Idiom dailyIdiom = idiomService.getDailyIdiom();
        return ResponseEntity.ok(dailyIdiom);
    }

    @GetMapping
    public ResponseEntity<List<Idiom>> getAllIdioms() {
        List<Idiom> idioms = idiomService.getAllIdioms();
        return ResponseEntity.ok(idioms);
    }
}

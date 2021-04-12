package be.joengenduvel.codenature.web;

import be.joengenduvel.codenature.game.Game;
import be.joengenduvel.codenature.game.GameManager;
import be.joengenduvel.codenature.web.models.GameResponse;
import be.joengenduvel.codenature.web.models.NewGameRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/games")
@AllArgsConstructor
public class GameController {
    private final GameManager gameManager;

    @GetMapping
    public List<GameResponse> listActiveGameIds(){
        return gameManager.getGames().stream().map(GameResponse::of).collect(Collectors.toList());
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<GameResponse> getGame(@PathVariable String id){
        Optional<Game> game = gameManager.getGame(UUID.fromString(id));
        return game.map(value -> ResponseEntity.ok(GameResponse.of(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> startNewGame(@RequestBody NewGameRequest newGameRequest){
        if(newGameRequest == null || newGameRequest.getName() == null)
            return ResponseEntity.badRequest().body("Name cannot be null");
        Game game = gameManager.newGame(newGameRequest.getName());
        return ResponseEntity.ok(GameResponse.of(game));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> stopGame(@PathVariable String id){
        gameManager.endGame(UUID.fromString(id));
        return ResponseEntity.accepted().build();
    }
}

package be.joengenduvel.codenature.web;

import be.joengenduvel.codenature.game.Game;
import be.joengenduvel.codenature.game.GameManager;
import be.joengenduvel.codenature.game.Player;
import be.joengenduvel.codenature.web.models.JoinResponse;
import be.joengenduvel.codenature.web.models.KeyBinding;
import be.joengenduvel.codenature.web.models.PlayerResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping("/games/{gameId}/players")
@AllArgsConstructor
public class PlayerController {

    private final GameManager gameManager;

    @GetMapping
    public ResponseEntity<List<PlayerResponse>> listPlayers(@PathVariable String gameId) {
        UUID gameUUID = UUID.fromString(gameId);
        Optional<Game> game = gameManager.getGame(gameUUID);
        return game.map(value -> ResponseEntity.ok(value.getPlayers().stream().map(PlayerResponse::of).collect(Collectors.toList()))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/{playerId}")
    public ResponseEntity<PlayerResponse> getPlayer(@PathVariable String gameId, @PathVariable String playerId) {
        UUID gameUUID = UUID.fromString(gameId);
        UUID playerUUID = UUID.fromString(playerId);
        Optional<Game> game = gameManager.getGame(gameUUID);
        if (game.isPresent()) {
            Optional<Player> player = game.get().getPlayer(playerUUID);
            if (player.isPresent())
                return ResponseEntity.accepted().body(PlayerResponse.of(player.get()));
        }
        return ResponseEntity.notFound().build();

    }


    @PostMapping
    public ResponseEntity<JoinResponse> join(@PathVariable String gameId) {
        UUID gameUUID = UUID.fromString(gameId);
        Optional<Game> game = gameManager.getGame(gameUUID);
        return game.map(value -> ResponseEntity.ok().body(new JoinResponse(value.newPlayer()))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{playerId}")
    public ResponseEntity<String> leave(@PathVariable String gameId, @PathVariable String playerId) {
        UUID gameUUID = UUID.fromString(gameId);
        UUID playerUUID = UUID.fromString(playerId);
        Optional<Game> game = gameManager.getGame(gameUUID);
        if (game.isPresent()) {
            game.get().removePlayer(playerUUID);
            return ResponseEntity.accepted().body("Goodbye");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/{playerId}", consumes = "application/json")
    public String move(@PathVariable String gameId, @PathVariable String playerId, @RequestBody KeyBinding keyBinding) {
        try {
            UUID gameUUID = UUID.fromString(gameId);
            UUID playerUUID = UUID.fromString(playerId);

            Optional<Game> game = gameManager.getGame(gameUUID);
            if (game.isPresent()) {
                Optional<Player> player = game.get().getPlayer(playerUUID);
                if (player.isPresent()) {
                    player.get().move(keyBinding);
                    return "ok";
                }
            }
        } catch (IllegalArgumentException e) {
            log.warn("{} or {} is not a uuid: {}", gameId, playerId, e.getMessage());
        }
        return "nok";
    }
}

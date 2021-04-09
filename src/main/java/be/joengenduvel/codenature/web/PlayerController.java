package be.joengenduvel.codenature.web;

import be.joengenduvel.codenature.game.Game;
import be.joengenduvel.codenature.game.Player;
import be.joengenduvel.codenature.web.models.KeyBinding;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/player")
@AllArgsConstructor
public class PlayerController {

    private final Game game;

    @PostMapping(value = "/{id}", consumes = "application/json")
    public String move(@PathVariable String id,  @RequestBody KeyBinding keyBinding){
        try {
            UUID playerId = UUID.fromString(id);
            Optional<Player> player = game.getPlayer(playerId);
            if (player.isPresent()) {
                player.get().move(keyBinding);
                return "ok";
            }
        }catch (IllegalArgumentException e){
            log.warn("{} is not a uuid", id);
        }
        return "nok";
    }
}

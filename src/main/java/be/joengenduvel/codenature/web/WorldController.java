package be.joengenduvel.codenature.web;

import be.joengenduvel.codenature.game.Game;
import be.joengenduvel.codenature.game.GameManager;
import be.joengenduvel.codenature.web.models.WorldRepresentation;
import be.joengenduvel.codenature.world.World;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/games/{gameId}")
public class WorldController {

    private final GameManager gameManager;

    @RequestMapping(value = "/world", produces = "application/json")
    public ResponseEntity<WorldRepresentation> getCompleteWorld(@PathVariable String gameId){
        UUID gameUUID = UUID.fromString(gameId);
        Optional<Game> game = gameManager.getGame(gameUUID);
        if (game.isPresent()){
            World world = game.get().getWorld();
            world.tick();
            return ResponseEntity.ok().body(WorldRepresentation.of(world));
        }
        return ResponseEntity.notFound().build();
    }


    @RequestMapping(value = "/players/{playerId}/world", produces = "application/json")
    public ResponseEntity<WorldRepresentation> getPlayerWorld(@PathVariable String gameId, @PathVariable String playerId){
        UUID gameUUID = UUID.fromString(gameId);
        UUID playerUUID = UUID.fromString(playerId);
        Optional<Game> game = gameManager.getGame(gameUUID);
        if (game.isPresent()){
            World world = game.get().getWorld();
            world.tick();
            //TODO: transform world
            return ResponseEntity.ok().body(WorldRepresentation.of(world));
        }
        return ResponseEntity.notFound().build();
    }
}

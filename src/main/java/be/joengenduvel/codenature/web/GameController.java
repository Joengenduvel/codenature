package be.joengenduvel.codenature.web;

import be.joengenduvel.codenature.game.Game;
import be.joengenduvel.codenature.web.models.JoinResponse;
import be.joengenduvel.codenature.world.World;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/game")
@AllArgsConstructor
public class GameController {

    private final Game game;

    @PostMapping
    public JoinResponse join(){
        return new JoinResponse(game.newPlayer());
    }

    @DeleteMapping
    public String leave(@RequestBody UUID uuid){
        return game.removePlayer(uuid);
    }
}

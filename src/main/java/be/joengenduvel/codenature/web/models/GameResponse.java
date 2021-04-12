package be.joengenduvel.codenature.web.models;

import be.joengenduvel.codenature.game.Game;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
public class GameResponse {
    String id;
    String name;
    ZonedDateTime createdAt;

    public static GameResponse of(Game game){
        return new GameResponse(
                game.getId().toString(),
                game.getName(),
                game.getCreationTime()
        );
    }
}

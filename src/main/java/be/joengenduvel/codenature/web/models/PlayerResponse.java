package be.joengenduvel.codenature.web.models;

import be.joengenduvel.codenature.game.Player;
import lombok.Value;

import java.util.UUID;

@Value
public class PlayerResponse {
    UUID id;
    String name;

    public static PlayerResponse of(Player player) {
        return new PlayerResponse(player.getId(), player.getName());
    }
}

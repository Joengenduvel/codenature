package be.joengenduvel.codenature.game;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class GameManager {

    @Getter
    private List<Game> games;

    public GameManager() {
        games = new ArrayList<>(0);
    }

    public Game newGame(String name) {
        Game game = new Game(name);
        games.add(game);
        return game;
    }

    public boolean endGame(UUID uuid) {
        Optional<Game> game = games.stream().filter(g -> g.getId().equals(uuid)).findFirst();
        return games.remove(game.orElse(null));
    }

    public void cleanGamesOlderThan(ZonedDateTime twoHoursAgo) {
        games.forEach(g -> {
            if(g.getCreationTime().isBefore(twoHoursAgo)){
                //TODO: make this less abrupt
                games.remove(g);
            }
        });
    }

    public Optional<Game> getGame(UUID gameUUID) {
        return games.stream().filter(g -> g.getId().equals(gameUUID)).findFirst();
    }
}

package be.joengenduvel.codenature.game;

import be.joengenduvel.codenature.math.Vector2D;
import be.joengenduvel.codenature.world.Sprite;
import be.joengenduvel.codenature.world.World;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Game {
    @Getter
    private final UUID id;
    @Getter
    private ZonedDateTime creationTime;
    @Getter
    private final String name;
    @Getter
    private final World world;

    private final Map<UUID, Player> players = new ConcurrentHashMap<>(0);

    public Game(String name) {
        this.id = UUID.randomUUID();
        this.creationTime = ZonedDateTime.now();
        this.world = new World();
        this.name = name;
    }

    //TODO: ask for players name
    public UUID newPlayer() {
        Player player = new Player(
                UUID.randomUUID(),
                "Player " + (players.size() + 1),
                new Sprite(
                        new Vector2D(players.size() * 10, players.size() * 10),
                        new Vector2D(players.size(), players.size()),
                        new Vector2D(0,0),
                        (long)(players.size()+1)*2,
                        new ArrayList<>()
                )
        );
        players.put(player.getId(),player);
        world.addSprite(player.getSprite());
        if(players.size()>0){
            //startTimer();
        }
        return player.getId();
    }

    public String removePlayer(UUID playerId) {
        players.remove(playerId);
        if(players.isEmpty()){
            //stopTimer();
        }
        return "ok";
    }

    public Optional<Player> getPlayer(UUID playerId) {
        return Optional.ofNullable(players.get(playerId));
    }

    public List<Player> getPlayers(){
        return new ArrayList<>(players.values());
    }
}

package be.joengenduvel.codenature.web.models;

import be.joengenduvel.codenature.world.PhysicsWorld;
import be.joengenduvel.codenature.world.World;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WorldRepresentation {
    private final long width;
    private final long height;
    private final List<SpriteRepresentation> players;

    public static WorldRepresentation of(World world){
        return new WorldRepresentation(
                (long)world.getSize().getX(),
                (long)world.getSize().getY(),
                world.getSprites().stream().map(SpriteRepresentation::of).collect(Collectors.toList())
        );
    }
}

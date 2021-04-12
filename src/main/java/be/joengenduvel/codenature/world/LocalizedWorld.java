package be.joengenduvel.codenature.world;

import be.joengenduvel.codenature.math.Vector2D;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class LocalizedWorld implements World {
    private final PhysicsWorld world;
    private final Vector2D size;
    private final Vector2D viewPosition;
    private final double viewAngle;

    @Override
    public Vector2D getSize() {
        return size;
    }

    public List<Sprite> getSprites() {
        return world.getSprites().stream().map(s ->{
            return new Sprite(
                    s.getPosition().substract(viewPosition).rotate(viewAngle).add(size.divide(2)),
                    s.getSpeed().rotate(viewAngle),
                    s.getAcceleration().rotate(viewAngle),
                    s.getMass(),
                    new ArrayList<>(0)
            );

                }).collect(Collectors.toList());
    }
}

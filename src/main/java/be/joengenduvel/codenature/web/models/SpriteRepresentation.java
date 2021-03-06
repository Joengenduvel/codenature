package be.joengenduvel.codenature.web.models;

import be.joengenduvel.codenature.math.Vector2D;
import be.joengenduvel.codenature.world.Sprite;
import lombok.Value;

@Value
public class SpriteRepresentation {
    Vector2D position;
    Vector2D speed;
    long mass;
    double angle;

    public static SpriteRepresentation of(Sprite sprite) {
        return new SpriteRepresentation(
                sprite.getPosition(),
                sprite.getSpeed(),
                sprite.getMass(),
                sprite.getAngle());
    }
}

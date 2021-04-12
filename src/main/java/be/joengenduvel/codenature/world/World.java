package be.joengenduvel.codenature.world;

import be.joengenduvel.codenature.math.Vector2D;

import java.util.List;

public interface World {
    Vector2D getSize();

    List<Sprite> getSprites();
}

package be.joengenduvel.codenature.game;

import be.joengenduvel.codenature.math.Vector2D;
import be.joengenduvel.codenature.web.models.KeyBinding;
import be.joengenduvel.codenature.world.Sprite;
import lombok.Value;

import java.util.UUID;

import static java.lang.Math.abs;

@Value
public class Player {
    public static final double NINETY_DEGREES = Math.PI / 2;
    UUID id;
    String name;
    Sprite sprite;

    public Sprite getSprite() {
        return sprite;
    }

    public void move(KeyBinding keyBinding){
        int x =0,y = 0;
        double rotationAngle = Math.PI/180;

        //map keys to force: 0 degrees = x-axis
        if(keyBinding.isDown()) x--;
        if(keyBinding.isUp()) x++;
        if(keyBinding.isLeft()) sprite.rotate(rotationAngle);
        if(keyBinding.isRight()) sprite.rotate(rotationAngle*-1);

        Vector2D force = new Vector2D(x,y);
        double movingAngle = sprite.getAngle();
        force = force.rotate(movingAngle);
        sprite.applyForce(force.normalize());
    }
}

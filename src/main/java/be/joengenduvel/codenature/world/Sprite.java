package be.joengenduvel.codenature.world;

import be.joengenduvel.codenature.math.Vector2D;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.round;

@AllArgsConstructor
@Getter
@Setter
@Slf4j
public class Sprite {
    private Vector2D position;
    private Vector2D speed;
    private Vector2D acceleration;
    private long mass;

    private List<Sprite> collidingWith;

    public void applyForce(Vector2D force) {
        acceleration = acceleration.add(force.divide(mass));
    }

    public void move(double timespan) {
        position = position.add(speed.times(timespan));
//        Vector2D deltaAcceleration = acceleration.times(timespan);
//        acceleration = acceleration.substract(deltaAcceleration);
//        if(acceleration.getMagnitude() < 0.000001)
//            acceleration = Vector2D.ZERO;
        speed = speed.add(acceleration);
        acceleration = Vector2D.ZERO;
    }

    public double getAngle() {
        return speed.getAngle();
    }
}

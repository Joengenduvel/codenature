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
    static final double GRAVITATIONAL_CONSTANT = 6.6743E-11;
    Vector2D position;
    Vector2D speed;
    Vector2D acceleration;
    long mass;

    private List<Sprite> collidingWith = new ArrayList<>(0);

    public void applyForce(Vector2D force) {
        acceleration = acceleration.add(force.divide(mass));
    }

    public boolean collidesWith(Sprite s) {
        if(s.getPosition().substract(getPosition()).getMagnitude()<10){
            if(!collidingWith.contains(s)) {
                collidingWith.add(s);
                log.info("{} collides with me {} because distance is {}", s, this, s.getPosition().substract(getPosition()).getMagnitude());
                //https://en.wikipedia.org/wiki/Elastic_collision
                final long totalMass = s.getMass() + getMass();
                //Calculate speed of colliding object
                final Vector2D v2 = getSpeed().times((double)getMass()*2/totalMass).add(s.getSpeed().times(((double)s.getMass()-getMass())/totalMass));
                //Calculate force to apply
                final Vector2D force = v2.times(s.getMass()).substract(s.getSpeed().times(s.getMass()));
                s.applyForce(force);
                return true;
            }else{
                log.info("ignore {} collides with me {} because distance is {}", s, this, s.getPosition().substract(getPosition()).getMagnitude());
            }
        }else {
            collidingWith.remove(s);
        }
        return false;
    }

    public void applyGravitationalPull(Sprite s){
        //F = GMm/R²
        Vector2D relativePointer = s.getPosition().substract(getPosition());
        double distance = relativePointer.getMagnitude();
        double forceMagnitude = GRAVITATIONAL_CONSTANT * getMass() * s.getMass() / (distance * distance);
        final Vector2D force = relativePointer.normalize().times(forceMagnitude * -1);
        s.applyForce(force);
    }

    public void applyDrag(double magnitude){
        if(getSpeed().getMagnitude()>0) {
            Vector2D vector2D = new Vector2D(-1, 0);
            vector2D = vector2D.rotate(getSpeed().getAngle()).times(magnitude);
            applyForce(vector2D);
        }
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
}

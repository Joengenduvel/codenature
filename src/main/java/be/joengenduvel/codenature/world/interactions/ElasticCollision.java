package be.joengenduvel.codenature.world.interactions;

import be.joengenduvel.codenature.math.Vector2D;
import be.joengenduvel.codenature.world.Sprite;
import lombok.extern.slf4j.Slf4j;

import java.util.function.BiFunction;
import java.util.function.Function;

@Slf4j
public class ElasticCollision implements Interaction {
    @Override
    public Boolean apply(Sprite s1, Sprite s2) {
        if(s2.getPosition().substract(s1.getPosition()).getMagnitude()<10){
            if(!s1.getCollidingWith().contains(s2)) {
                s1.getCollidingWith().add(s2);
                log.info("{} collides with me {} because distance is {}", s2, this, s2.getPosition().substract(s1.getPosition()).getMagnitude());
                //https://en.wikipedia.org/wiki/Elastic_collision
                final long totalMass = s1.getMass() + s2.getMass();
                //Calculate speed of colliding object
                final Vector2D v2 = s1.getSpeed().times((double)s1.getMass()*2/totalMass).add(s2.getSpeed().times(((double)s2.getMass()-s1.getMass())/totalMass));
                //Calculate force to apply
                final Vector2D force = v2.times(s2.getMass()).substract(s2.getSpeed().times(s2.getMass()));
                s2.applyForce(force);
                return true;
            }else{
                log.info("ignore {} collides with me {} because distance is {}", s2, this, s2.getPosition().substract(s1.getPosition()).getMagnitude());
            }
        }else {
            s1.getCollidingWith().remove(s2);
        }
        return false;
    }
}

package be.joengenduvel.codenature.world;

import be.joengenduvel.codenature.math.Vector2D;
import be.joengenduvel.codenature.world.forces.Drag;
import be.joengenduvel.codenature.world.forces.Force;
import be.joengenduvel.codenature.world.interactions.ElasticCollision;
import be.joengenduvel.codenature.world.interactions.GravitationalPull;
import be.joengenduvel.codenature.world.interactions.Interaction;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class World {
    private static final Vector2D WORLD_SIZE = new Vector2D(300, 300);
    private static final double SPEED_FACTOR = 0.05;
    private static final double GRAVITATIONAL_CONSTANT = 6.6743E-11;

    private ZonedDateTime previousTick;

    private final List<Sprite> sprites;
    private final List<Interaction> interactionFunctions;
    private final List<Force> forces;

    public World() {
        sprites = new CopyOnWriteArrayList<>();

        interactionFunctions = new ArrayList<>(2);
        interactionFunctions.add(new ElasticCollision());
        interactionFunctions.add(new GravitationalPull(GRAVITATIONAL_CONSTANT));

        forces = new ArrayList<>(1);
        forces.add(new Drag(0.01));

        sprites.add(new Sprite(
                new Vector2D(150,150),
                Vector2D.ZERO,
                Vector2D.ZERO,
                (long)10E11,
                new ArrayList<>()
        ));
    }

    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    public void tick(){
        if(previousTick == null){
            //only at first tick
            previousTick = ZonedDateTime.now();
        }

        ZonedDateTime now = ZonedDateTime.now();
        long deltaT = ChronoUnit.MILLIS.between(previousTick, now);
        sprites.forEach(s -> {
            s.move(deltaT*SPEED_FACTOR);
            wrapAroundCornersOfTheWorld(s);
            //TODO: make parallel
            forces.forEach(f -> f.apply(s));
            checkInteractions(s);
        });
        previousTick = now;
    }



    public Vector2D getSize() {
        return WORLD_SIZE;
    }

    public List<Sprite> getSprites(){
        return sprites;
    }

    private void wrapAroundCornersOfTheWorld(Sprite s) {
        double x = s.getPosition().getX();
        double y = s.getPosition().getY();

        if(x < 0){
            x = WORLD_SIZE.getX() + x;
        }
        if(y < 0){
            y = WORLD_SIZE.getY() + y;
        }

        if(x > WORLD_SIZE.getX()){
            x = x - WORLD_SIZE.getX();
        }
        if(y > WORLD_SIZE.getY()){
            y = y - WORLD_SIZE.getY();
        }

        s.setPosition(new Vector2D(x,y));
    }


    private void checkInteractions(Sprite s) {
        sprites.forEach(os -> {
            if(os != s){
                //TODO: make parallel
                interactionFunctions.forEach(f -> f.apply(os, s));
            }
        });
    }
}

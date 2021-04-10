package be.joengenduvel.codenature.world.forces;

import be.joengenduvel.codenature.math.Vector2D;
import be.joengenduvel.codenature.world.Sprite;

public class Drag implements Force{
    private final double magnitude;

    public Drag(double magnitude) {
        this.magnitude = magnitude;
    }

    @Override
    public boolean apply(Sprite s) {
        if(s.getSpeed().getMagnitude()>0) {
            Vector2D vector2D = new Vector2D(-1, 0);
            vector2D = vector2D.rotate(s.getSpeed().getAngle()).times(magnitude);
            s.applyForce(vector2D);
            return true;
        }
        return false;
    }
}

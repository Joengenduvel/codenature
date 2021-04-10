package be.joengenduvel.codenature.world.interactions;

import be.joengenduvel.codenature.math.Vector2D;
import be.joengenduvel.codenature.world.Sprite;

public class GravitationalPull implements Interaction {
    private final double gravitationalConstant;

    public GravitationalPull(double gravitationalConstant) {
        this.gravitationalConstant = gravitationalConstant;
    }

    @Override
    public Boolean apply(Sprite s1, Sprite s2) {
        //F = GMm/R²
        Vector2D relativePointer = s2.getPosition().substract(s1.getPosition());
        double distance = relativePointer.getMagnitude();
        double forceMagnitude = gravitationalConstant * s1.getMass() * s2.getMass() / (distance * distance);
        final Vector2D force = relativePointer.normalize().times(forceMagnitude * -1);
        s2.applyForce(force);
        return true;
    }
}

package be.joengenduvel.codenature.math;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.round;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

@Value
@Getter
@Slf4j
public class Vector2D {
    public static final Vector2D ZERO = new Vector2D(0,0);

    private static final double FULL_CIRCLE = 2 * Math.PI;
    double x,y;

    //TODO change to make it logical to use
    public Vector2D add(Vector2D b) {
        return new Vector2D(x + b.x, y + b.y);
    }

    public Vector2D substract(Vector2D b) {
        return new Vector2D(x - b.x, y - b.y);
    }

    //TODO change to make it logical to use
    public Vector2D times(double n) {
        return new Vector2D(x*n, y*n);
    }


    public Vector2D divide(double n) {
        if(n == 0)
            return this;
        return new Vector2D(x/n, y/n);
    }

    //TODO change to make it logical to use
    public Vector2D rotate(double angle){
        long x2=round(cos(angle) * x - sin(angle)*y);
        long y2=round(sin(angle)* x + cos(angle) * y);
        return new Vector2D(x2,y2);
    }

    public double getMagnitude(){
        return sqrt(x*x+y*y);
    }

    public double getAngle(){
        return (atan2(y,x)+ FULL_CIRCLE) % FULL_CIRCLE;
    }

    public Vector2D normalize() {
        return divide(getMagnitude());
    }
}

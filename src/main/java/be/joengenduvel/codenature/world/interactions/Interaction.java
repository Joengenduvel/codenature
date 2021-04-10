package be.joengenduvel.codenature.world.interactions;

import be.joengenduvel.codenature.world.Sprite;

import java.util.function.BiFunction;

public interface Interaction extends BiFunction<Sprite,Sprite, Boolean> {
}

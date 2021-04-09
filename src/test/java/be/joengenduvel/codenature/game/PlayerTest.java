package be.joengenduvel.codenature.game;

import be.joengenduvel.codenature.math.Vector2D;
import be.joengenduvel.codenature.web.models.KeyBinding;
import be.joengenduvel.codenature.world.Sprite;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void rightMoveLeft() {
        Vector2D speed = new Vector2D(1, 0);
        Player player = getPlayer(speed);

        player.move(new KeyBinding(true, false, false, false));
        player.getSprite().move(0);

        assertThat(player.getSprite().getSpeed()).isEqualTo(new Vector2D(1,1));
    }

    @Test
    void rightMoveRight() {
        Vector2D speed = new Vector2D(1, 0);
        Player player = getPlayer(speed);

        player.move(new KeyBinding(false, true, false, false));
        player.getSprite().move(0);

        assertThat(player.getSprite().getSpeed()).isEqualTo(new Vector2D(1,-1));
    }

    @Test
    void rightMoveUp() {
        Vector2D speed = new Vector2D(1, 0);
        Player player = getPlayer(speed);

        player.move(new KeyBinding(false, false, true, false));
        player.getSprite().move(0);

        assertThat(player.getSprite().getSpeed()).isEqualTo(new Vector2D(2,0));
    }

    @Test
    void rightMoveDown() {
        Vector2D speed = new Vector2D(1, 0);
        Player player = getPlayer(speed);

        player.move(new KeyBinding(false, false, false, true));
        player.getSprite().move(0);

        assertThat(player.getSprite().getSpeed()).isEqualTo(new Vector2D(0,0));
    }

    private Player getPlayer(Vector2D speed) {
        return new Player(UUID.randomUUID(), "test", new Sprite(new Vector2D(0, 0), speed, new Vector2D(0,0), 0, new ArrayList<>()));
    }
}
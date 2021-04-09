package be.joengenduvel.codenature.math;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Vector2DTest {

    @Test
    void getAngle0() {
        assertThat(new Vector2D(0, 0).getAngle()).isEqualTo(0);
    }


    @Test
    void getAngle45() {
        assertThat(new Vector2D(1, 1).getAngle()).isEqualTo(Math.PI / 4);
    }

    @Test
    void getAngle90() {
        assertThat(new Vector2D(0, 1).getAngle()).isEqualTo(2 * Math.PI / 4);
    }

    @Test
    void getAngle135() {
        assertThat(new Vector2D(-1, 1).getAngle()).isEqualTo(3 * Math.PI / 4);
    }

    @Test
    void getAngle180() {
        assertThat(new Vector2D(-1, 0).getAngle()).isEqualTo(4 * Math.PI / 4);
    }

    @Test
    void getAngle225() {
        assertThat(new Vector2D(-1, -1).getAngle()).isEqualTo(5 * Math.PI / 4);
    }

    @Test
    void getAngle270() {
        assertThat(new Vector2D(0, -1).getAngle()).isEqualTo(6 * Math.PI / 4);
    }

    @Test
    void getAngle315() {
        assertThat(new Vector2D(1, -1).getAngle()).isEqualTo(7 * Math.PI / 4);
    }

    @Test
    void getAngle360() {
        assertThat(new Vector2D(1, 0).getAngle()).isEqualTo(0);
    }
}
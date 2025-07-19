package upm.models;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StripTest {
    private Strip strip;

    @BeforeEach
    public void setUp() {
        strip = new Strip(7);
    }

    @Test
    public void testInsert() {
        Color color = Color.RED;
        strip.insert(color);
        Assertions.assertEquals(color, strip.getColor(0));
    }

    @Test
    public void testIsFull() {
        Assertions.assertFalse(strip.isFull());
        int dimension = strip.getDimension();
        for (int i = 0; i < dimension; i++) {
            strip.insert(Color.YELLOW);
        }
        Assertions.assertTrue(strip.isFull());
    }

    @Test
    public void testInsertFullException() {
        for (int i = 0; i < strip.getDimension(); i++) {
            strip.insert(Color.YELLOW);
        }
        Exception exception = Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            strip.insert(Color.RED);
        });
    }

    @Test
    public void testGetColor() {
        strip.insert(Color.YELLOW);
        strip.insert(Color.RED);
        Color color = strip.getColor(1);
        Assertions.assertEquals(Color.RED, color);
    }

    @Test
    public void testWinnerBegin() {
        strip.insert(Color.RED);
        strip.insert(Color.RED);
        strip.insert(Color.RED);
        strip.insert(Color.RED);
        Color winner = strip.winner();
        Assertions.assertEquals(Color.RED, winner);
    }

    @Test
    public void testWinnerMiddle() {
        strip.insert(null);
        strip.insert(Color.RED);
        strip.insert(Color.RED);
        strip.insert(Color.RED);
        strip.insert(Color.RED);
        Color winner = strip.winner();
        Assertions.assertEquals(Color.RED, winner);
    }

    @Test
    public void testWinnerEnd() {
        strip.insert(null);
        strip.insert(null);
        strip.insert(null);
        strip.insert(Color.RED);
        strip.insert(Color.RED);
        strip.insert(Color.RED);
        strip.insert(Color.RED);
        Color winner = strip.winner();
        Assertions.assertEquals(Color.RED, winner);
    }

    @Test
    public void testReset() {
        strip.insert(Color.RED);
        strip.insert(Color.YELLOW);
        strip.reset();
        Assertions.assertNull(strip.getColor(0));
    }
}

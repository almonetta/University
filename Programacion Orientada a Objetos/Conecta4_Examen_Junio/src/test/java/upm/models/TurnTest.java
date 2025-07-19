package upm.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TurnTest {
    @Test
    public void testChange() {
        Turn turn = new Turn();
        Assertions.assertEquals(Color.RED, turn.getColor());
        turn.change();
        Assertions.assertEquals(Color.YELLOW, turn.getColor());
        turn.change();
        Assertions.assertEquals(Color.RED, turn.getColor());
    }

}


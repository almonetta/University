package upm.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    public void testReset() {
        board.insert(0, Color.RED);
        board.insert(1, Color.YELLOW);
        board.insert(2, Color.RED);
        board.reset();
        for (int i = 0; i < Board.COLUMNS; i++) {
            Assertions.assertNull(board.row(0).getColor(0));
        }
    }

    @Test
    public void testInsertValidColumn() {
        board.insert(0, Color.RED);
        Assertions.assertEquals(Color.RED, board.row(0).getColor(0));
    }

    @Test
    public void testInsertInvalidColumn() {
        Exception exception = Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            board.insert(-1, Color.RED);
        });
    }

    @Test
    public void testRow() {
        board.insert(0, Color.RED);
        board.insert(1, Color.YELLOW);
        board.insert(2, Color.RED);
        Strip row = board.row(0);
        Assertions.assertEquals(Color.RED, row.getColor(0));
        Assertions.assertEquals(Color.YELLOW, row.getColor(1));
        Assertions.assertEquals(Color.RED, row.getColor(2));
    }

    @Test
    public void testFull() {
        Color color = Color.RED;
        for (int i = 0; i < Board.ROWS; i++) {
            color = color.equals(Color.RED) ? Color.YELLOW : Color.RED;
            Color color2 = color;
            for (int j = 0; j < Board.COLUMNS; j++) {
                board.insert(j, color2);
                if (j % 3 == 0) {
                    color2 = color2.equals(Color.RED) ? Color.YELLOW : Color.RED;
                }
            }
        }
        Assertions.assertTrue(board.full());
    }

    @Test
    public void testWinnerColumn() {
        board.insert(0, Color.RED);
        board.insert(0, Color.RED);
        board.insert(0, Color.RED);
        board.insert(0, Color.RED);
        Assertions.assertEquals(Color.RED, board.winner());
    }

    @Test
    public void testWinnerRow() {
        board.insert(0, Color.YELLOW);
        board.insert(1, Color.YELLOW);
        board.insert(2, Color.YELLOW);
        board.insert(3, Color.YELLOW);
        Assertions.assertEquals(Color.YELLOW, board.winner());
    }

    @Test
    public void testWinnerDiagonalRight() {
        board.insert(0, Color.RED);
        board.insert(1, Color.YELLOW);
        board.insert(1, Color.RED);
        board.insert(2, Color.YELLOW);
        board.insert(2, Color.YELLOW);
        board.insert(2, Color.RED);
        board.insert(3, Color.YELLOW);
        board.insert(3, Color.YELLOW);
        board.insert(3, Color.YELLOW);
        board.insert(3, Color.RED);
        Assertions.assertEquals(Color.RED, board.winner());
    }

    @Test
    public void testWinnerDiagonalLeft() {
        board.insert(0, Color.YELLOW);
        board.insert(0, Color.YELLOW);
        board.insert(0, Color.YELLOW);
        board.insert(0, Color.RED);
        board.insert(1, Color.YELLOW);
        board.insert(1, Color.YELLOW);
        board.insert(1, Color.RED);
        board.insert(2, Color.YELLOW);
        board.insert(2, Color.RED);
        board.insert(3, Color.RED);
        Assertions.assertEquals(Color.RED, board.winner());
    }

}





package upm.console;


import upm.models.Board;
import upm.models.Color;

public class BoardView extends View {

    public void showBoard(Board board) {
        for (int j = Board.ROWS - 1; j >= 0; j--) {
            for (int i = 0; i < Board.COLUMNS; i++) {
                Color color = board.row(j).getColor(i);
                if (color == null) {
                    System.out.print("-");
                } else if (color.equals(Color.RED)) {
                    System.out.print(BoardView.RED + "R" + BoardView.RESET);
                } else if (color.equals(Color.YELLOW)) {
                    System.out.print(BoardView.YELLOW + "Y" + BoardView.RESET);
                }
            }
            System.out.println();
        }
    }
}
package upm.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public final static int COLUMNS = 7;
    public final static int ROWS = 6;
    private final List<Strip> columns;

    public Board() {
        this.columns = new ArrayList<>(COLUMNS);
        for (int i = 0; i < COLUMNS; i++) {
            Strip column = new Strip(ROWS);
            columns.add(column);
        }
    }

    public void reset() {
        for (int i = 0; i < COLUMNS; i++) {
            columns.get(i).reset();
        }
    }

    public void insert(int column, Color color) {
        if (column < 0 || column >= COLUMNS) {
            throw new UnsupportedOperationException("Columna no permitida. Debe estar entre 0.." + (COLUMNS - 1));
        }
        if (this.winner() != null) {
            throw new UnsupportedOperationException("Ya existe un ganador. La partida finalizó");
        }
        this.columns.get(column).insert(color);
    }

    public Strip row(int rowNumber) {
        Strip row = new Strip(COLUMNS);
        for (Strip strip : this.columns) {
            row.insert(strip.getColor(rowNumber));
        }
        return row;
    }

    private Strip rightDiagonal(int diagonalNumber) {
        int column = Math.max(diagonalNumber, 0);
        int row = diagonalNumber < 0 ? -diagonalNumber : 0;
        int size = ROWS;
        if (diagonalNumber < 0) {
            size = ROWS + diagonalNumber;
        } else if (diagonalNumber > (COLUMNS - ROWS)) {
            size = COLUMNS - diagonalNumber;
        }
        Strip diagonal = new Strip(size);
        for (int i = 0; i < diagonal.getDimension(); i++) {
            diagonal.insert(this.columns.get(column).getColor(row));
            column++;
            row++;
        }
        return diagonal;
    }

    private Strip leftDiagonal(int diagonalNumber) {
        int column = diagonalNumber < COLUMNS ? diagonalNumber : COLUMNS - 1;
        int row = diagonalNumber < COLUMNS ? 0 : diagonalNumber - COLUMNS + 1;
        int size = ROWS;
        if (diagonalNumber < (ROWS - 1)) {
            size = diagonalNumber + 1;
        } else if (diagonalNumber > (COLUMNS - 1)) {
            size = ROWS - diagonalNumber + COLUMNS - 1;
        }
        Strip diagonal = new Strip(size);
        for (int i = 0; i < diagonal.getDimension(); i++) {
            diagonal.insert(this.columns.get(column).getColor(row));
            column--;
            row++;
        }
        return diagonal;
    }

    public boolean full() {
        for (Strip strip : this.columns) {
            if (!strip.isFull()) {
                return false;
            }
        }
        return true;
    }

    public Color winner() {
        for (Strip column : this.columns) {
            Color winner = column.winner();
            if (null != winner) {
                return winner;
            }
        }
        for (int i = 0; i < ROWS; i++) {
            Color winner = this.row(i).winner();
            if (null != winner) {
                return winner;
            }
        }
        for (int i = -ROWS + 1; i < COLUMNS; i++) {
            Color winner = this.rightDiagonal(i).winner();
            if (null != winner) {
                return winner;
            }
        }
        for (int i = 0; i < COLUMNS + ROWS - 1; i++) {
            Color winner = this.leftDiagonal(i).winner();
            if (null != winner) {
                return winner;
            }
        }
        return null;
    }
}

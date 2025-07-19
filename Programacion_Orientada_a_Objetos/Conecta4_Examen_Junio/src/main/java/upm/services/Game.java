package upm.services;

import upm.models.Board;
import upm.models.Color;
import upm.models.Data;
import upm.models.Turn;

public class Game {
    private final Turn turn;
    private final Board board;

    public Game(Turn turn, Board board) {
        this.turn = turn;
        this.board = board;
    }

    public Data insert(int column) {
        this.board.insert(column, this.turn.getColor());
        if (this.board.winner() == null && !this.board.full()) {
            this.turn.change();
        }
        return this.state();
    }

    public Data state() {
        Color winner = this.board.winner();
        if (winner != null) {
            return new Data("The winner is " + winner, this.board);
        } else if (this.board.full()) {
            return new Data("Tie!!!", this.board);
        } else {
            return new Data("Turn: " + this.turn.getColor(), this.board);
        }
    }

    public Data reset() {
        this.turn.start();
        this.board.reset();
        return this.state();
    }
}
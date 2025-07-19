package upm.models;

public class Data {
    private final String message;
    private final Board board;

    public Data(String message, Board board) {
        this.message = message;
        this.board = board;
    }

    public String getMessage() {
        return message;
    }

    public Board getBoard() {
        return board;
    }
}

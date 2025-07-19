package upm;

import upm.console.BoardView;
import upm.console.CommandLineInterface;
import upm.console.ErrorHandler;
import upm.models.Board;
import upm.models.Turn;
import upm.services.Game;

public class DependencyInjector {

    private final BoardView view;
    private final Board board;
    private final Turn turn;
    private final Game game;
    private final CommandLineInterface commandLineInterface;
    private final ErrorHandler errorHandler;


    public DependencyInjector() {
        this.view = new BoardView();
        this.board = new Board();
        this.turn = new Turn();
        this.game = new Game(turn, board);
        this.commandLineInterface = new CommandLineInterface(view, game);
        this.errorHandler = new ErrorHandler(commandLineInterface, view);
    }

    public void inject() {
        this.errorHandler.handlesErrors();
    }

    public BoardView getView() {
        return view;
    }

    public Board getBoard() {
        return board;
    }

    public Turn getTurn() {
        return turn;
    }

    public Game getGame() {
        return game;
    }

    public CommandLineInterface getCommandLineInterface() {
        return commandLineInterface;
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }
}

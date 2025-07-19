package upm.console;

import upm.models.Data;
import upm.services.Game;

import java.util.Scanner;

public class CommandLineInterface {
    public static final String DELIMITER_ANY_WHITE_SPACE = "\\s";
    private final BoardView view;
    private final Game game;

    public CommandLineInterface(BoardView view, Game game) {
        this.view = view;
        this.game = game;
    }

    private void help() {
        for (CommandNames command : CommandNames.values()) {
            this.view.show(command.getHelp());
        }
    }

    private void insert(Scanner scanner) {
        this.showData(this.game.insert(Integer.parseInt(scanner.next())));
    }

    private void reset() {
        this.showData(this.game.reset());
    }

    private void state() {
        this.showData(this.game.state());
    }

    private void showData(Data data) {
        this.view.showBoard(data.getBoard());
        this.view.show(data.getMessage());
    }

    public void runCommands() {
        Scanner scanner = new Scanner(System.in).useDelimiter(CommandLineInterface.DELIMITER_ANY_WHITE_SPACE);
        boolean exit = false;
        while (!exit) {
            exit = runCommand(scanner);
        }
    }

    private boolean runCommand(Scanner scanner) {
        this.view.showCommand();
        CommandNames command = CommandNames.fromValue(scanner.next());
        boolean exit = false;
        switch (command) {
            case INSERT:
                this.insert(scanner);
                break;
            case RESET:
                this.reset();
                break;
            case STATE:
                this.state();
                break;
            case HELP:
                this.help();
                break;
            case EXIT:
                exit = true;
                break;
        }
        return exit;
    }
}

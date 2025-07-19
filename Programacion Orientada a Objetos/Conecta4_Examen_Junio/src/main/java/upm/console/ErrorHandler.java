package upm.console;

public class ErrorHandler {
    private final CommandLineInterface commandLineInterface;
    private final BoardView view;

    public ErrorHandler(CommandLineInterface commandLineInterface, BoardView view) {
        this.commandLineInterface = commandLineInterface;
        this.view = view;
        this.view.showBold("App Connect4. " + View.COPY_RIGHT + "UPM.ETSISI.POO");
    }

    public void handlesErrors() {
        while (true) {
            try {
                this.commandLineInterface.runCommands();
                break;
            } catch (Exception e) {
                this.view.showError(">>> ERROR (" + e.getClass().getSimpleName() + ") >>> " + e.getMessage());
            }
        }
        this.view.showBold("Hasta pronto!");
    }
}

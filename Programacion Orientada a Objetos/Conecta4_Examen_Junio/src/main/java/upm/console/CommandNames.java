package upm.console;

public enum CommandNames {
    INSERT("i", " <column>: se inserta el color actual en la columna indicada"),
    RESET("reset", ": inicializa la partida"),
    STATE("state", ": presenta el estado de la partida partida"),
    HELP("help", ": muestra la ayuda."),
    EXIT("exit", ": termina la ejecución.");

    private final String value;
    private final String help;

    CommandNames(String value, String help) {
        this.value = value;
        this.help = help;
    }

    public static CommandNames fromValue(String value) {
        for (CommandNames command : CommandNames.values()) {
            if (command.getValue().equals(value)) {
                return command;
            }
        }
        throw new UnsupportedOperationException("Comando '" + value + "' no existe.");
    }

    public String getValue() {
        return this.value;
    }

    public String getHelp() {
        return this.getValue() + this.help;
    }
}

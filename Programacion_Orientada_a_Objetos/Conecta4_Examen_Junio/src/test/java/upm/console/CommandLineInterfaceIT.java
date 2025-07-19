package upm.console;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import upm.DependencyInjector;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandLineInterfaceIT {
    private CommandLineInterface commandLineInterface;

    @BeforeEach
    void before() {
        this.commandLineInterface = new DependencyInjector().getCommandLineInterface();
    }

    @Test
    void testProcessCommandsError() {
        System.setIn(new ByteArrayInputStream("kk ".getBytes()));
        assertThrows(UnsupportedOperationException.class, () -> this.commandLineInterface.runCommands());
    }
    @Test
    void testProcessCommandsReset() {
        String input = "reset \r";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertThrows(UnsupportedOperationException.class, () -> this.commandLineInterface.runCommands());
    }

    @Test
    void testProcessCommandsInsert() {
        String input = "i 3 \r";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        assertThrows(UnsupportedOperationException.class, () -> this.commandLineInterface.runCommands());
    }

}

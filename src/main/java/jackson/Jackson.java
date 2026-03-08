package jackson;

import jackson.command.Command;
import jackson.parser.Parser;
import jackson.storage.Storage;
import jackson.exception.JacksonException;
import jackson.tasklist.TaskList;
import jackson.ui.Ui;

/**
 * The main entry point for the Jackson task management application.
 * This class coordinates the user interface, storage, and logic components
 * to manage the application's lifecycle.
 */
public class Jackson {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes the Jackson application with a specific file path for data storage.
     * It sets up the UI and Storage components, ensures the data file exists,
     * and attempts to load existing tasks.
     *
     * @param filePath The relative or absolute path to the file where tasks are saved.
     */
    public Jackson(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            storage.makeFile();
            tasks = storage.loadFile();
        } catch (JacksonException e) {
            Ui.showErrorMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Starts the main execution loop of the application.
     * It displays a welcome message and repeatedly processes user input
     * until an exit command is received.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (JacksonException e) {
                Ui.showErrorMessage(e.getMessage());
            }
        }
    }

    /**
     * Main method that serves as the starting point for the JVM.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        new Jackson("./data/jackson.txt").run();
    }
}

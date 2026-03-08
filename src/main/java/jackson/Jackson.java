package jackson;

import jackson.command.Command;
import jackson.parser.Parser;
import jackson.storage.Storage;
import jackson.exception.JacksonException;
import jackson.tasklist.TaskList;
import jackson.ui.Ui;

public class Jackson {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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


    public static void main(String[] args) {
        new Jackson("./data/jackson.txt").run();
    }
}

package jackson;

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
        String line = ui.readCommand().trim();

        while (!line.equals("bye")) {
            String instruction = Parser.getCommandWord(line);
            switch (instruction) {
            case "list":
                ui.printList(tasks);
                break;

            case "mark":
            case "unmark":
                try {
                    int taskNumber = tasks.updateMarkStatus(tasks, line);
                    ui.printStatus(instruction, taskNumber, tasks.getItemAtIndex(taskNumber - 1));
                } catch (JacksonException e) {
                    Ui.showErrorMessage(e.getMessage());
                }
                break;

            case "todo":
            case "deadline":
            case "event":
                try {
                    tasks.addTask(line);
                    ui.printTaskAdded(tasks.getItemAtIndex(tasks.size() - 1));
                    ui.printNumberOfItems(tasks);
                } catch (JacksonException e) {
                    Ui.showErrorMessage(e.getMessage());
                }
                break;

            case "delete":
                try {
                    tasks.deleteTask(line);
                    ui.printNumberOfItems(tasks);
                } catch (JacksonException e) {
                    Ui.showErrorMessage(e.getMessage());
                }
                break;

            default:
                Ui.showMessage("Yo bro your instruction is invalid");
            }
            line = ui.readCommand();
        }
        ui.showGoodbye();
        storage.saveCurrentData(tasks);
    }

    public static void main(String[] args) {
        new Jackson("./data/jackson.txt").run();
    }
}

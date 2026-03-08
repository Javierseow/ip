package jackson;

import jackson.parser.Parser;
import jackson.storage.Storage;
import jackson.exception.JacksonException;
import jackson.task.Task;
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
                    int taskNumber = Parser.updateMarkStatus(tasks, line);
                    ui.printStatus(instruction, taskNumber, TaskList.getItemAtIndex(taskNumber - 1));
                } catch (JacksonException e) {
                    Ui.showErrorMessage(e.getMessage());
                }
                break;

            case "todo":
            case "deadline":
            case "event":
                try {
                    Parser.addTask(line);
                    ui.printTaskAdded(TaskList.getItemAtIndex(tasks.size() - 1));
                    ui.printNumberOfItems(tasks);
                } catch (JacksonException e) {
                    Ui.showErrorMessage(e.getMessage());
                }
                break;

            case "delete":
                try {
                    Task taskDeleted = Parser.deleteTask(line);
                    ui.printTaskDeleted(taskDeleted);
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

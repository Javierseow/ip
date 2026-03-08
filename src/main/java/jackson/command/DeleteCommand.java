package jackson.command;

import jackson.exception.JacksonException;
import jackson.parser.Parser;
import jackson.task.Task;
import jackson.tasklist.TaskList;
import jackson.ui.Ui;
import jackson.storage.Storage;

public class DeleteCommand extends Command {
    private String line;

    public DeleteCommand(String line) {
        this.line = line;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JacksonException {
        Task taskDeleted = Parser.deleteTask(line);
        ui.printTaskDeleted(taskDeleted);
        ui.printNumberOfItems(tasks);
    }
}
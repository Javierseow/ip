package jackson.command;

import jackson.exception.JacksonException;
import jackson.parser.Parser;
import jackson.tasklist.TaskList;
import jackson.ui.Ui;
import jackson.storage.Storage;

public class AddCommand extends Command {
    private String line;

    public AddCommand(String line) {
        this.line = line;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JacksonException {
        Parser.addTask(line);
        ui.printTaskAdded(TaskList.getItemAtIndex(tasks.size() - 1));
        ui.printNumberOfItems(tasks);
    }
}
package jackson.command;

import jackson.exception.JacksonException;
import jackson.parser.Parser;
import jackson.task.Task;
import jackson.tasklist.TaskList;
import jackson.ui.Ui;
import jackson.storage.Storage;

public class FindCommand extends Command {
    private String line;

    public FindCommand(String line) {
        this.line = line;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JacksonException {
        Ui.showMessage("Here are the matching tasks in your list:");
        boolean taskExists = false;
        taskExists = Parser.findTask(line, tasks);
        if (!taskExists) {
            Ui.showMessage("Rip bro got no tasks that match your word");
        }
    }
}
package jackson.command;

import jackson.exception.JacksonException;
import jackson.tasklist.TaskList;
import jackson.ui.Ui;
import jackson.storage.Storage;

public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JacksonException {
        Ui.showMessage("Yo bro your instruction is invalid");
    }
}
package jackson.command;

import jackson.storage.Storage;
import jackson.tasklist.TaskList;
import jackson.ui.Ui;
import jackson.exception.JacksonException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JacksonException;

    public boolean isExit() {
        return false;
    }
}
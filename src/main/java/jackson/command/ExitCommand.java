package jackson.command;

import jackson.tasklist.TaskList;
import jackson.ui.Ui;
import jackson.storage.Storage;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        storage.saveCurrentData(tasks);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
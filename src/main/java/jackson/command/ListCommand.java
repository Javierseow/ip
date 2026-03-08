package jackson.command;

import jackson.tasklist.TaskList;
import jackson.ui.Ui;
import jackson.storage.Storage;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
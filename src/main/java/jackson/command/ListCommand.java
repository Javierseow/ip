package jackson.command;

import jackson.tasklist.TaskList;
import jackson.ui.Ui;
import jackson.storage.Storage;

/**
 * Represents a command to display all tasks currently in the task list.
 * This class interacts with the Ui to print the formatted list of tasks to the user.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     * It calls the UI component to display every task currently stored in the TaskList.
     *
     * @param tasks   The TaskList containing the tasks to be displayed.
     * @param ui      The Ui used to format and print the list.
     * @param storage The Storage object (not used by this specific command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
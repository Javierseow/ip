package jackson.command;

import jackson.tasklist.TaskList;
import jackson.ui.Ui;
import jackson.storage.Storage;

/**
 * Represents a command to terminate the Jackson application.
 * This class handles the cleanup process, including saving data and
 * displaying a goodbye message.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     * Displays a goodbye message to the user and saves the current state
     * of the task list to the storage file.
     *
     * @param tasks   The TaskList to be saved before exiting.
     * @param ui      The Ui used to display the goodbye message.
     * @param storage The Storage used to persist task data to the disk.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        storage.saveCurrentData(tasks);
    }

    /**
     * Signals to the main application loop that the program should stop running.
     *
     * @return True, indicating that the application should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
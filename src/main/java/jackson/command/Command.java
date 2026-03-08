package jackson.command;

import jackson.storage.Storage;
import jackson.tasklist.TaskList;
import jackson.ui.Ui;
import jackson.exception.JacksonException;

/**
 * Represents an executable command in the Jackson application.
 * This is an abstract class that serves as a base for all specific command types
 * like Add, Delete, and Exit.
 */
public abstract class Command {

    /**
     * Executes the specific logic associated with the command.
     * This method defines how the command interacts with the task list,
     * user interface, and data storage.
     *
     * @param tasks   The TaskList containing the application's tasks.
     * @param ui      The Ui object used to interact with the user.
     * @param storage The Storage object used to save or load data from the file.
     * @throws JacksonException If an error occurs during the execution of the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws JacksonException;

    /**
     * Indicates whether this command should result in the application exiting.
     * By default, commands do not cause the application to terminate.
     *
     * @return True if the application should exit, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
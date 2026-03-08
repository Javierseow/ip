package jackson.command;

import jackson.exception.JacksonException;
import jackson.tasklist.TaskList;
import jackson.ui.Ui;
import jackson.storage.Storage;

/**
 * Represents a command that is created when the user input is not recognized.
 * This class serves as a graceful way to handle unknown or malformed instructions.
 */
public class InvalidCommand extends Command {

    /**
     * Executes the invalid command by notifying the user of the error.
     * It displays a message indicating that the provided instruction was not understood.
     *
     * @param tasks   The TaskList (not modified by this command).
     * @param ui      The Ui used to display the error message.
     * @param storage The Storage (not modified by this command).
     * @throws JacksonException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JacksonException {
        Ui.showMessage("Yo bro your instruction is invalid");
    }
}
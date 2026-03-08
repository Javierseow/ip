package jackson.command;

import jackson.exception.JacksonException;
import jackson.parser.Parser;
import jackson.tasklist.TaskList;
import jackson.ui.Ui;
import jackson.storage.Storage;

/**
 * Represents a command to add a task to the task list.
 * This class handles the addition of Todo, Deadline, and Event tasks.
 */
public class AddCommand extends Command {
    /** The raw input string containing the task type and description. */
    private String line;

    /**
     * Initializes an AddCommand with the full user input.
     *
     * @param line The full command line entered by the user.
     */
    public AddCommand(String line) {
        this.line = line;
    }

    /**
     * Executes the add command.
     * Parses the input to create a task, adds it to the list,
     * and triggers the UI to show confirmation to the user.
     *
     * @param tasks   The TaskList where the new task will be added.
     * @param ui      The Ui used to display the "task added" message.
     * @param storage The Storage used for potential data operations.
     * @throws JacksonException If the input format is invalid or description is missing.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JacksonException {
        Parser.addTask(line);
        ui.printTaskAdded(TaskList.getItemAtIndex(tasks.size() - 1));
        ui.printNumberOfItems(tasks);
    }
}
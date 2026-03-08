package jackson.command;

import jackson.exception.JacksonException;
import jackson.parser.Parser;
import jackson.task.Task;
import jackson.tasklist.TaskList;
import jackson.ui.Ui;
import jackson.storage.Storage;

/**
 * Represents a command to delete a task from the task list.
 * This class captures the user input and coordinates the removal of a specific task
 * based on its index in the list.
 */
public class DeleteCommand extends Command {
    /** The raw input string containing the index of the task to be deleted. */
    private String line;

    /**
     * Initializes a DeleteCommand with the full user input.
     *
     * @param line The full command line entered by the user (e.g., "delete 3").
     */
    public DeleteCommand(String line) {
        this.line = line;
    }

    /**
     * Executes the delete command.
     * It uses the Parser to remove the task from the list, then triggers the UI
     * to display the deleted task and the updated total task count.
     *
     * @param tasks   The TaskList from which the task will be removed.
     * @param ui      The Ui used to display confirmation of the deletion.
     * @param storage The Storage used for potential data operations.
     * @throws JacksonException If the index provided is invalid or out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JacksonException {
        Task taskDeleted = Parser.deleteTask(line);
        ui.printTaskDeleted(taskDeleted);
        ui.printNumberOfItems(tasks);
    }
}
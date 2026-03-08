package jackson.command;

import jackson.exception.JacksonException;
import jackson.parser.Parser;
import jackson.tasklist.TaskList;
import jackson.ui.Ui;
import jackson.storage.Storage;

/**
 * Represents a command to change the completion status of a task.
 * This class handles both marking a task as done and unmarking a task as not done.
 */
public class MarkCommand extends Command {
    /** The raw input string containing the task index. */
    private String line;
    /** The specific instruction word (either "mark" or "unmark"). */
    private String instruction;

    /**
     * Initializes a MarkCommand with the full user input and the specific instruction.
     *
     * @param line        The full command line entered by the user.
     * @param instruction The specific action to perform ("mark" or "unmark").
     */
    public MarkCommand(String line, String instruction) {
        this.line = line;
        this.instruction = instruction;
    }

    /**
     * Executes the mark or unmark command.
     * It uses the Parser to update the task's status in the TaskList and then
     * triggers the UI to display the updated status to the user.
     *
     * @param tasks   The TaskList containing the task to be updated.
     * @param ui      The Ui used to display the status update message.
     * @param storage The Storage object used for data operations.
     * @throws JacksonException If the task index is invalid, missing, or out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JacksonException {
        int taskNumber = Parser.updateMarkStatus(tasks, line);
        ui.printStatus(instruction, taskNumber, TaskList.getItemAtIndex(taskNumber - 1));
    }
}
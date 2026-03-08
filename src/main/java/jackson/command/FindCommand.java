package jackson.command;

import jackson.exception.JacksonException;
import jackson.parser.Parser;
import jackson.tasklist.TaskList;
import jackson.ui.Ui;
import jackson.storage.Storage;

/**
 * Represents a command to search for tasks by a keyword.
 * This class coordinates the search process and provides feedback to the user
 * regarding matching tasks in the list.
 */
public class FindCommand extends Command {
    /** The raw input string containing the search keyword. */
    private String line;

    /**
     * Initializes a FindCommand with the full user input.
     *
     * @param line The full command line entered by the user (e.g., "find book").
     */
    public FindCommand(String line) {
        this.line = line;
    }

    /**
     * Executes the find command.
     * It uses the Parser to filter tasks matching the keyword and prints them via the UI.
     * If no matches are found, it displays a failure message to the user.
     *
     * @param tasks   The TaskList to search within.
     * @param ui      The Ui used to display messages and search results.
     * @param storage The Storage used for potential data operations.
     * @throws JacksonException If the search keyword is missing or invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JacksonException {
        boolean taskExists = false;
        taskExists = Parser.findTask(line, tasks);
        if (!taskExists) {
            Ui.showMessage("Rip bro got no tasks that match your word");
        }
    }
}
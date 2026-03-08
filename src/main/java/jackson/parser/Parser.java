package jackson.parser;

import jackson.command.AddCommand;
import jackson.command.Command;
import jackson.command.ListCommand;
import jackson.command.MarkCommand;
import jackson.command.DeleteCommand;
import jackson.command.FindCommand;
import jackson.command.ExitCommand;
import jackson.command.InvalidCommand;
import jackson.exception.JacksonException;
import jackson.task.Task;
import jackson.tasklist.TaskList;
import jackson.ui.Ui;

/**
 * Deals with making sense of the user command.
 * This class provides utility methods to parse raw input strings into commands
 * or specific task actions.
 */
public class Parser {
    /**
     * Extracts the command word from a full line of input.
     *
     * @param line The full input string entered by the user.
     * @return The first word of the input string.
     */
    public static String getCommandWord(String line) {
        return line.trim().split(" ")[0];
    }

    /**
     * Splits an input string based on a specific character or regex pattern.
     *
     * @param line The input string to split.
     * @param splitCharacter The character or pattern to split by.
     * @param maxSplit The maximum number of segments to split the string into.
     * @return An array of strings resulting from the split.
     */
    public static String[] splitInput(String line, String splitCharacter, int maxSplit) {
        return line.trim().split("\\s*" + splitCharacter + "\\s*", maxSplit);
    }

    /**
     * Parses the full command string and returns the corresponding Command object.
     *
     * @param line The full input string entered by the user.
     * @return A Command object that can be executed.
     * @throws JacksonException If the command is unrecognized or improperly formatted.
     */
    public static Command parse(String line) throws JacksonException {
        String instruction = getCommandWord(line);
        switch (instruction) {
        case "list":
            return new ListCommand();

        case "mark":
        case "unmark":
            return new MarkCommand(line, instruction);

        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(line);

        case "delete":
            return new DeleteCommand(line);

        case "find":
            return new FindCommand(line);

        case "bye":
            return new ExitCommand();

        default:
            return new InvalidCommand();
        }
    }

    /**
     * Parses a string to create and add a task directly to the TaskList.
     * Handles specific formatting for todos, deadlines, and events.
     *
     * @param line The description and metadata of the task to be added.
     * @throws JacksonException If the task description or time format is invalid.
     */
    public static void addTask(String line) throws JacksonException {
        String[] commandAndDescription = Parser.splitInput(line, " ", 2);

        if (commandAndDescription.length < 2) {
            throw new JacksonException("Bro ur " + commandAndDescription[0] + " got no description");
        }
        String command = commandAndDescription[0];
        String description = commandAndDescription[1];

        if (command.equals("todo")) {
            TaskList.addTodo(description);
            return;
        }

        String[] taskInfo = Parser.splitInput(description, "/", 0);
        if (taskInfo.length < 1 || taskInfo[0].isEmpty()) {
            throw new JacksonException("Bro ur " + command + " got no description");
        }

        if (command.equals("deadline")) {
            if (taskInfo.length < 2 || !taskInfo[1].trim().startsWith("by ")) {
                throw new JacksonException("Yo u forgot to specify ur deadline time");
            }
            TaskList.addDeadline(taskInfo[0].trim(), taskInfo[1].trim().substring(3).trim());
            return;
        }

        if (taskInfo.length < 3) {
            throw new JacksonException("Dude u didn't tell me your event duration properly");
        }
        if (!taskInfo[1].trim().startsWith("from ")) {
            throw new JacksonException("Gotta tell me when your event starts bro");
        }
        if (!taskInfo[2].trim().startsWith("to ")) {
            throw new JacksonException("Gotta tell me when your event ends bro");
        }
        TaskList.addEvent(taskInfo[0].trim(),
                taskInfo[1].trim().substring(5).trim(),
                taskInfo[2].trim().substring(3).trim());
    }

    /**
     * Parses a delete command string and removes the task from the TaskList.
     *
     * @param line The raw delete command containing the task index.
     * @return The Task object that was removed.
     * @throws JacksonException If the index is missing, not a number, or out of bounds.
     */
    public static Task deleteTask(String line) throws JacksonException {
        try {
            String[] inputs = splitInput(line, " ", 0);
            int taskToDelete = Integer.parseInt(inputs[1]) - 1;
            return TaskList.deleteTask(taskToDelete);
        } catch (NumberFormatException e) {
            throw new JacksonException("Eh, pls give a valid task number");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new JacksonException("Bro what task do u wanna delete?");
        } catch (IndexOutOfBoundsException e) {
            throw new JacksonException("Error, the task doesn't exist");
        }
    }

    /**
     * Parses a mark/unmark command string and updates the task status.
     *
     * @param tasks The TaskList containing the task to update.
     * @param line The raw command string containing the task index.
     * @return The 1-based index of the task that was updated.
     * @throws JacksonException If the index is invalid or out of bounds.
     */
    public static int updateMarkStatus(TaskList tasks, String line) throws JacksonException {
        try {
            String[] inputs = splitInput(line, " ", 0);

            int taskNumber = Integer.parseInt(inputs[1].trim());

            if (taskNumber > tasks.size() || taskNumber <= 0) {
                throw new JacksonException("Bro that task doesn't exist");
            }

            TaskList.updateMarkStatus(inputs[0].trim(), taskNumber - 1);

            return taskNumber;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new JacksonException("Pls provide a task number to mark");
        } catch (NumberFormatException e) {
            throw new JacksonException("Bro ur task number ain't valid lol");
        }
    }

    /**
     * Searches for tasks matching a keyword and prints them to the UI.
     *
     * @param line The raw find command containing the search keyword.
     * @param tasks The TaskList to search within.
     * @return True if at least one matching task was found, false otherwise.
     * @throws JacksonException If the search keyword is missing.
     */
    public static boolean findTask(String line, TaskList tasks) throws JacksonException {
        try {
            String keyword = Parser.splitInput(line, " ", 2)[1].trim();
            boolean taskExists = false;
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.findTask(i, keyword)) {
                    Ui.showMessageNoLine((i + 1) + ".");
                    Ui.showMessage(String.valueOf(TaskList.getItemAtIndex(i)));
                    taskExists = true;
                }
            }
            return taskExists;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new JacksonException("bro u didn't say any keyword");
        }
    }
}
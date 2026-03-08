package jackson.parser;

import jackson.Jackson;
import jackson.command.*;
import jackson.exception.JacksonException;
import jackson.task.Task;
import jackson.tasklist.TaskList;

public class Parser {
    public static String getCommandWord(String line) {
        return line.trim().split(" ")[0];
    }

    public static String[] splitInput(String line, String splitCharacter, int maxSplit) {
        return line.trim().split("\\s*" + splitCharacter + "\\s*", maxSplit);
    }

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

        case "bye":
            return new ExitCommand();

        default:
            return new InvalidCommand();
        }
    }

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
}
package jackson.parser;

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

    public static Task deleteTask(String line) throws JacksonException {
        try {
            String[] inputs = Parser.splitInput(line, " ", 0);
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
}
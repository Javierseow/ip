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
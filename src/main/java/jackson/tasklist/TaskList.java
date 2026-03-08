package jackson.tasklist;

import jackson.exception.JacksonException;
import jackson.parser.Parser;
import jackson.task.Deadline;
import jackson.task.Event;
import jackson.task.Task;
import jackson.task.Todo;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(String line) throws JacksonException {
        String[] commandAndDescription = Parser.splitInput(line, " ", 2);

        if (commandAndDescription.length < 2) {
            throw new JacksonException("Bro ur " + commandAndDescription[0] + " got no description");
        }
        String command = commandAndDescription[0];
        String description = commandAndDescription[1];

        if (command.equals("todo")) {
            tasks.add(new Todo(description));
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
            tasks.add(new Deadline(taskInfo[0].trim(), taskInfo[1].trim().substring(3).trim()));
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
        tasks.add(new Event(taskInfo[0].trim(),
                taskInfo[1].trim().substring(5).trim(),
                taskInfo[2].trim().substring(3).trim()));
    }

    public static Task deleteTask(int indexToRemove) {
        return tasks.remove(indexToRemove);
    }

    public static void updateMarkStatus(String command, int taskNumber) throws JacksonException {
        if (command.equals("mark")) {
            getItemAtIndex(taskNumber).markAsDone();
        } else {
            getItemAtIndex(taskNumber).markAsUndone();
        }
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    public static Task getItemAtIndex(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}

package jackson.tasklist;

import jackson.Jackson;
import jackson.exception.JacksonException;
import jackson.parser.Parser;
import jackson.task.Deadline;
import jackson.task.Event;
import jackson.task.Task;
import jackson.task.Todo;
import jackson.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

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

    public void deleteTask(String line) throws JacksonException {
        try {
            String[] inputs = Parser.splitInput(line, " ", 0);

            int indexToRemove = Integer.parseInt(inputs[1]) - 1;
            Task removedItem = tasks.remove(indexToRemove);
            System.out.println("Aight. I've removed this task:");
            System.out.print("  ");
            System.out.println(removedItem);
        } catch (NumberFormatException e) {
            throw new JacksonException("Eh, pls give a valid task number");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new JacksonException("Bro what task do u wanna delete?");
        } catch (IndexOutOfBoundsException e) {
            throw new JacksonException("Error, the task doesn't exist");
        }
    }

    public int updateMarkStatus(TaskList tasks, String line) throws JacksonException {
        try {
            String[] inputs = Parser.splitInput(line, " ", 0);

            int taskNumber = Integer.parseInt(inputs[1].trim());

            if (taskNumber > tasks.size() || taskNumber <= 0) {
                throw new JacksonException("Bro that task doesn't exist");
            }

            if (inputs[0].startsWith("mark")) {
                tasks.getItemAtIndex(taskNumber - 1).markAsDone();
            } else {
                tasks.getItemAtIndex(taskNumber - 1).markAsUndone();
            }
            return taskNumber;

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new JacksonException("Pls provide a task number to mark");
        } catch (NumberFormatException e) {
            throw new JacksonException("Bro ur task number ain't valid lol");
        }
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    public Task getItemAtIndex(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}

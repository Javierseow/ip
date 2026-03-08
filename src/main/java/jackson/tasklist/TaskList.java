package jackson.tasklist;

import jackson.exception.JacksonException;
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

    public static void addTodo(String description) {
        tasks.add(new Todo(description));
    }

    public static void addDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
    }

    public static void addEvent(String description, String from, String to) {
        tasks.add(new Event(description, from, to));
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

    public void findTask(String line) throws JacksonException {
        try {
            String keyword = Parser.splitInput(line, " ", 2)[1].trim();
            Ui.showMessage("Here are the matching tasks in your list:");
            boolean taskExists = false;
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getDescription().contains(keyword)) {
                    System.out.print((i + 1) + ".");
                    System.out.println(tasks.get(i));
                    taskExists = true;
                }
            }
            if (!taskExists) {
                Ui.showMessage("Rip bro got no tasks that match your word");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new JacksonException("bro u didn't say any keyword");
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

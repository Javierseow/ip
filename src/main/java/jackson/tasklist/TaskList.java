package jackson.tasklist;

import jackson.exception.JacksonException;
import jackson.parser.Parser;
import jackson.task.Deadline;
import jackson.task.Event;
import jackson.task.Task;
import jackson.task.Todo;
import jackson.ui.Ui;

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

    public boolean findTask(int index, String keyword) throws JacksonException {
        return tasks.get(index).getDescription().contains(keyword);
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

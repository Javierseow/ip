package jackson.tasklist;

import jackson.exception.JacksonException;
import jackson.task.Deadline;
import jackson.task.Event;
import jackson.task.Task;
import jackson.task.Todo;

import java.util.ArrayList;

/**
 * Contains the task list and has operations to add, delete, and modify tasks in the list.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Initializes an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a new Todo task to the list.
     *
     * @param description The description of the todo task.
     */
    public static void addTodo(String description) {
        tasks.add(new Todo(description));
    }

    /**
     * Adds a new Deadline task to the list.
     *
     * @param description The description of the deadline task.
     * @param by The deadline time/date.
     */
    public static void addDeadline(String description, String by) {
        tasks.add(new Deadline(description, by));
    }

    /**
     * Adds a new Event task to the list.
     *
     * @param description The description of the event.
     * @param from The start time/date.
     * @param to The end time/date.
     */
    public static void addEvent(String description, String from, String to) {
        tasks.add(new Event(description, from, to));
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param indexToRemove The 0-based index of the task to be deleted.
     * @return The Task object that was removed.
     */
    public static Task deleteTask(int indexToRemove) {
        return tasks.remove(indexToRemove);
    }

    /**
     * Updates the completion status of a task based on the command provided.
     *
     * @param command The string "mark" to complete or "unmark" to incomplete.
     * @param taskNumber The 0-based index of the task to update.
     * @throws JacksonException If the task cannot be accessed.
     */
    public static void updateMarkStatus(String command, int taskNumber) throws JacksonException {
        if (command.equals("mark")) {
            getItemAtIndex(taskNumber).markAsDone();
        } else {
            getItemAtIndex(taskNumber).markAsUndone();
        }
    }

    /**
     * Checks if a specific task's description contains the search keyword.
     *
     * @param index The 0-based index of the task to check.
     * @param keyword The string to search for within the description.
     * @return True if the keyword is found, false otherwise.
     * @throws JacksonException If the task cannot be accessed.
     */
    public boolean findTask(int index, String keyword) throws JacksonException {
        return tasks.get(index).getDescription().contains(keyword);
    }

    /**
     * Checks if the task list is currently empty.
     *
     * @return True if there are no tasks, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the number of tasks currently in the list.
     *
     * @return The size of the internal ArrayList.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The 0-based index of the task to retrieve.
     * @return The Task at the specified position.
     */
    public static Task getItemAtIndex(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the underlying ArrayList of tasks.
     *
     * @return The list of all Task objects.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
package jackson.ui;

import jackson.task.Task;
import jackson.tasklist.TaskList;
import java.util.Scanner;

/**
 * Deals with interactions with the user.
 * This class handles reading user input and displaying messages to the console.
 */
public class Ui {
    private Scanner sc;

    /**
     * Initializes a new Ui instance and sets up the scanner for user input.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        showMessage("Hello, I'm Jackson\nWhat can I do for you?");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        showMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Reads the next line of input from the user.
     *
     * @return The full command entered by the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints a message followed by a new line.
     *
     * @param message The message to be displayed.
     */
    public static void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints a message without a new line.
     *
     * @param message The message to be displayed.
     */
    public static void showMessageNoLine(String message) {
        System.out.print(message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public static void showErrorMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints all tasks currently in the task list.
     * If the list is empty, a notification is shown.
     *
     * @param tasks The TaskList containing tasks to be printed.
     */
    public void printList(TaskList tasks) {
        if (tasks.isEmpty()) {
            showMessage("Your list is empty bro");
            return;
        }

        System.out.println("Here's your list bro");
        for (int i = 0; i < tasks.size(); i++) {
            showMessageNoLine((i + 1) + ".");
            showMessage(String.valueOf(TaskList.getItemAtIndex(i)));
        }
    }

    /**
     * Prints the current number of tasks in the list with proper pluralization.
     *
     * @param tasks The TaskList to count.
     */
    public void printNumberOfItems(TaskList tasks) {
        showMessageNoLine("Now you have " + tasks.size() + " task");
        if (tasks.size() != 1) {
            showMessageNoLine("s");
        }
        showMessage(" in the list");
    }

    /**
     * Prints a status update when a task is marked or unmarked.
     *
     * @param command    The command used (mark or unmark).
     * @param taskNumber The 1-based index of the task.
     * @param task       The task object that was updated.
     */
    public void printStatus(String command, int taskNumber, Task task) {
        String status = command.startsWith("mark") ? "done" : "not done yet";
        showMessage("Okay, I've marked task " + taskNumber + " as " + status);
        showMessageNoLine("  ");
        showMessage(String.valueOf(task));
    }

    /**
     * Prints a confirmation that a task has been added.
     *
     * @param taskAdded The task that was added to the list.
     */
    public void printTaskAdded(Task taskAdded) {
        showMessage("Aight. I've added this task:");
        showMessageNoLine("  ");
        showMessage(String.valueOf(taskAdded));
    }

    /**
     * Prints a confirmation that a task has been removed.
     *
     * @param taskDeleted The task that was removed from the list.
     */
    public void printTaskDeleted(Task taskDeleted) {
        showMessage("Aight. I've removed this task:");
        showMessageNoLine("  ");
        showMessage(String.valueOf(taskDeleted));
    }
}
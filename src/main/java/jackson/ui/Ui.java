package jackson.ui;

import jackson.task.Task;
import jackson.tasklist.TaskList;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        showMessage("Hello, I'm Jackson\nWhat can I do for you?");
    }

    public void showGoodbye() {
        showMessage("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }
    public static void showMessageNoLine(String message) {
        System.out.print(message);
    }

    public static void showErrorMessage(String message) {
        System.out.println(message);
    }

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

    public void printNumberOfItems(TaskList tasks) {
        showMessageNoLine("Now you have " + tasks.size() + " task");
        if (tasks.size() != 1) {
            showMessageNoLine("s");
        }
        showMessage(" in the list");
    }

    public void printStatus(String command, int taskNumber, Task task) {
        String status = command.startsWith("mark") ? "done" : "not done yet";
        showMessage("Okay, I've marked task " + taskNumber + " as " + status);
        showMessageNoLine("  ");
        showMessage(String.valueOf(task));
    }

    public void printTaskAdded(Task taskAdded) {
        showMessage("Aight. I've added this task:");
        showMessageNoLine("  ");
        showMessage(String.valueOf(taskAdded));
    }

    public void printTaskDeleted(Task taskDeleted) {
        showMessage("Aight. I've removed this task:");
        showMessageNoLine("  ");
        showMessage(String.valueOf(taskDeleted));
    }
}
package jackson.ui;

import jackson.task.Task;
import jackson.tasklist.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello, I'm Jackson\nWhat can I do for you?");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
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
            System.out.println("Your list is empty bro");
            return;
        }

        System.out.println("Here's your list bro");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(tasks.getItemAtIndex(i));
        }
    }

    public void printNumberOfItems(TaskList tasks) {
        System.out.print("Now you have " + tasks.size() + " task");
        if (tasks.size() != 1) {
            System.out.print("s");
        }
        System.out.println(" in the list");
    }

    public void printStatus(String command, int taskNumber, Task task) {
        String status = command.startsWith("mark") ? "done" : "not done yet";
        System.out.println("Okay, I've marked task " + taskNumber + " as " + status);
        System.out.print("  ");
        System.out.println(task);
    }

    public void printTaskAdded(Task taskAdded) {
        System.out.println("Aight. I've added this task:");
        System.out.print("  ");
        System.out.println(taskAdded);
    }
}
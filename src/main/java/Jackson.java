import java.util.Scanner;

public class Jackson {
    static final int MAX_SIZE = 100;

    public static void printList(Task[] items) {
        if (Task.getTaskCount() == 0) {
            System.out.println("Your list is empty bro");
            return;
        }

        System.out.println("Here's your list bro");
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.print((i + 1) + ".");
            System.out.println(items[i]);
        }
    }

    public static void updateMarkStatus(Task[] items, String instruction, int taskNumber) {
        if (instruction.startsWith("mark")) {
            items[taskNumber - 1].markAsDone();
        } else {
            items[taskNumber - 1].markAsUndone();
        }
    }

    public static void addTask(Task[] items, String taskType, String description) {
        if (taskType.equals("todo")) {
            items[Task.getTaskCount()] = new Todo(description);
            return;
        }

        String[] taskInfo = description.split("/");
        if (taskType.equals("deadline")) {
            items[Task.getTaskCount()] = new Deadline(taskInfo[0].trim(),
                    taskInfo[1].trim().substring(3).trim());
            return;
        }

        items[Task.getTaskCount()] = new Event(taskInfo[0].trim(),
                taskInfo[1].trim().substring(5).trim(),
                taskInfo[2].trim().substring(3).trim());
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello, I'm Jackson");
        System.out.println("What can I do for you?");

        String line = in.nextLine();
        Task[] items = new Task[MAX_SIZE];

        while (!line.equals("bye")) {
            String[] words = line.trim().split(" ", 2);
            switch (words[0]) {
            case "list":
                printList(items);
                break;

            case "mark":
            case "unmark":
                int taskNumber = Integer.parseInt(words[1].trim());

                if (taskNumber > Task.getTaskCount() || taskNumber <= 0) {
                    System.out.println("Error, invalid task");
                    break;
                }

                updateMarkStatus(items, words[0], taskNumber);
                String status = line.startsWith("mark") ? "done" : "not done yet";
                System.out.println("Okay, I've marked task " + taskNumber + " as " + status);
                System.out.print("  ");
                System.out.println(items[taskNumber - 1]);
                break;

            case "todo":
            case "deadline":
            case "event":
                System.out.println("Got it. I've added this task:");
                System.out.print("  ");
                addTask(items, words[0], words[1].trim());
                System.out.println(items[Task.getTaskCount() - 1]);
                System.out.print("Now you have " + Task.getTaskCount() + " task");
                if (Task.getTaskCount() > 1) {
                    System.out.print("s");
                }
                System.out.println(" in the list");
                break;

            default:
                System.out.println("Yo bro your instruction is invalid");
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

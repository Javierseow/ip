import java.util.Scanner;

public class Jackson {

    public static void printList(Task[] items) {
        if (Task.getTaskCount() == 0) {
            System.out.println("Your list is empty bro");
        } else {
            System.out.println("Here's your list bro");
            for (int i = 0; i < Task.getTaskCount(); i++) {
                System.out.print((i+1) + ". ");
                System.out.print("[" + items[i].getStatusIcon() + "] ");
                System.out.println(items[i].getDescription());
            }
        }
    }

    public static void updateMarkStatus (Task[] items, String instruction, int taskNumber) {
        if (instruction.startsWith("mark")) {
            items[taskNumber-1].markAsDone();
        } else {
            items[taskNumber-1].markAsUndone();
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello, I'm Jackson");
        System.out.println("What can I do for you?");

        String line = in.nextLine();
        Task[] items = new Task[100];

        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printList(items);
            } else if (line.startsWith("mark") || line.startsWith("unmark")) {
                String[] words = line.split(" ");
                int taskNumber = Integer.parseInt(words[1]);
                if (taskNumber > Task.getTaskCount() || taskNumber <= 0) {
                    System.out.println("Error, invalid task");
                } else {
                    updateMarkStatus(items, words[0], taskNumber);
                    if (line.startsWith("mark")) {
                        System.out.println("Okay, I've marked task "
                                + taskNumber + " as done");
                    } else {
                        System.out.println("Okay, I've marked task "
                                + taskNumber + " as not done yet");
                    }
                }
            } else {
                System.out.println("Added: " + line);
                items[Task.getTaskCount()] = new Task(line);
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

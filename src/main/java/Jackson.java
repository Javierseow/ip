import java.util.Scanner;

public class Jackson {

    public static void printList(String[] items, int itemCount) {
        if (itemCount == 0) {
            System.out.println("Your list is empty bro");
        } else {
            for (int i = 0; i < itemCount; i++) {
                System.out.print((i+1) + ". ");
                System.out.println(items[i]);
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello, I'm Jackson");
        System.out.println("What can I do for you?");

        String line = in.nextLine();
        String[] items = new String[100];
        int itemCount = 0;

        while (!line.equals("bye")) {
            if (line.equals("list")) {
                printList(items, itemCount);
            } else {
                System.out.println("Added: " + line);
                items[itemCount] = line;
                itemCount++;
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

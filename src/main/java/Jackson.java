import java.util.Scanner;

public class Jackson {
    public static void main(String[] args) {
        String line = "";

        Scanner in = new Scanner(System.in);
        System.out.println("Hello, I'm Jackson");
        System.out.print("What can I do for you?");
        while (!line.equals("bye")) {
            System.out.println(line);
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}

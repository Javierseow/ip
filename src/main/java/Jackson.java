import java.util.Scanner;

public class Jackson {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        System.out.println("Hello, I'm Jackson");
        System.out.println("What can I do for you?");
        do {
            line = in.nextLine();
            System.out.println(line);
        } while (!line.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
    }
}

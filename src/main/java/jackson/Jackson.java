package jackson;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import jackson.task.Task;
import jackson.task.Todo;
import jackson.task.Deadline;
import jackson.task.Event;
import jackson.exception.JacksonException;

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

    public static void addTask(Task[] items, String taskType, String description) throws JacksonException{
        if (taskType.equals("todo")) {
            items[Task.getTaskCount()] = new Todo(description);
            return;
        }

        String[] taskInfo = description.split("/");
        if (taskInfo.length < 1 || taskInfo[0].isEmpty()) {
            throw new JacksonException("Bro ur " + taskType + " got no description");
        }

        if (taskType.equals("deadline")) {
            if (taskInfo.length < 2 || !taskInfo[1].trim().startsWith("by ")) {
                throw new JacksonException("Yo u forgot to specify ur deadline time");
            }
            items[Task.getTaskCount()] = new Deadline(taskInfo[0].trim(),
                    taskInfo[1].trim().substring(3).trim());
            return;
        }

        if (taskInfo.length < 3) {
            throw new JacksonException("Dude u didn't tell me your event duration properly");
        }
        if (!taskInfo[1].trim().startsWith("from ")) {
            throw new JacksonException("Gotta tell me when your event starts bro");
        }
        if (!taskInfo[2].trim().startsWith("to ")) {
            throw new JacksonException("Gotta tell me when your event ends bro");
        }
        items[Task.getTaskCount()] = new Event(taskInfo[0].trim(),
                taskInfo[1].trim().substring(5).trim(),
                taskInfo[2].trim().substring(3).trim());
    }

    public static void makeFile() {
        try {
            File folder = new File("./data");
            folder.mkdirs();

            File file = new File("./data/jackson.txt");
            file.createNewFile();

        } catch (IOException e) {
            System.out.println("Error loading file" + e.getMessage());
        }
    }

    public static void loadFile(Task[] items) {
        try {
            File file = new File("./data/jackson.txt");
            Scanner fileIn = new Scanner(file);

            while (fileIn.hasNextLine()) {
                String line = fileIn.nextLine();
                String[] taskInfo = line.split("\\|");
                addTask(items, taskInfo[0].trim(), taskInfo[2].trim());
                if (taskInfo[1].trim().equals("X")) {
                    updateMarkStatus(items, "mark", Task.getTaskCount());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading file");
        } catch (JacksonException e) {
            System.out.println("Error loading past data");
        }
    }

    public static void saveCurrentData(Task[] items) {
        try {
            FileWriter fileWriter = new FileWriter("./data/jackson.txt");
            for (Task item : items) {
                if (item == null) {
                    continue;
                }
                String lineToSave = "";
                if (item instanceof Todo) {
                    lineToSave = "todo | " + item.getStatusIcon() + " | " + item.getDescription();
                } else if (item instanceof Deadline deadlineTask) {
                    lineToSave = "deadline | " + deadlineTask.getStatusIcon() + " | " + deadlineTask.getDescription()
                            + " /by " + deadlineTask.getBy();
                } else if (item instanceof Event eventTask) {
                    lineToSave = "event | " + eventTask.getStatusIcon() + " | " + eventTask.getDescription()
                            + " /from " + eventTask.getFrom() + " /to " + eventTask.getTo();
                } else {
                    throw new IOException();
                }
                fileWriter.write(lineToSave + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks");
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello, I'm Jackson");
        System.out.println("What can I do for you?");

        Task[] items = new Task[MAX_SIZE];
        makeFile();
        loadFile(items);
        String line = in.nextLine();

        while (!line.equals("bye")) {
            String[] words = line.trim().split(" ", 2);
            switch (words[0]) {
            case "list":
                printList(items);
                break;

            case "mark":
            case "unmark":
                try {
                    int taskNumber = Integer.parseInt(words[1].trim());

                    if (taskNumber > Task.getTaskCount() || taskNumber <= 0) {
                        throw new JacksonException("Bro that task doesn't exist");
                    }

                    updateMarkStatus(items, words[0], taskNumber);
                    String status = line.startsWith("mark") ? "done" : "not done yet";
                    System.out.println("Okay, I've marked task " + taskNumber + " as " + status);
                    System.out.print("  ");
                    System.out.println(items[taskNumber - 1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Pls provide a task number to mark");
                } catch (NumberFormatException e) {
                    System.out.println("Bro ur task number ain't valid lol");
                } catch (JacksonException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case "todo":
            case "deadline":
            case "event":
                try {
                    if (words.length < 2) {
                        throw new JacksonException("Bro ur " + words[0] + " got no description");
                    }
                    addTask(items, words[0], words[1].trim());

                    System.out.println("Got it. I've added this task:");
                    System.out.print("  ");
                    System.out.println(items[Task.getTaskCount() - 1]);
                    System.out.print("Now you have " + Task.getTaskCount() + " task");
                    if (Task.getTaskCount() > 1) {
                        System.out.print("s");
                    }
                    System.out.println(" in the list");
                } catch (JacksonException e) {
                    System.out.println(e.getMessage());
                }
                break;

            default:
                System.out.println("Yo bro your instruction is invalid");
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        saveCurrentData(items);
    }
}

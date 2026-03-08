package jackson.storage;

import jackson.parser.Parser;
import jackson.task.Task;
import jackson.task.Todo;
import jackson.task.Event;
import jackson.task.Deadline;
import jackson.exception.JacksonException;
import jackson.tasklist.TaskList;
import jackson.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 * This class handles all disk I/O operations for the Jackson application.
 */
public class Storage {
    private final String filePath;

    /**
     * Initializes a Storage instance with a specific file path.
     *
     * @param filePath The path to the data file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates the data directory and file if they do not already exist.
     *
     * @throws JacksonException If an I/O error occurs during directory or file creation.
     */
    public void makeFile() throws JacksonException {
        try {
            File folder = new File("./data");
            folder.mkdirs();

            File file = new File(filePath);
            file.createNewFile();

        } catch (IOException e) {
            throw new JacksonException("Error loading file" + e.getMessage());
        }
    }

    /**
     * Loads the task list from the hard drive.
     * Parses each line of the data file and populates a new TaskList.
     *
     * @return A TaskList populated with tasks from the file.
     * @throws JacksonException If the file is missing or contains corrupted data.
     */
    public TaskList loadFile() throws JacksonException {
        try {
            File file = new File(filePath);
            Scanner fileIn = new Scanner(file);
            TaskList tasks = new TaskList();

            while (fileIn.hasNextLine()) {
                String line = fileIn.nextLine();
                String[] lineSplit = line.split("\\|");
                Parser.addTask(lineSplit[1].trim());
                if (lineSplit[0].trim().equals("X")) {
                    Parser.updateMarkStatus(tasks, "mark " + tasks.size());
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new JacksonException("Error loading file");
        } catch (JacksonException | ArrayIndexOutOfBoundsException e) {
            throw new JacksonException("Errors found in file, creating new list for you");
        }
    }

    /**
     * Saves the current task list to the hard drive.
     * Formats tasks into a text-based representation for storage.
     *
     * @param tasks The TaskList containing the tasks to be saved.
     */
    public void saveCurrentData(TaskList tasks) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                if (task == null) {
                    continue;
                }
                String lineToSave;
                if (task instanceof Todo) {
                    lineToSave = task.getStatusIcon() + " | todo " + task.getDescription();
                } else if (task instanceof Deadline deadlineTask) {
                    lineToSave = deadlineTask.getStatusIcon() + " | deadline " + deadlineTask.getDescription()
                            + " /by " + deadlineTask.getBy();
                } else if (task instanceof Event eventTask) {
                    lineToSave = eventTask.getStatusIcon() + " | event " + eventTask.getDescription()
                            + " /from " + eventTask.getFrom() + " /to " + eventTask.getTo();
                } else {
                    throw new IOException();
                }
                fileWriter.write(lineToSave + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            Ui.showErrorMessage("Error saving tasks");
        }
    }
}
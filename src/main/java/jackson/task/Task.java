package jackson.task;

/**
 * Represents a generic task in the Jackson application.
 * A task consists of a description and a completion status.
 */
public class Task {
    /** The description of the task. */
    protected String description;
    /** The completion status of the task. */
    protected boolean isDone;

    /**
     * Initializes a new Task with the specified description.
     * By default, the task is marked as not done.
     *
     * @param description The text describing the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "X" represents a completed task, while a blank space represents an incomplete one.
     *
     * @return A string containing the status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
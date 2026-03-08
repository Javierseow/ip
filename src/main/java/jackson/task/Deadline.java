package jackson.task;

/**
 * Represents a deadline type task in the Jackson application.
 * A deadline is a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    /** The date or time by which the task must be completed. */
    protected String by;

    /**
     * Initializes a new Deadline task with the specified description and deadline time.
     *
     * @param description The text describing the deadline task.
     * @param by The date or time the task is due.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the deadline time of the task.
     *
     * @return A string representing the due date/time.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns a string representation of the deadline task, including the [D] prefix
     * and the deadline details.
     *
     * @return A formatted string representing the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
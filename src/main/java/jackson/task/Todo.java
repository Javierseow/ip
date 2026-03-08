package jackson.task;

/**
 * Represents a todo type task in the Jackson application.
 * A todo is a basic task that only contains a description.
 */
public class Todo extends Task {

    /**
     * Initializes a new Todo task with the specified description.
     *
     * @param description The text describing the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task, including the [T] prefix.
     *
     * @return A formatted string representing the todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
package jackson.task;

/**
 * Represents an event type task in the Jackson application.
 * An event is a task that occurs within a specific time frame,
 * starting at a certain time and ending at another.
 */
public class Event extends Task {
    /** The start time or date of the event. */
    protected String to;
    /** The end time or date of the event. */
    protected String from;

    /**
     * Initializes a new Event task with the specified description and duration.
     *
     * @param description The text describing the event.
     * @param from The start time or date of the event.
     * @param to The end time or date of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.to = to;
        this.from = from;
    }

    /**
     * Returns the start time of the event.
     *
     * @return A string representing the start time.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return A string representing the end time.
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns a string representation of the event task, including the [E] prefix
     * and the duration details.
     *
     * @return A formatted string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
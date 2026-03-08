package jackson.exception;

/**
 * Represents a custom exception specific to the Jackson application.
 * This class is used to signal errors that occur during the execution
 * of user commands or file operations.
 */
public class JacksonException extends Exception {
    /**
     * Constructs a new JacksonException with the specified detail message.
     *
     * @param message The error message explaining the cause of the exception.
     */
    public JacksonException(String message) {
        super(message);
    }
}

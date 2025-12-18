package game;

/**
 * Exception thrown when invalid user input or game data is detected.
 * <p>
 * Used to signal validation errors such as invalid moves or
 * incorrect user choices.
 * </p>
 */
public class ValidationException extends Exception {

    /**
     * Creates a new ValidationException with the specified message.
     *
     * @param message a description of the validation error
     */
    public ValidationException(String message) {
        super(message);
    }
}

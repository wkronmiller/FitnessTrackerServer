package net.kronmiller.william.fitness.exceptions;

/**
 * Created by wkronmiller on 7/15/17.
 */
public class InvalidUserException extends AppException {
    public InvalidUserException(String message) {
        super("Invalid User: " + message);
    }
}

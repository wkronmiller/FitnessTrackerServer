package net.kronmiller.william.fitness.exceptions;

/**
 * Created by wkronmiller on 7/15/17.
 */
public class DuplicateRecordException extends AppException {
    public DuplicateRecordException() {
        super("Attempted to insert duplicate record");
    }
    public DuplicateRecordException(String message) {
        super(message);
    }
}

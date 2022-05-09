package Exceptions;

public class OperationNotAllowed extends Exception {

    /**
     * A new exception is constructed with error message errorMessage.
     * @param errorMessage the error message of the exception
     */
    public OperationNotAllowed(String errorMessage) {
        super(errorMessage);
    }
}

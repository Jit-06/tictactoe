package exception;

public class InvalidBoardSizeException extends Exception{

    public InvalidBoardSizeException() {
        super();
    }

    public InvalidBoardSizeException(String message) {
        super(message);
    }

    public InvalidBoardSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidBoardSizeException(Throwable cause) {
        super(cause);
    }

    protected InvalidBoardSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

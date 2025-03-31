package exception;

public class InvalidNumberOfPlayerException extends Exception{

    public InvalidNumberOfPlayerException() {
        super();
    }

    protected InvalidNumberOfPlayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InvalidNumberOfPlayerException(Throwable cause) {
        super(cause);
    }

    public InvalidNumberOfPlayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidNumberOfPlayerException(String message) {
        super(message);
    }
}

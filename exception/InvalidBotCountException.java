package exception;

public class InvalidBotCountException extends Exception{

    public InvalidBotCountException() {
        super();
    }

    public InvalidBotCountException(String message) {
        super(message);
    }

    public InvalidBotCountException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidBotCountException(Throwable cause) {
        super(cause);
    }

    protected InvalidBotCountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

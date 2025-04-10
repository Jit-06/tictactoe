package exception;

public class DuplicateSymbolException extends Exception{

    public DuplicateSymbolException() {
        super();
    }

    public DuplicateSymbolException(String message) {
        super(message);
    }

    public DuplicateSymbolException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateSymbolException(Throwable cause) {
        super(cause);
    }

    protected DuplicateSymbolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

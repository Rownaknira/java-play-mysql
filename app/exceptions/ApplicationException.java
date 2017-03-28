package exceptions;

/**
 * Created by rownak on 3/27/17.
 */
public class ApplicationException extends RuntimeException {
    private String message;

    public ApplicationException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}

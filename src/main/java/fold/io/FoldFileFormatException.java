package fold.io;

import java.io.IOException;

/**
 * Generic exception thrown when fold file reading or writing fails.
 */
public class FoldFileFormatException extends IOException {
    public FoldFileFormatException(String message) {
        super(message);
    }

    public FoldFileFormatException(Throwable cause) {
        super(cause);
    }
}

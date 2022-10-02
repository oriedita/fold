package fold.io;

import java.io.IOException;

/**
 * Generic exception thrown when fold file reading or writing fails.
 */
public class FoldFileFormatException extends IOException {
    /**
     * Create a new FoldFileFormatException.
     *
     * @param message Message to report in this exception.
     */
    public FoldFileFormatException(String message) {
        super(message);
    }

    /**
     * Create a new FoldFileFormatException from an IOException.
     *
     * @param cause An exception caused while processing a fold file.
     */
    public FoldFileFormatException(IOException cause) {
        super(cause);
    }
}

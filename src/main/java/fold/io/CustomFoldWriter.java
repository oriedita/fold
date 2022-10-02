package fold.io;

import fold.model.FoldFile;

import java.io.IOException;
import java.io.OutputStream;

import static fold.io.impl.Fold.json;

/**
 * Custom fold writer, also writes subclasses of {@link FoldFile}.
 *
 * @param <T> A subclass of {@link FoldFile} to write to.
 */
public class CustomFoldWriter<T extends FoldFile> {
    private final OutputStream outputStream;

    /**
     * Create a new CustomFoldWriter instance.
     *
     * @param outputStream The outputStream to write to.
     */
    public CustomFoldWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    /**
     * Write the FoldFile to the outputStream.
     *
     * @param foldFile The (subclass of) {@link FoldFile} to write to the outputStream.
     * @throws FoldFileFormatException If writing to the outputStream causes an exception.
     */
    public void write(T foldFile) throws FoldFileFormatException {
        try {
            json.write(foldFile, outputStream);
        } catch (IOException e) {
            throw new FoldFileFormatException(e);
        }
    }
}

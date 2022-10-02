package fold.io;

import fold.model.FoldFile;

import java.io.*;

import static fold.io.impl.Fold.json;

/**
 * Custom fold writer, also writes subclasses of {@link FoldFile}.
 *
 * @param <T> A subclass of {@link FoldFile} to write to.
 */
public class CustomFoldWriter<T extends FoldFile> implements Writer<T> {
    private final OutputStream outputStream;

    public CustomFoldWriter(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    @Override
    public void write(T foldFile) throws FoldFileFormatException {
        try {
            json.write(foldFile, outputStream);
        } catch (IOException e) {
            throw new FoldFileFormatException(e);
        }
    }
}

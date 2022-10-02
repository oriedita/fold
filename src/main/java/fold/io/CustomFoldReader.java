package fold.io;

import fold.model.FoldFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static fold.io.impl.Fold.json;

/**
 * Custom fold Reader, writes a subclass of {@link FoldFile}.
 *
 * @param <T> A subclass of {@link FoldFile} to read into.
 */
public class CustomFoldReader<T extends FoldFile> implements Reader<T> {
    private final Class<T> tClass;
    private final InputStream inputStream;

    public CustomFoldReader(Class<T> tClass, InputStream inputStream) {
        this.tClass = tClass;
        this.inputStream = inputStream;
    }

    @Override
    public T read() throws IOException {
        try {
            return json.beanFrom(tClass, inputStream);
        } catch (IOException e) {
            throw new FoldFileFormatException(e);
        }
    }
}

package fold.io;

import com.fasterxml.jackson.jr.ob.JSON;
import fold.model.FoldFile;

import java.io.IOException;
import java.io.InputStream;

import static fold.io.impl.Fold.json;

/**
 * Custom fold Reader, writes a subclass of {@link FoldFile}.
 *
 * @param <T> A subclass of {@link FoldFile} to read into.
 */
public class CustomFoldReader<T extends FoldFile> {
    private final Class<T> tClass;
    private final InputStream inputStream;

    /**
     * Create a new CustomFoldReader instance.
     *
     * @param tClass      The (subclass of) {@link FoldFile} this reader can read from.
     * @param inputStream The inputStream to read from.
     */
    public CustomFoldReader(Class<T> tClass, InputStream inputStream) {
        this.tClass = tClass;
        this.inputStream = inputStream;
    }

    /**
     * Read a FoldFile from the given inputStream
     *
     * @return A (subclass of) {@link FoldFile}
     * @throws FoldFileFormatException If reading the inputStream causes an exception.
     */
    public T read() throws FoldFileFormatException {
        try {
            return getJson().beanFrom(tClass, inputStream);
        } catch (IOException e) {
            throw new FoldFileFormatException(e);
        }
    }

    protected JSON getJson() {
        return json;
    }
}

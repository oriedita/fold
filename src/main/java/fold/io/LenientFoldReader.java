package fold.io;

import com.fasterxml.jackson.jr.ob.JSON;
import fold.model.FoldFile;

import java.io.InputStream;

import static fold.io.impl.Fold.lenient;

/**
 * Does not throw an exception when unexpected fields are encountered.
 */
public class LenientFoldReader  extends CustomFoldReader<FoldFile> {
    /**
     * Create a new FoldReader from an InputStream.
     *
     * @param inputStream The InputStream to read from when reading this FoldFile.
     */
    public LenientFoldReader(InputStream inputStream) {
        super(FoldFile.class, inputStream);
    }

    @Override
    protected JSON getJson() {
        return lenient;
    }
}

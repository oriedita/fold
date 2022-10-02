package fold.io;

import fold.model.FoldFile;

import java.io.InputStream;

/**
 * Default fold reader, reads an {@link InputStream} into a {@link FoldFile}.
 */
public class FoldReader extends CustomFoldReader<FoldFile> {
    public FoldReader(InputStream inputStream) {
        super(FoldFile.class, inputStream);
    }
}

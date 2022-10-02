package fold.io;

import fold.model.FoldFile;

import java.io.OutputStream;

/**
 * Default fold writer, writes a {@link FoldFile} into an {@link OutputStream}.
 */
public class FoldWriter extends CustomFoldWriter<FoldFile> {
    /**
     * Create a new FoldWriter which writes to an OutputStream.
     *
     * @param outputStream The OutputStream to write the FoldFile to.
     */
    public FoldWriter(OutputStream outputStream) {
        super(outputStream);
    }
}

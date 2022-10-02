package fold.io;

import fold.model.FoldFile;

import java.io.OutputStream;

/**
 * Default fold writer, writes a {@link FoldFile} into an {@link OutputStream}.
 */
public class FoldWriter extends CustomFoldWriter<FoldFile> {
    public FoldWriter(OutputStream outputStream) {
        super(outputStream);
    }
}

package fold.io;

import fold.model.Edge;
import fold.model.FoldFile;

import java.io.*;

/**
 * Write Crease Pattern .cp files.
 *
 * Reads Edges and Vertices from the {@see FoldFile}.
 */
public class CreasePatternWriter {
    private final OutputStream out;

    public CreasePatternWriter(OutputStream out) {
        this.out = out;
    }

    /**
     * Write a fold file object to a crease pattern file.
     *
     * @param foldFile Lines to write
     * @throws FoldFileFormatException When writing fails.
     */
    public void write(FoldFile foldFile) throws FoldFileFormatException {
        try (OutputStreamWriter osw = new OutputStreamWriter(out); BufferedWriter bw = new BufferedWriter(osw); PrintWriter pw = new PrintWriter(bw)) {
            for (Edge e : foldFile.getRootFrame().getEdges()) {
                int color;
                switch (e.getAssignment()) {
                    case BORDER:
                        color = 1;
                        break;
                    case MOUNTAIN_FOLD:
                        color = 2;
                        break;
                    case VALLEY_FOLD:
                        color = 3;
                        break;
                    default:
                        color = 4;
                        break;
                }

                pw.println(String.format("%d %s %s %s %s", color, e.getStart().getX(), e.getStart().getY(), e.getEnd().getX(), e.getEnd().getY()));
            }
        } catch (IOException e) {
            throw new FoldFileFormatException(e);
        }
    }
}

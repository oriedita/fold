package fold.io;

import fold.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Read Crease Pattern .cp files.
 *
 * Resulting {@see FoldFile} only has Edges and Vertices.
 */
public class CreasePatternReader {
    private final InputStream in;

    public CreasePatternReader(InputStream in) {
        this.in = in;
    }

    private static FoldEdgeAssignment getFoldEdgeAssignment(String str) {
        FoldEdgeAssignment assignment;
        switch (str) {
            case "1":
                assignment = FoldEdgeAssignment.BORDER;
                break;
            case "2":
                assignment = FoldEdgeAssignment.MOUNTAIN_FOLD;
                break;
            case "3":
                assignment = FoldEdgeAssignment.VALLEY_FOLD;
                break;
            case "4":
            default:
                assignment = FoldEdgeAssignment.FLAT_FOLD;
                break;
        }
        return assignment;
    }

    /**
     * Read a .cp crease pattern file. Does not fail if the file is malformatted.
     *
     * @return A FoldFile instance from the crease pattern file.
     * @throws FoldFileFormatException If the file doesn't exist or is not readable.
     */
    public FoldFile read() throws FoldFileFormatException {
        try (InputStreamReader isr = new InputStreamReader(in); BufferedReader reader = new BufferedReader(isr)) {
            FoldFile foldFile = new FoldFile();
            FoldFrame frame = foldFile.getRootFrame();

            StringTokenizer tk;
            String fileLine;

            while ((fileLine = reader.readLine()) != null) {
                tk = new StringTokenizer(fileLine, " ");

                try {
                    // Important, note the order of nextToken calls.
                    frame.addEdge(new Edge(
                            getFoldEdgeAssignment(tk.nextToken()),
                            new Vertex(Double.parseDouble(tk.nextToken()), Double.parseDouble(tk.nextToken())),
                            new Vertex(Double.parseDouble(tk.nextToken()), Double.parseDouble(tk.nextToken()))
                    ));
                } catch (NoSuchElementException | NumberFormatException e) {
                    // Reading a token failed, just discard this line.
                    System.err.println("Encountered error line while parsing crease pattern, skipping. " + e.getMessage());
                }

            }

            return foldFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

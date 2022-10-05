package fold;

import fold.io.CreasePatternReader;
import fold.io.CreasePatternWriter;
import fold.io.FoldFileFormatException;
import fold.model.Edge;
import fold.model.FoldEdgeAssignment;
import fold.model.FoldFile;
import fold.model.Vertex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.Objects;

public class CreasePatternReaderTest {
    @Test
    public void testReadCreasePatternFile() throws FoldFileFormatException, FileNotFoundException {
        CreasePatternReader reader = new CreasePatternReader(new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("creasepattern/simple.cp")).getFile()));

        FoldFile file = reader.read();

        Assertions.assertEquals(List.of(
                new Edge(FoldEdgeAssignment.BORDER, new Vertex(1.0, 1.0), new Vertex(-1.0, -1.0)),
                new Edge(FoldEdgeAssignment.MOUNTAIN_FOLD, new Vertex(0.0, 0.0), new Vertex(1.0, 0.0))
        ), file.getRootFrame().getEdges());

        Assertions.assertEquals(List.of(
                new Vertex(1.0, 1.0),
                new Vertex(-1.0, -1.0),
                new Vertex(0.0, 0.0),
                new Vertex(1.0, 0.0)
        ), file.getRootFrame().getVertices());
    }

    /**
     * The crease pattern parser is very lenient when errors occur.
     */
    @Test
    public void testReadMalformedCreasePatternFile() throws FoldFileFormatException, FileNotFoundException {
        CreasePatternReader reader = new CreasePatternReader(new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("creasepattern/junk.cp")).getFile()));

        FoldFile file = reader.read();

        Assertions.assertEquals(List.of(
                new Edge(FoldEdgeAssignment.BORDER, new Vertex(1.0, 1.0), new Vertex(-1.0, -1.0)),
                new Edge(FoldEdgeAssignment.FLAT_FOLD, new Vertex(0.0, 0.0), new Vertex(1.0, 0.0))
        ), file.getRootFrame().getEdges());

        Assertions.assertEquals(List.of(
                new Vertex(1.0, 1.0),
                new Vertex(-1.0, -1.0),
                new Vertex(0.0, 0.0),
                new Vertex(1.0, 0.0)
        ), file.getRootFrame().getVertices());
    }

    @Test
    public void testWriteCreasePatternFile() throws FoldFileFormatException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CreasePatternWriter writer = new CreasePatternWriter(out);

        FoldFile file = new FoldFile();
        Edge e = new Edge(FoldEdgeAssignment.BORDER, new Vertex(0.0, 0.0), new Vertex(3.0, 4.0));
        file.getRootFrame().addEdge(e);

        writer.write(file);

        String data = out.toString();

        Assertions.assertEquals("1 0.0 0.0 3.0 4.0\n", replaceCrlf(data));
    }

    /**
     * Test if reading and writing a file results in the same value.
     */
    @Test
    public void testReadWriteCreasePatternFile() throws IOException {
        FileInputStream in = new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("creasepattern/birdbase.cp")).getFile());
        CreasePatternReader reader = new CreasePatternReader(in);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        CreasePatternWriter writer = new CreasePatternWriter(out);

        writer.write(reader.read());

        byte[] expected;
        try (FileInputStream in2 = new FileInputStream(Objects.requireNonNull(getClass().getClassLoader().getResource("creasepattern/birdbase.cp")).getFile())) {
            expected = in2.readAllBytes();
        }

        Assertions.assertArrayEquals(expected, out.toByteArray());
    }

    private String replaceCrlf(String string) {
        return string.replace("\r\n", "\n");
    }
}

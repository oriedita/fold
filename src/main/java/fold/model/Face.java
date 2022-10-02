package fold.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A face consists of a list of {@link Vertex} and a list of {@link Edge}.
 */
public class Face {
    private List<Vertex> vertices = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();

    /**
     * Get the list of vertices in this face.
     *
     * @return The vertices contained in this face.
     */
    public List<Vertex> getVertices() {
        return vertices;
    }

    /**
     * Set the list of vertices in this face.
     *
     * @param vertices The vertices contained in this face.
     */
    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    /**
     * Get the list of edges for this face.
     *
     * @return The edges that this face consists of.
     */
    public List<Edge> getEdges() {
        return edges;
    }

    /**
     * Set the list of edges for this face.
     *
     * @param edges The edges that this face consists of.
     */
    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}

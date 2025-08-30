package fold.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Holds a coordinate, a vertex is part of a list of {@link Face} it is part of and a list of {@link Vertex} which are adjacent.
 */
public class Vertex {
    public Vertex() {
    }

    public Vertex(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    private Double x;
    private Double y;
    private Double z;
    private List<Vertex> vertices = new ArrayList<>();
    private List<Face> faces = new ArrayList<>();

    /**
     * @since 1.2
     */
    private List<Edge> edges = new ArrayList<>();

    /**
     * Get the x coordinate of this vertex.
     *
     * @return The x coordinate of this vertex.
     */
    public Double getX() {
        return x;
    }

    /**
     * Set the x coordinate of this vertex.
     *
     * @param x The x coordinate of this vertex.
     */
    public void setX(Double x) {
        this.x = x;
    }

    /**
     * Get the y coordinate of this vertex.
     *
     * @return The y coordinate of this vertex.
     */
    public Double getY() {
        return y;
    }

    /**
     * Set the y coordinate of this vertex.
     *
     * @param y The y coordinate of this vertex.
     */
    public void setY(Double y) {
        this.y = y;
    }

    /**
     * Get the z coordinate of this vertex. This value is usually zero.
     *
     * @return The z coordinate of this vertex.
     */
    public Double getZ() {
        return z;
    }

    /**
     * Set the z coordinate of this vertex.
     *
     * @param z The z coordinate of this vertex.
     */
    public void setZ(Double z) {
        this.z = z;
    }

    /**
     * Get all vertices that are adjacent to this vertex.
     * <p>
     * This is a replacement for {@link Edge}, a frame with edges does not need this value.
     *
     * @return All vertices that are adjacent to this vertex.
     */
    public List<Vertex> getVertices() {
        return vertices;
    }

    /**
     * Set the vertices that are adjacent to this vertex.
     *
     * @param vertices The vertices that are adjacent to this vertex.
     */
    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    /**
     * Get all the faces this vertex is part of.
     *
     * @return The faces this vertex is part of.
     */
    public List<Face> getFaces() {
        return faces;
    }

    /**
     * Set the faces this vertex is part of.
     *
     * @param faces The faces this vertex is part of.
     */
    public void setFaces(List<Face> faces) {
        this.faces = faces;
    }

    /**
     * Get the edges this vertex is incident to.
     *
     * @return The edges this vertix is incident to.
     */
    public List<Edge> getEdges() {
        return edges;
    }

    /**
     * Set the edges this vertex is incident to.
     *
     * @param edges The edges this vertix is incident to.
     */
    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(getX(), vertex.getX()) && Objects.equals(getY(), vertex.getY()) && Objects.equals(getZ(), vertex.getZ()) && Objects.equals(getVertices(), vertex.getVertices()) && Objects.equals(getFaces(), vertex.getFaces()) && Objects.equals(getEdges(), vertex.getEdges());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ(), getVertices(), getFaces(), getEdges());
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", vertices=" + vertices +
                ", faces=" + faces +
                ", edges=" + edges +
                '}';
    }
}

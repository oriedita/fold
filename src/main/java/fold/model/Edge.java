package fold.model;

import java.util.Objects;

/**
 * Describes an edge between two {@link Vertex} objects.
 */
public class Edge {
    public Edge() {
    }

    public Edge(FoldEdgeAssignment assignment, Vertex start, Vertex end) {
        this.start = start;
        this.end = end;
        this.assignment = assignment;
    }

    private Vertex start;
    private Vertex end;
    private FoldEdgeAssignment assignment;
    private Double foldAngle;
    private Double length;

    /**
     * Get the start vertex of this edge.
     *
     * @return The start vertex of this edge.
     */
    public Vertex getStart() {
        return start;
    }

    /**
     * Set the start vertex of this edge. This vertex must also be present in vertices in {@link FoldFrame}.
     *
     * @param start The start vertex of this edge
     */
    public void setStart(Vertex start) {
        this.start = start;
    }

    /**
     * Set the start vertex of this edge.
     *
     * @return The start vertex of this edge.
     */
    public Vertex getEnd() {
        return end;
    }

    /**
     * Set the end vertex of this edge. This vertex must also be present in vertices in {@link FoldFrame}.
     *
     * @param end The end vertex of this edge
     */
    public void setEnd(Vertex end) {
        this.end = end;
    }

    /**
     * For each edge, a string representing its fold direction assignment:
     * <ul>
     * <li>"B": border/boundary edge (only one incident face).
     * <li>"M": mountain fold.
     * <li>"V": valley fold.
     * <li>"F": flat (unfolded) fold.
     * <li>"U": unassigned/unknown.
     * </ul>
     * <p>
     * For example, this property can be used to specify a full mountain-valley assignment (consisting of "M", "V", and "B"), or just to label which edges are boundary edges (consisting of "U" or "B").
     * <p>
     * For orientable manifolds, a valley fold points the two face normals into each other, while a mountain fold makes them point away from each other. For nonorientable manifolds, a valley fold is defined as bringing the normal of the face to the left of the edge (listed first in edges_faces) to point into the adjacent face (when fully folded), while a mountain fold has the same normal point away from the adjacent face.
     *
     * @return The fold direction assignment of this edge.
     */
    public FoldEdgeAssignment getAssignment() {
        return assignment;
    }

    /**
     * Set the assignment of this edge.
     *
     * @param assignment An instance of FoldEdgeAssignment for this edge.
     */
    public void setAssignment(FoldEdgeAssignment assignment) {
        this.assignment = assignment;
    }

    /**
     * Get the angle of this fold.
     * <p>
     * For each edge, the fold angle (deviation from flatness) along each edge of the pattern. The fold angle is a number in degrees lying in the range [−180, 180]. The fold angle is positive for valley folds, negative for mountain folds, and zero for flat, unassigned, and border folds. Accordingly, the sign of edge_foldAngle should match edges_assignment if both are specified.
     *
     * @return The angle of this fold.
     */
    public Double getFoldAngle() {
        return foldAngle;
    }

    /**
     * Set the angle of this fold.
     *
     * @param foldAngle The angle of this fold.
     */
    public void setFoldAngle(Double foldAngle) {
        this.foldAngle = foldAngle;
    }

    /**
     * Get the length of this edge.
     * <p>
     * For each edge, the length of the edge (a number). This is mainly useful for defining the intrinsic geometry of abstract complexes where the coordinates of {@link Vertex} are unspecified; otherwise, this value can be computed from the coordinates in {@link Vertex}.
     *
     * @return The length of this edge.
     */
    public Double getLength() {
        return length;
    }

    /**
     * Set the length of this edge.
     *
     * @param length The length of this edge.
     */
    public void setLength(Double length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return Objects.equals(getStart(), edge.getStart()) && Objects.equals(getEnd(), edge.getEnd()) && getAssignment() == edge.getAssignment() && Objects.equals(getFoldAngle(), edge.getFoldAngle()) && Objects.equals(getLength(), edge.getLength());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStart(), getEnd(), getAssignment(), getFoldAngle(), getLength());
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                ", end=" + end +
                ", assignment=" + assignment +
                ", foldAngle=" + foldAngle +
                ", length=" + length +
                '}';
    }
}

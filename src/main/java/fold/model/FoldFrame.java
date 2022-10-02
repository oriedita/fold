package fold.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A single frame in a fold file.
 */
public class FoldFrame {
    private List<Vertex> vertices = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();
    private List<Face> faces = new ArrayList<>();

    private List<FaceOrder> faceOrders = new ArrayList<>();
    private List<EdgeOrder> edgeOrders = new ArrayList<>();


    private String frameAuthor;

    private String frameTitle;

    private String frameDescription;

    private List<String> frameClasses = new ArrayList<>();

    private List<String> attributes = new ArrayList<>();

    private String unit;

    private Integer parent;

    private Boolean inherit;

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public List<Face> getFaces() {
        return faces;
    }

    public void setFaces(List<Face> faces) {
        this.faces = faces;
    }

    public List<FaceOrder> getFaceOrders() {
        return faceOrders;
    }

    public void setFaceOrders(List<FaceOrder> faceOrders) {
        this.faceOrders = faceOrders;
    }

    public List<EdgeOrder> getEdgeOrders() {
        return edgeOrders;
    }

    public void setEdgeOrders(List<EdgeOrder> edgeOrders) {
        this.edgeOrders = edgeOrders;
    }

    /**
     * The human author.
     */
    public String getFrameAuthor() {
        return frameAuthor;
    }

    public void setFrameAuthor(String frameAuthor) {
        this.frameAuthor = frameAuthor;
    }

    /**
     * A title for the frame.
     */
    public String getFrameTitle() {
        return frameTitle;
    }

    public void setFrameTitle(String frameTitle) {
        this.frameTitle = frameTitle;
    }

    /**
     * A description of the frame.
     */
    public String getFrameDescription() {
        return frameDescription;
    }

    public void setFrameDescription(String frameDescription) {
        this.frameDescription = frameDescription;
    }

    /**
     * A subjective interpretation about what the frame represents. Some standard frame classes:
     * <p>
     * "creasePattern": a crease pattern (unfolded)
     * "foldedForm": a folded form/state, e.g. flat folding or 3D folding
     * "graph": vertices and edges, but no lengths or faces
     * "linkage": vertices and edges and edge lengths, but no faces
     * Custom classes should have a colon in them; see Custom Properties below.
     */
    public List<String> getFrameClasses() {
        return frameClasses;
    }

    public void setFrameClasses(List<String> frameClasses) {
        this.frameClasses = frameClasses;
    }

    /**
     * Attributes that objectively describe properties of the folded structure being represented (array of strings).
     * Some standard frame attributes include
     * <p>
     * "2D": the coordinates lie in 2D (xy); z coordinates are all implicitly or explicitly 0
     * "3D": the coordinates lie in 3D (xyz) and not 2D (xy)
     * "abstract": the polyhedral complex is not embedded in Euclidean space, so there are no vertex coordinates
     * (but there might be edge lengths defining intrinsic geometry)
     * "manifold": the polyhedral complex is a manifold (has at most two faces incident to each edge)
     * "nonManifold": the polyhedral complex is not a manifold (has more than two faces incident to an edge)
     * "orientable": the polyhedral complex is orientable, meaning it can be assigned a consistent normal direction (and hence it is also manifold)
     * "nonOrientable": the polyhedral complex is not orientable, meaning it cannot be assigned a consistent normal direction
     * "selfTouching": the polyhedral complex has faces that touch in their relative interiors, so you probably want a face ordering
     * "nonSelfTouching": the polyhedral complex has no touching faces, so face ordering isn't needed
     * "selfIntersecting": the polyhedral complex has properly intersecting faces
     * "nonSelfIntersecting": the polyhedral complex has no properly intersecting faces
     * Custom attributes should have a colon in them; see Custom Properties below.
     */
    public List<String> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    /**
     * Physical or logical unit that all coordinates are relative to (a string). Standard defined values
     * are as follows. You can also use a custom string, but it will probably not be understood by software.
     * <p>
     * "unit" (equivalent to not specifying a unit): no physical meaning
     * "in": inches (25.4 mm)
     * "pt": desktop publishing/PostScript points (1/72 in)
     * "m": meters (1/299,792,458 light seconds)
     * "cm": centimeters (1/100 meters)
     * "mm": millimeters (1/1000 meters)
     * "um": microns (1/1,000,000 meters)
     * "nm": nanometers (1/1,000,000,000 meters)
     */
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Parent frame ID. Intuitively, this frame (the child) is a modification (or, in general, is related to) the
     * parent frame. This property is optional, but enables organizing frames into a tree structure.
     */
    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    /**
     * Boolean. If true, any properties in the parent frame (or recursively inherited from an ancestor) that is
     * not overridden in this frame are automatically inherited, allowing you to avoid duplicated data in many
     * cases. For example, the frame can change the vertex coordinates (vertices_coords) while inheriting the
     * structure of the parent's mesh.
     */
    public Boolean getInherit() {
        return inherit;
    }

    public void setInherit(Boolean inherit) {
        this.inherit = inherit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoldFrame frame = (FoldFrame) o;
        return Objects.equals(getVertices(), frame.getVertices()) && Objects.equals(getEdges(), frame.getEdges()) && Objects.equals(getFaces(), frame.getFaces()) && Objects.equals(getFaceOrders(), frame.getFaceOrders()) && Objects.equals(getEdgeOrders(), frame.getEdgeOrders()) && Objects.equals(getFrameAuthor(), frame.getFrameAuthor()) && Objects.equals(getFrameTitle(), frame.getFrameTitle()) && Objects.equals(getFrameDescription(), frame.getFrameDescription()) && Objects.equals(getFrameClasses(), frame.getFrameClasses()) && Objects.equals(getAttributes(), frame.getAttributes()) && Objects.equals(getUnit(), frame.getUnit()) && Objects.equals(getParent(), frame.getParent()) && Objects.equals(getInherit(), frame.getInherit());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVertices(), getEdges(), getFaces(), getFaceOrders(), getEdgeOrders(), getFrameAuthor(), getFrameTitle(), getFrameDescription(), getFrameClasses(), getAttributes(), getUnit(), getParent(), getInherit());
    }

    public static class FaceOrder {
        private Face face1;
        private Face face2;
        private Boolean face1AboveFace2;

        public Face getFace1() {
            return face1;
        }

        public void setFace1(Face face1) {
            this.face1 = face1;
        }

        public Face getFace2() {
            return face2;
        }

        public void setFace2(Face face2) {
            this.face2 = face2;
        }

        public Boolean getFace1AboveFace2() {
            return face1AboveFace2;
        }

        public void setFace1AboveFace2(Boolean face1AboveFace2) {
            this.face1AboveFace2 = face1AboveFace2;
        }
    }

    public static class EdgeOrder {
        private Edge edge1;
        private Edge edge2;
        private Boolean edge1AboveEdge2;

        public Edge getEdge1() {
            return edge1;
        }

        public void setEdge1(Edge edge1) {
            this.edge1 = edge1;
        }

        public Edge getEdge2() {
            return edge2;
        }

        public void setEdge2(Edge edge2) {
            this.edge2 = edge2;
        }

        public Boolean getEdge1AboveEdge2() {
            return edge1AboveEdge2;
        }

        public void setEdge1AboveEdge2(Boolean edge1AboveEdge2) {
            this.edge1AboveEdge2 = edge1AboveEdge2;
        }
    }
}

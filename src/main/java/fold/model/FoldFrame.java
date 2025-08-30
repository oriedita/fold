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
    private String author;
    private String title;
    private String description;
    private List<String> classes = new ArrayList<>();
    private List<String> attributes = new ArrayList<>();
    private String unit;
    private Integer parent;
    private Boolean inherit;

    /**
     * Get the list of vertices in this frame.
     *
     * @return The list of vertices in this frame.
     */
    public List<Vertex> getVertices() {
        return vertices;
    }

    /**
     * Set the list of vertices for this frame.
     *
     * @param vertices The list of vertices for this frame.
     */
    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    /**
     * Get the list of edges in this frame.
     *
     * @return The list of edges in this frame.
     */
    public List<Edge> getEdges() {
        return edges;
    }

    /**
     * Set the list of edges for this frame.
     *
     * @param edges The list of edges for this frame.
     */
    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public void addEdge(Edge edge) {
        this.edges.add(edge);
        this.vertices.add(edge.getStart());
        this.vertices.add(edge.getEnd());
    }

    /**
     * Get the list of faces in this frame.
     *
     * @return The list of faces in this frame.
     */
    public List<Face> getFaces() {
        return faces;
    }

    /**
     * Set the list of faces for this frame.
     *
     * @param faces The list of faces for this frame.
     */
    public void setFaces(List<Face> faces) {
        this.faces = faces;
    }

    /**
     * Get the face orders of this frame.
     *
     * @return The face orders of this frame.
     */
    public List<FaceOrder> getFaceOrders() {
        return faceOrders;
    }

    /**
     * Set the face orders for this frame.
     *
     * @param faceOrders The face orders for this frame.
     */
    public void setFaceOrders(List<FaceOrder> faceOrders) {
        this.faceOrders = faceOrders;
    }

    /**
     * Get the edge orders of this frame.
     *
     * @return The edge orders of this frame.
     */
    public List<EdgeOrder> getEdgeOrders() {
        return edgeOrders;
    }

    /**
     * Set the edge orders for this frame.
     *
     * @param edgeOrders The edge orders for this frame.
     */
    public void setEdgeOrders(List<EdgeOrder> edgeOrders) {
        this.edgeOrders = edgeOrders;
    }

    /**
     * The human author.
     *
     * @return The human author of this frame.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set the human author for this frame.
     *
     * @param author The human author for this frame.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * A title for the frame.
     *
     * @return The description of this frame.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the title for this frame.
     *
     * @param title The title for this frame.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * A description of the frame.
     *
     * @return The description of this frame.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description for this frame.
     *
     * @param description The description for this frame.
     * @see #getDescription()
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * A subjective interpretation about what the frame represents. Some standard frame classes:
     * <dl>
     * <dt>creasePattern <dd> a crease pattern (unfolded)
     * <dt>foldedForm <dd> a folded form/state, e.g. flat folding or 3D folding
     * <dt>graph <dd> vertices and edges, but no lengths or faces
     * <dt>linkage <dd> vertices and edges and edge lengths, but no faces
     * </dl>
     * <p>
     * Custom classes should have a colon in them; see Custom Properties below.
     *
     * @return The classes of this frame.
     */
    public List<String> getClasses() {
        return classes;
    }

    /**
     * Set the classes for this frame.
     *
     * @param classes The classes for this frame.
     * @see #getClasses()
     */
    public void setClasses(List<String> classes) {
        this.classes = classes;
    }

    /**
     * Attributes that objectively describe properties of the folded structure being represented (array of strings).
     * Some standard frame attributes include
     * <dl>
     * <dt>2D <dd> the coordinates lie in 2D (xy); z coordinates are all implicitly or explicitly 0
     * <dt>3D <dd> the coordinates lie in 3D (xyz) and not 2D (xy)
     * <dt>abstract <dd> the polyhedral complex is not embedded in Euclidean space, so there are no vertex coordinates
     * (but there might be edge lengths defining intrinsic geometry)
     * <dt>manifold <dd> the polyhedral complex is a manifold (has at most two faces incident to each edge)
     * <dt>nonManifold <dd> the polyhedral complex is not a manifold (has more than two faces incident to an edge)
     * <dt>orientable <dd> the polyhedral complex is orientable, meaning it can be assigned a consistent normal direction (and hence it is also manifold)
     * <dt>nonOrientable <dd> the polyhedral complex is not orientable, meaning it cannot be assigned a consistent normal direction
     * <dt>selfTouching <dd> the polyhedral complex has faces that touch in their relative interiors, so you probably want a face ordering
     * <dt>nonSelfTouching <dd> the polyhedral complex has no touching faces, so face ordering isn't needed
     * <dt>selfIntersecting <dd> the polyhedral complex has properly intersecting faces
     * <dt>nonSelfIntersecting <dd> the polyhedral complex has no properly intersecting faces
     * <dt>joins / noJoins<dd> whether any edges have an assignment of "J" (join)
     * <dt>cuts / noCuts <dd> whether any edges have an assignment of "C" (cut/slit representing multiple "B" edges)
     * </dl>
     * <p>
     * Custom attributes should have a colon in them; see Custom Properties below.
     *
     * @return The attributes of this frame.
     */
    public List<String> getAttributes() {
        return attributes;
    }

    /**
     * Set the attributes for this frame.
     *
     * @param attributes The attributes for this frame.
     * @see #getAttributes()
     */
    public void setAttributes(List<String> attributes) {
        this.attributes = attributes;
    }

    /**
     * Physical or logical unit that all coordinates are relative to (a string). Standard defined values
     * are as follows. You can also use a custom string, but it will probably not be understood by software.
     * <dl>
     * <dt>unit <dd>(equivalent to not specifying a unit): no physical meaning
     * <dt>in <dd> inches (25.4 mm)
     * <dt>pt <dd> desktop publishing/PostScript points (1/72 in)
     * <dt>m <dd> meters (1/299,792,458 light seconds)
     * <dt>cm <dd> centimeters (1/100 meters)
     * <dt>mm <dd> millimeters (1/1000 meters)
     * <dt>um <dd> microns (1/1,000,000 meters)
     * <dt>nm <dd> nanometers (1/1,000,000,000 meters)
     * </dl>
     *
     * @return The unit of this frame.
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Set the unit for this frame.
     *
     * @param unit The unit for this frame.
     * @see #getUnit()
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * Parent frame ID. Intuitively, this frame (the child) is a modification (or, in general, is related to) the
     * parent frame. This property is optional, but enables organizing frames into a tree structure.
     *
     * @return A pointer to the parent frame.
     */
    public Integer getParent() {
        return parent;
    }

    /**
     * Set the parent id for this frame.
     *
     * @param parent The parent id for this frame.
     * @see #getParent()
     */
    public void setParent(Integer parent) {
        this.parent = parent;
    }

    /**
     * Boolean. If true, any properties in the parent frame (or recursively inherited from an ancestor) that is
     * not overridden in this frame are automatically inherited, allowing you to avoid duplicated data in many
     * cases. For example, the frame can change the vertex coordinates (vertices_coords) while inheriting the
     * structure of the parent's mesh.
     *
     * @return If this frame inherits the parent frame.
     */
    public Boolean getInherit() {
        return inherit;
    }

    /**
     * Set if this frame inherits any properties from the parent frame.
     *
     * @param inherit If this frame inherits any properties from the parent frame.
     * @see #getInherit()
     */
    public void setInherit(Boolean inherit) {
        this.inherit = inherit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoldFrame frame = (FoldFrame) o;
        return Objects.equals(getVertices(), frame.getVertices()) && Objects.equals(getEdges(), frame.getEdges()) && Objects.equals(getFaces(), frame.getFaces()) && Objects.equals(getFaceOrders(), frame.getFaceOrders()) && Objects.equals(getEdgeOrders(), frame.getEdgeOrders()) && Objects.equals(getAuthor(), frame.getAuthor()) && Objects.equals(getTitle(), frame.getTitle()) && Objects.equals(getDescription(), frame.getDescription()) && Objects.equals(getClasses(), frame.getClasses()) && Objects.equals(getAttributes(), frame.getAttributes()) && Objects.equals(getUnit(), frame.getUnit()) && Objects.equals(getParent(), frame.getParent()) && Objects.equals(getInherit(), frame.getInherit());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(getVertices(), getEdges(), getFaces(), getFaceOrders(), getEdgeOrders(), getAuthor(), getTitle(), getDescription(), getClasses(), getAttributes(), getUnit(), getParent(), getInherit());
    }

    /**
     * Given two faces this class defines the order of the two faces.
     */
    public static class FaceOrder {
        private Face face1;
        private Face face2;
        private Boolean face1AboveFace2;

        /**
         * Get the first face of this definition.
         *
         * @return The first face of this definition.
         */
        public Face getFace1() {
            return face1;
        }

        /**
         * Set the first face of this definition.
         *
         * @param face1 The first face of this definition.
         */
        public void setFace1(Face face1) {
            this.face1 = face1;
        }

        /**
         * Get the second face of this definition.
         *
         * @return The second face of this definition.
         */
        public Face getFace2() {
            return face2;
        }

        /**
         * Set the second face of this definition.
         *
         * @param face2 The second face of this definition.
         */
        public void setFace2(Face face2) {
            this.face2 = face2;
        }

        /**
         * Get if face1 lies above face2.
         *
         * @return If face1 lies above face2, this value is null if there is no stacking defined.
         */
        public Boolean getFace1AboveFace2() {
            return face1AboveFace2;
        }

        /**
         * Set if face1 lies above face2.
         *
         * @param face1AboveFace2 If face1 lies above2 or null if there is no stacking defined.
         */
        public void setFace1AboveFace2(Boolean face1AboveFace2) {
            this.face1AboveFace2 = face1AboveFace2;
        }
    }

    /**
     * Given two edges this class defines the stacking order of these edges.
     */
    public static class EdgeOrder {
        private Edge edge1;
        private Edge edge2;
        private Boolean edge1AboveEdge2;

        /**
         * Get the first edge of this definition.
         *
         * @return The first edge of this definition.
         */
        public Edge getEdge1() {
            return edge1;
        }

        /**
         * Set the first edge of this definition.
         *
         * @param edge1 The first edge of this definition.
         */
        public void setEdge1(Edge edge1) {
            this.edge1 = edge1;
        }

        /**
         * Get the second edge of this definition.
         *
         * @return The second edge of this definition.
         */
        public Edge getEdge2() {
            return edge2;
        }

        /**
         * Set the second edge of this definition.
         *
         * @param edge2 The second edge of this definition.
         */
        public void setEdge2(Edge edge2) {
            this.edge2 = edge2;
        }

        /**
         * Get if edge1 lies above edge2.
         *
         * @return If edge1 lies above edge2, this value is null if there is no stacking defined.
         */
        public Boolean getEdge1AboveEdge2() {
            return edge1AboveEdge2;
        }

        /**
         * Set if edge1 lies above edge2.
         *
         * @param edge1AboveEdge2 If edge1 lies above2 or null if there is no stacking defined.
         */
        public void setEdge1AboveEdge2(Boolean edge1AboveEdge2) {
            this.edge1AboveEdge2 = edge1AboveEdge2;
        }
    }
}

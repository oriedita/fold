package fold.io.impl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.jr.ob.api.ValueWriter;
import com.fasterxml.jackson.jr.ob.impl.JSONWriter;
import fold.model.Edge;
import fold.model.Face;
import fold.model.FoldFrame;
import fold.model.Vertex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class FoldFrameValueWriter implements ValueWriter {

    private static int convertOrderBack(Boolean face1AboveFace2) {
        if (face1AboveFace2 == null) {
            return 0;
        }

        if (face1AboveFace2) {
            return 1;
        }

        return -1;
    }

    @Override
    public void writeValue(JSONWriter context, JsonGenerator g, Object value) throws IOException {
        g.writeStartObject();
        partialWriteValue(context, g, (FoldFrame) value);
        g.writeEndObject();
    }

    public void partialWriteValue(JSONWriter context, JsonGenerator g, FoldFrame value) throws IOException {
        if (value.getAuthor() != null)
            g.writeStringField("frame_author", value.getAuthor());
        if (value.getTitle() != null)
            g.writeStringField("frame_title", value.getTitle());
        if (value.getDescription() != null)
            g.writeStringField("frame_description", value.getDescription());
        if (value.getClasses().size() > 0) {
            g.writeFieldName("frame_classes");
            context.writeValue(value.getClasses());
        }
        if (value.getAttributes().size() > 0) {
            g.writeFieldName("frame_attributes");
            context.writeValue(value.getAttributes());
        }

        if (value.getUnit() != null)
            g.writeStringField("frame_unit", value.getUnit());

        // Vertex information: vertices_...

        List<List<Double>> vertices_coords = new ArrayList<>();
        List<List<Integer>> vertices_vertices = new ArrayList<>();
        List<List<Integer>> vertices_faces = new ArrayList<>();

        for (Vertex vertex : value.getVertices()) {
            if (vertex.getX() != null && vertex.getY() != null) {
                List<Double> coords = new ArrayList<>();
                coords.add(vertex.getX());
                coords.add(vertex.getY());

                if (vertex.getZ() != null) {
                    coords.add(vertex.getZ());
                }
                vertices_coords.add(coords);
            }
            if (vertex.getFaces().size() > 0) {
                vertices_faces.add(vertex.getFaces().stream().map(f -> value.getFaces().indexOf(f)).collect(Collectors.toList()));
            }
            if (vertex.getVertices().size() > 0) {
                vertices_vertices.add(vertex.getVertices().stream().map(v -> value.getVertices().indexOf(v)).collect(Collectors.toList()));
            }
        }

        if (vertices_coords.size() > 0) {
            g.writeFieldName("vertices_coords");
            context.writeValue(vertices_coords);
        }
        if (vertices_vertices.size() > 0) {
            g.writeFieldName("vertices_vertices");
            context.writeValue(vertices_vertices);
        }
        if (vertices_faces.size() > 0) {
            g.writeFieldName("vertices_faces");
            context.writeValue(vertices_faces);
        }

        // Edge information: edges_...
        List<List<Integer>> edges_vertices = new ArrayList<>();
        List<List<Integer>> edges_faces = new ArrayList<>();
        List<String> edges_assignment = new ArrayList<>();
        List<Double> edges_foldAngle = new ArrayList<>();
        List<Double> edges_length = new ArrayList<>();

        for (Edge edge : value.getEdges()) {
            if (edge.getAssignment() != null) {
                edges_assignment.add(edge.getAssignment().getLetter());
            }
            if (edge.getFoldAngle() != null) {
                edges_foldAngle.add(edge.getFoldAngle());
            }
            if (edge.getLength() != null) {
                edges_length.add(edge.getLength());
            }
            if (edge.getStart() != null && edge.getEnd() != null) {
                int edgeStartVertexId = value.getVertices().indexOf(edge.getStart());
                int edgeEndVertexId = value.getVertices().indexOf(edge.getEnd());
                edges_vertices.add(Arrays.asList(edgeStartVertexId, edgeEndVertexId));
            }
        }

        if (edges_vertices.size() > 0) {
            g.writeFieldName("edges_vertices");
            context.writeValue(edges_vertices);
        }
        if (edges_faces.size() > 0) {
            g.writeFieldName("edges_faces");
            context.writeValue(edges_faces);
        }
        if (edges_assignment.size() > 0) {
            g.writeFieldName("edges_assignment");
            context.writeValue(edges_assignment);
        }
        if (edges_foldAngle.size() > 0) {
            g.writeFieldName("edges_foldAngle");
            context.writeValue(edges_foldAngle);
        }
        if (edges_length.size() > 0) {
            g.writeFieldName("edges_length");
            context.writeValue(edges_length);
        }

        // Face information: faces_...
        List<List<Integer>> faces_vertices = new ArrayList<>();
        List<List<Integer>> faces_edges = new ArrayList<>();

        for (Face face : value.getFaces()) {
            if (face.getEdges().size() > 0) {
                faces_edges.add(face.getEdges().stream().map(e -> value.getEdges().indexOf(e)).collect(Collectors.toList()));
            }
            if (face.getVertices().size() > 0) {
                faces_vertices.add(face.getVertices().stream().map(v -> value.getVertices().indexOf(v)).collect(Collectors.toList()));
            }
        }

        if (faces_vertices.size() > 0) {
            g.writeFieldName("faces_vertices");
            context.writeValue(faces_vertices);
        }
        if (faces_edges.size() > 0) {
            g.writeFieldName("faces_edges");
            context.writeValue(faces_edges);
        }

        // Layer information: faceOrders and edgeOrders

        List<List<Integer>> edgeOrders = new ArrayList<>();
        List<List<Integer>> faceOrders = new ArrayList<>();

        for (int i = 0; i < value.getFaceOrders().size(); i++) {
            FoldFrame.FaceOrder faceOrder = value.getFaceOrders().get(i);

            int faceOrderFace1Id = value.getFaces().indexOf(faceOrder.getFace1());
            int faceOrderFace2Id = value.getFaces().indexOf(faceOrder.getFace2());
            faceOrders.add(Arrays.asList(faceOrderFace1Id, faceOrderFace2Id, convertOrderBack(faceOrder.getFace1AboveFace2())));
        }

        for (int i = 0; i < value.getEdgeOrders().size(); i++) {
            FoldFrame.EdgeOrder edgeOrder = value.getEdgeOrders().get(i);

            int edge1Id = value.getEdges().indexOf(edgeOrder.getEdge1());
            int edge2Id = value.getEdges().indexOf(edgeOrder.getEdge2());

            edgeOrders.add(Arrays.asList(edge1Id, edge2Id, convertOrderBack(edgeOrder.getEdge1AboveEdge2())));
        }

        if (edgeOrders.size() > 0) {
            g.writeFieldName("edgeOrders");
            context.writeValue(edgeOrders);
        }
        if (faceOrders.size() > 0) {
            g.writeFieldName("faceOrders");
            context.writeValue(faceOrders);
        }
    }

    @Override
    public Class<?> valueType() {
        return FoldFrame.class;
    }
}

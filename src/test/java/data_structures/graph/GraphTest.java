package data_structures.graph;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class GraphTest {
    @Test
    public void testAddVertexWhenEmpty() {
        var graph = new SimpleGraph(10);
        graph.AddVertex(10);

        Assertions.assertEquals(10, graph.vertex[0].Value);
    }

    @Test
    public void testAddVertexWhenNotEmpty() {
        var graph = new SimpleGraph(3);
        graph.AddVertex(3);
        graph.AddVertex(2);

        Assertions.assertEquals(3, graph.vertex[0].Value);
        Assertions.assertEquals(2, graph.vertex[1].Value);
    }

    @Test
    public void testRemoveVertexWhenEmpty() {
        var graph = new SimpleGraph(3);
        graph.RemoveVertex(2);
    }

    @Test
    public void testRemoveVertexWhenNotEmptyWithoutEdges() {
        var graph = new SimpleGraph(3);
        graph.AddVertex(1);
        graph.RemoveVertex(0);

        Assertions.assertNull(graph.vertex[0]);
    }

    @Test
    public void testRemoveVertexWhenNotEmptyWithEdges() {
        var graph = new SimpleGraph(3);

        // add vertexes
        graph.vertex[0] = new Vertex(1);
        graph.vertex[1] = new Vertex(2);
        graph.vertex[2] = new Vertex(3);

        // add edges
        graph.m_adjacency[0][2] = 1;
        graph.m_adjacency[1][2] = 1;

        graph.RemoveVertex(2);

        Assertions.assertNull(graph.vertex[2]);
        Assertions.assertEquals(0, graph.m_adjacency[0][2]);
        Assertions.assertEquals(0 ,graph.m_adjacency[1][2]);
    }
}

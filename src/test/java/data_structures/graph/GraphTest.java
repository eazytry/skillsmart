package data_structures.graph;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

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
        Assertions.assertEquals(0, graph.m_adjacency[1][2]);
    }

    @Test
    public void testDepthFirstSearchWhenEmpty() {
        var graph = new SimpleGraph(0);

        var vertices = graph.DepthFirstSearch(0, 0);

        Assertions.assertTrue(vertices.isEmpty());
    }

    @Test
    public void testDepthFirstSearch() {
        var graph = new SimpleGraph(5);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);

        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(0, 3);
        graph.AddEdge(2, 3);
        graph.AddEdge(1, 3);
        graph.AddEdge(1, 4);
        graph.AddEdge(3, 4);
        graph.AddEdge(3, 3);

        var vertices = graph.DepthFirstSearch(0, 4);

        Assertions.assertFalse(vertices.isEmpty());
    }

    @Test
    public void testBreadthFirstSearch() {
        var graph = new SimpleGraph(6);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddVertex(6);

        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(0, 3);
        graph.AddEdge(2, 3);
        graph.AddEdge(1, 3);
        graph.AddEdge(1, 4);
        graph.AddEdge(3, 4);
        graph.AddEdge(3, 3);
        graph.AddEdge(4, 5);

        var vertices = graph.BreadthFirstSearch(0, 5);

        Assertions.assertFalse(vertices.isEmpty());
    }

    @Test
    public void testBreadthFirstSearchReverse() {
        var graph = new SimpleGraph(5);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);

        graph.m_adjacency[0][1] = 1;
        graph.m_adjacency[1][0] = 1;
        graph.m_adjacency[0][2] = 1;
        graph.m_adjacency[2][0] = 1;
        graph.m_adjacency[3][0] = 1;
        graph.m_adjacency[0][3] = 1;
        graph.m_adjacency[2][3] = 1;
        graph.m_adjacency[3][2] = 1;
        graph.m_adjacency[1][3] = 1;
        graph.m_adjacency[3][1] = 1;
        graph.m_adjacency[1][4] = 1;
        graph.m_adjacency[4][1] = 1;
        graph.m_adjacency[3][4] = 1;
        graph.m_adjacency[4][3] = 1;
        graph.m_adjacency[3][3] = 1;

        var vertices = graph.BreadthFirstSearch(3, 0);

        Assertions.assertFalse(vertices.isEmpty());
        Assertions.assertEquals(4, vertices.get(0).Value);
        Assertions.assertEquals(1, vertices.get(1).Value);
    }

    @Test
    public void testBreadthFirstSearchShouldReturnTwoVertexes() {
        var graph = new SimpleGraph(6);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddVertex(6);

        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(0, 3);
        graph.AddEdge(2, 3);
        graph.AddEdge(1, 3);
        graph.AddEdge(1, 4);
        graph.AddEdge(3, 4);
        graph.AddEdge(3, 3);
        graph.AddEdge(4, 5);

        var vertices = graph.BreadthFirstSearch(4, 5);

        Assertions.assertFalse(vertices.isEmpty());
    }

    @Test
    public void testBreadthFirstSearchShouldReturnTwoVertexesWhenOneEdgeExists() {
        var graph = new SimpleGraph(2);

        graph.AddVertex(1);
        graph.AddVertex(2);

        graph.AddEdge(0, 1);

        var vertices = graph.BreadthFirstSearch(0, 1);

        Assertions.assertFalse(vertices.isEmpty());
        Assertions.assertEquals(1, vertices.get(0).Value);
        Assertions.assertEquals(2, vertices.get(1).Value);
    }


    @Test
    public void testBreadthFirstSearchShouldReturnEmptyWhenOneVertexExists() {
        var graph = new SimpleGraph(1);

        graph.AddVertex(1);

        var vertices = graph.BreadthFirstSearch(0, 1);

        Assertions.assertTrue(vertices.isEmpty());
    }

    @Test
    public void testBreadthFirstSearchShouldReturnTwoVertexesWhenTwoEdgeExists() {
        var graph = new SimpleGraph(3);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 2);

        var vertices = graph.BreadthFirstSearch(1, 2);

        Assertions.assertFalse(vertices.isEmpty());
        Assertions.assertEquals(2, vertices.get(0).Value);
        Assertions.assertEquals(3, vertices.get(1).Value);
    }

    @Test
    public void testBreadthFirstSearchWhenEmpty() {
        var graph = new SimpleGraph(6);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddVertex(6);

        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(0, 3);
        graph.AddEdge(2, 3);
        graph.AddEdge(1, 3);
        graph.AddEdge(1, 4);
        graph.AddEdge(3, 4);
        graph.AddEdge(3, 3);

        var vertices = graph.BreadthFirstSearch(0, 5);

        Assertions.assertTrue(vertices.isEmpty());
    }

    @Test
    public void testDepthFirstSearchReverse() {
        var graph = new SimpleGraph(4);

        // add vertexes
        graph.vertex[0] = new Vertex(1);
        graph.vertex[1] = new Vertex(2);
        graph.vertex[2] = new Vertex(3);
        graph.vertex[3] = new Vertex(4);

        // add edges
        graph.m_adjacency[0][2] = 1;
        graph.m_adjacency[2][1] = 1;
        graph.m_adjacency[1][3] = 1;

        var vertices = graph.DepthFirstSearch(3, 0);

        Assertions.assertFalse(vertices.isEmpty());
        Assertions.assertEquals(4, vertices.get(0).Value);
        Assertions.assertEquals(2, vertices.get(1).Value);
        Assertions.assertEquals(3, vertices.get(2).Value);
        Assertions.assertEquals(1, vertices.get(3).Value);
    }

    @Test
    public void testDepthFirstSearchNotFound() {
        var graph = new SimpleGraph(4);

        // add vertexes
        graph.vertex[0] = new Vertex(1);
        graph.vertex[1] = new Vertex(2);
        graph.vertex[2] = new Vertex(3);
        graph.vertex[3] = new Vertex(4);

        // add edges
        graph.m_adjacency[0][2] = 1;
        graph.m_adjacency[1][3] = 1;

        var vertices = graph.DepthFirstSearch(0, 3);

        Assertions.assertTrue(vertices.isEmpty());
    }
}

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
    public void testBreadthFirstSearchWhenAllEdges() {
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

    @Test
    public void testBreadthFirstSearchWhenFirstOneEdge() {
        var graph = new SimpleGraph(5);

        graph.AddVertex(10);
        graph.AddVertex(40);
        graph.AddVertex(30);
        graph.AddVertex(20);
        graph.AddVertex(5);

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 0);
        graph.AddEdge(0, 2);
        graph.AddEdge(2, 0);
        graph.AddEdge(0, 3);
        graph.AddEdge(3, 0);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 2);
        graph.AddEdge(1, 3);
        graph.AddEdge(3, 1);
        graph.AddEdge(4, 3);
        graph.AddEdge(3, 4);
        graph.AddEdge(3, 3);

        var vertices = graph.BreadthFirstSearch(0, 3);

        Assertions.assertFalse(vertices.isEmpty());
        Assertions.assertEquals(2, vertices.size());
        Assertions.assertEquals(1, graph.m_adjacency[0][3]);
        Assertions.assertEquals(1, graph.m_adjacency[3][0]);
        Assertions.assertEquals(10, vertices.get(0).Value);
        Assertions.assertEquals(20, vertices.get(1).Value);
    }

    @Test
    public void testBreadthFirstSearchWhenFirstOneEdgeReverse() {
        var graph = new SimpleGraph(6);

        graph.AddVertex(10);
        graph.AddVertex(40);
        graph.AddVertex(30);
        graph.AddVertex(20);
        graph.AddVertex(5);
        graph.AddVertex(4);

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 0);
        graph.AddEdge(0, 2);
        graph.AddEdge(2, 0);
        graph.AddEdge(0, 3);
        graph.AddEdge(3, 0);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 2);
        graph.AddEdge(1, 3);
        graph.AddEdge(3, 1);
        graph.AddEdge(4, 3);
        graph.AddEdge(3, 4);
        graph.AddEdge(3, 3);
        graph.AddEdge(4, 5);
        graph.AddEdge(5, 4);

        var vertices = graph.BreadthFirstSearch(3, 0);

        Assertions.assertFalse(vertices.isEmpty());
        Assertions.assertEquals(2, vertices.size());
        Assertions.assertTrue(graph.IsEdge(3, 0));
        Assertions.assertEquals(20, vertices.get(0).Value);
        Assertions.assertEquals(10, vertices.get(1).Value);
    }

    @Test
    public void testBreadthFirstSearchWhenMiddleOneEdge() {
        var graph = new SimpleGraph(6);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddVertex(6);

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 0);
        graph.AddEdge(0, 2);
        graph.AddEdge(2, 0);
        graph.AddEdge(0, 3);
        graph.AddEdge(3, 0);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 2);
        graph.AddEdge(1, 3);
        graph.AddEdge(3, 1);
        graph.AddEdge(4, 3);
        graph.AddEdge(3, 4);
        graph.AddEdge(3, 3);
        graph.AddEdge(4, 5);
        graph.AddEdge(5, 4);

        var vertices = graph.BreadthFirstSearch(2, 3);

        Assertions.assertFalse(vertices.isEmpty());
        Assertions.assertEquals(2, vertices.size());
        Assertions.assertEquals(3, vertices.get(0).Value);
        Assertions.assertEquals(4, vertices.get(1).Value);
    }

    @Test
    public void testBreadthFirstSearchWhenMiddleOneEdgeReverse() {
        var graph = new SimpleGraph(6);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddVertex(6);

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 0);
        graph.AddEdge(0, 2);
        graph.AddEdge(2, 0);
        graph.AddEdge(0, 3);
        graph.AddEdge(3, 0);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 2);
        graph.AddEdge(1, 3);
        graph.AddEdge(3, 1);
        graph.AddEdge(4, 3);
        graph.AddEdge(3, 4);
        graph.AddEdge(3, 3);
        graph.AddEdge(4, 5);
        graph.AddEdge(5, 4);

        var vertices = graph.BreadthFirstSearch(3, 2);

        Assertions.assertFalse(vertices.isEmpty());
        Assertions.assertEquals(2, vertices.size());
        Assertions.assertEquals(4, vertices.get(0).Value);
        Assertions.assertEquals(3, vertices.get(1).Value);
    }


    @Test
    public void testBreadthFirstSearchWhenAllEdgesReverse() {
        var graph = new SimpleGraph(6);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddVertex(6);

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 0);
        graph.AddEdge(0, 2);
        graph.AddEdge(2, 0);
        graph.AddEdge(0, 3);
        graph.AddEdge(3, 0);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 2);
        graph.AddEdge(1, 3);
        graph.AddEdge(3, 1);
        graph.AddEdge(4, 3);
        graph.AddEdge(3, 4);
        graph.AddEdge(3, 3);
        graph.AddEdge(4, 5);
        graph.AddEdge(5, 4);

        var vertices = graph.BreadthFirstSearch(5, 0);

        Assertions.assertFalse(vertices.isEmpty());
        Assertions.assertEquals(4, vertices.size());
        Assertions.assertEquals(6, vertices.get(0).Value);
        Assertions.assertEquals(5, vertices.get(1).Value);
        Assertions.assertEquals(4, vertices.get(2).Value);
        Assertions.assertEquals(1, vertices.get(3).Value);
    }

    @Test
    public void testBreadthFirstSearchWhenMiddleTwoEdges() {
        var graph = new SimpleGraph(7);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddVertex(6);
        graph.AddVertex(7);

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 0);
        graph.AddEdge(1, 6);
        graph.AddEdge(6, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(2, 0);
        graph.AddEdge(0, 3);
        graph.AddEdge(3, 0);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 2);
        graph.AddEdge(1, 3);
        graph.AddEdge(3, 1);
        graph.AddEdge(4, 3);
        graph.AddEdge(3, 4);
        graph.AddEdge(3, 3);
        graph.AddEdge(4, 5);
        graph.AddEdge(5, 4);

        var vertices = graph.BreadthFirstSearch(3, 6);

        Assertions.assertFalse(vertices.isEmpty());
        Assertions.assertEquals(3, vertices.size());
        Assertions.assertEquals(4, vertices.get(0).Value);
        Assertions.assertEquals(2, vertices.get(1).Value);
        Assertions.assertEquals(7, vertices.get(2).Value);
    }

    @Test
    public void testBreadthFirstSearchWhenMiddleTwoEdgesReverse() {
        var graph = new SimpleGraph(7);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddVertex(6);
        graph.AddVertex(7);

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 0);
        graph.AddEdge(1, 6);
        graph.AddEdge(6, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(2, 0);
        graph.AddEdge(0, 3);
        graph.AddEdge(3, 0);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 2);
        graph.AddEdge(1, 3);
        graph.AddEdge(3, 1);
        graph.AddEdge(4, 3);
        graph.AddEdge(3, 4);
        graph.AddEdge(3, 3);
        graph.AddEdge(4, 5);
        graph.AddEdge(5, 4);

        var vertices = graph.BreadthFirstSearch(6, 3);

        Assertions.assertFalse(vertices.isEmpty());
        Assertions.assertEquals(3, vertices.size());
        Assertions.assertEquals(7, vertices.get(0).Value);
        Assertions.assertEquals(2, vertices.get(1).Value);
        Assertions.assertEquals(4, vertices.get(2).Value);
    }

    @Test
    public void testBreadthFirstSearchWhenLastTwoVertexes() {
        var graph = new SimpleGraph(6);

        graph.AddVertex(10);
        graph.AddVertex(40);
        graph.AddVertex(30);
        graph.AddVertex(20);
        graph.AddVertex(5);
        graph.AddVertex(4);

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 0);
        graph.AddEdge(0, 2);
        graph.AddEdge(2, 0);
        graph.AddEdge(0, 3);
        graph.AddEdge(3, 0);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 2);
        graph.AddEdge(1, 3);
        graph.AddEdge(3, 1);
        graph.AddEdge(4, 3);
        graph.AddEdge(3, 4);
        graph.AddEdge(3, 3);
        graph.AddEdge(4, 5);
        graph.AddEdge(5, 4);

        var vertices = graph.BreadthFirstSearch(4, 5);

        Assertions.assertFalse(vertices.isEmpty());
        Assertions.assertEquals(2, vertices.size());
        Assertions.assertTrue(graph.IsEdge(4, 5));
        Assertions.assertEquals(5, vertices.get(0).Value);
        Assertions.assertEquals(4, vertices.get(1).Value);
    }

    @Test
    public void testBreadthFirstSearchWhenLastTwoVertexesReverse() {
        var graph = new SimpleGraph(6);

        graph.AddVertex(10);
        graph.AddVertex(40);
        graph.AddVertex(30);
        graph.AddVertex(20);
        graph.AddVertex(5);
        graph.AddVertex(4);

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 0);
        graph.AddEdge(0, 2);
        graph.AddEdge(2, 0);
        graph.AddEdge(0, 3);
        graph.AddEdge(3, 0);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 2);
        graph.AddEdge(1, 3);
        graph.AddEdge(3, 1);
        graph.AddEdge(4, 3);
        graph.AddEdge(3, 4);
        graph.AddEdge(3, 3);
        graph.AddEdge(4, 5);
        graph.AddEdge(5, 4);

        var vertices = graph.BreadthFirstSearch(5, 4);

        Assertions.assertFalse(vertices.isEmpty());
        Assertions.assertEquals(2, vertices.size());
        Assertions.assertTrue(graph.IsEdge(5, 4));
        Assertions.assertEquals(4, vertices.get(0).Value);
        Assertions.assertEquals(5, vertices.get(1).Value);
    }

    @Test
    public void testBreadthFirstSearchWhenRecursive() {
        var graph = new SimpleGraph(6);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddVertex(6);

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 0);
        graph.AddEdge(0, 2);
        graph.AddEdge(2, 0);
        graph.AddEdge(0, 3);
        graph.AddEdge(3, 0);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 2);
        graph.AddEdge(1, 3);
        graph.AddEdge(3, 1);
        graph.AddEdge(4, 1);
        graph.AddEdge(1, 4);
        graph.AddEdge(4, 3);
        graph.AddEdge(3, 4);
        graph.AddEdge(3, 3);
        graph.AddEdge(4, 5);
        graph.AddEdge(5, 4);

        var vertices = graph.BreadthFirstSearch(3, 3);

        Assertions.assertFalse(vertices.isEmpty());
        Assertions.assertEquals(2, vertices.size());
        Assertions.assertEquals(4, vertices.get(0).Value);
    }

    @Test
    public void testBreadthFirstSearchWhenOneEdgeExists() {
        var graph = new SimpleGraph(2);

        graph.AddVertex(1);
        graph.AddVertex(2);

        graph.AddEdge(0, 1);

        var vertices = graph.BreadthFirstSearch(0, 1);

        Assertions.assertFalse(vertices.isEmpty());
        Assertions.assertEquals(2, vertices.size());
        Assertions.assertEquals(1, vertices.get(0).Value);
        Assertions.assertEquals(2, vertices.get(1).Value);
    }


    @Test
    public void testBreadthFirstSearchWhenOneEdgeExistsReverse() {
        var graph = new SimpleGraph(2);

        graph.AddVertex(1);
        graph.AddVertex(2);

        graph.AddEdge(0, 1);

        var vertices = graph.BreadthFirstSearch(1, 0);

        Assertions.assertFalse(vertices.isEmpty());
        Assertions.assertEquals(2, vertices.get(0).Value);
        Assertions.assertEquals(1, vertices.get(1).Value);
    }

    @Test
    public void testBreadthFirstSearchShouldWhenRouteNotExists() {
        var graph = new SimpleGraph(6);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddVertex(6);

        graph.AddEdge(0, 1);
        graph.AddEdge(1, 0);
        graph.AddEdge(0, 2);
        graph.AddEdge(2, 0);
        graph.AddEdge(0, 3);
        graph.AddEdge(3, 0);
        graph.AddEdge(2, 3);
        graph.AddEdge(3, 2);
        graph.AddEdge(1, 3);
        graph.AddEdge(3, 1);
        graph.AddEdge(3, 3);
        graph.AddEdge(4, 5);
        graph.AddEdge(5, 4);

        var vertices = graph.BreadthFirstSearch(0, 5);

        Assertions.assertTrue(vertices.isEmpty());
    }

    @Test
    public void testWeakVertices() {
        var graph = new SimpleGraph(9);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);
        graph.AddVertex(4);
        graph.AddVertex(5);
        graph.AddVertex(6);
        graph.AddVertex(7);
        graph.AddVertex(8);
        graph.AddVertex(9);

        graph.AddEdge(0, 1);
        graph.AddEdge(0, 3);
        graph.AddEdge(0, 4);
        graph.AddEdge(1, 5);
        graph.AddEdge(2, 5);
        graph.AddEdge(2, 6);
        graph.AddEdge(3, 7);
        graph.AddEdge(3, 4);
        graph.AddEdge(4, 7);
        graph.AddEdge(4, 5);
        graph.AddEdge(5, 6);
        graph.AddEdge(6, 8);

        var vertices = graph.WeakVertices();

        Assertions.assertFalse(vertices.isEmpty());
    }

    @Test
    public void testWeakVerticesWhenOneTriangle() {
        var graph = new SimpleGraph(3);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);

        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);

        var vertices = graph.WeakVertices();

        Assertions.assertFalse(vertices.isEmpty());
        Assertions.assertEquals(3, vertices.size());
        Assertions.assertTrue(vertices.stream().anyMatch(v -> v.Value == 1));
        Assertions.assertTrue(vertices.stream().anyMatch(v -> v.Value == 2));
        Assertions.assertTrue(vertices.stream().anyMatch(v -> v.Value == 3));
    }

    @Test
    public void testWeakVerticesWhenEmpty() {
        var graph = new SimpleGraph(3);

        graph.AddVertex(1);
        graph.AddVertex(2);
        graph.AddVertex(3);

        graph.AddEdge(0, 1);
        graph.AddEdge(0, 2);
        graph.AddEdge(1, 2);

        var vertices = graph.WeakVertices();

        Assertions.assertTrue(vertices.isEmpty());
    }


}

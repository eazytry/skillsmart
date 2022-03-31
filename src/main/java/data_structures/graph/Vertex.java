package data_structures.graph;

import java.util.*;
import java.util.stream.Collectors;

class Vertex {
    public int Value;
    public boolean Hit;

    public Vertex(int val) {
        Value = val;
        Hit = false;
    }
}

class SimpleGraph {
    Vertex[] vertex;
    int[][] m_adjacency;
    int max_vertex;

    public SimpleGraph(int size) {
        max_vertex = size;
        m_adjacency = new int[size][size];
        vertex = new Vertex[size];
    }

    public void AddVertex(int value) {
        vertex[findEmptyVertexIndex()] = new Vertex(value);
        // ваш код добавления новой вершины
        // с значением value
        // в незанятую позицию vertex
    }

    private int findEmptyVertexIndex() {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == null)
                return i;
        }
        throw new IllegalStateException("Graph is full");
    }

    // здесь и далее, параметры v -- индекс вершины
    // в списке  vertex
    public void RemoveVertex(int v) {
        if (v >= max_vertex)
            throw new IllegalArgumentException("Index must be less than max_vertex index");

        vertex[v] = null;
        for (int i = 0; i < max_vertex; i++) {
            m_adjacency[v][i] = 0;
            m_adjacency[i][v] = 0;
        }
        // ваш код удаления вершины со всеми её рёбрами
    }

    public boolean IsEdge(int v1, int v2) {

        // true если есть ребро между вершинами v1 и v2
        return m_adjacency[v1][v2] == 1;
    }

    public void AddEdge(int v1, int v2) {
        m_adjacency[v1][v2] = 1;
        m_adjacency[v2][v1] = 1;
        // добавление ребра между вершинами v1 и v2
    }

    public void RemoveEdge(int v1, int v2) {
        m_adjacency[v2][v1] = 0;
        m_adjacency[v1][v2] = 0;
        // удаление ребра между вершинами v1 и v2
    }

    public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo) {
        if (max_vertex == 0) {
            return new ArrayList<>();
        }
        clearHits();
        Stack<Integer> stack = new Stack<>();
        vertex[VFrom].Hit = true;
        stack.push(VFrom);

        return DepthFirstSearch(stack, VTo).stream().map(index -> vertex[index]).collect(Collectors.toCollection(ArrayList::new));
        // Узлы задаются позициями в списке vertex.
        // Возвращается список узлов -- путь из VFrom в VTo.
        // Список пустой, если пути нету.
    }

    private void clearHits() {
        Arrays.stream(vertex).forEach(v -> v.Hit = false);
    }

    private Stack<Integer> DepthFirstSearch(Stack<Integer> stack, int VTo) {
        if (stack.isEmpty()) {
            return stack;
        }
        var foundVertex = searchLinkedNotHitVertexOrToIndex(stack.peek());
        if (foundVertex == null) {
            stack.pop();
        } else {
            vertex[foundVertex].Hit = true;
            stack.push(foundVertex);
            if (foundVertex == VTo) {
                return stack;
            }
        }
        return DepthFirstSearch(stack, VTo);
    }

    private Integer searchLinkedNotHitVertexOrToIndex(int index) {
        for (int i = 0; i < max_vertex; i++) {
            if ((m_adjacency[i][index] > 0 || m_adjacency[index][i] > 0) && !vertex[i].Hit) {
                return i;
            }
        }
        return null;
    }
}
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
        if (VFrom >= vertex.length || VTo >= vertex.length) {
            return new ArrayList<>();
        }
        clearHits();
        Stack<Integer> stack = new Stack<>();
        vertex[VFrom].Hit = true;
        stack.push(VFrom);

        return DepthFirstSearch(stack, VTo)
                .stream()
                .map(index -> vertex[index])
                .collect(Collectors.toCollection(ArrayList::new));
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
        var foundVertex = searchLinkedNotHitVertexOrToIndex(stack.peek(), VTo);
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

    private Integer searchLinkedNotHitVertexOrToIndex(int index, int toIndex) {
        HashSet<Integer> candidates = new HashSet<>();
        for (int i = 0; i < vertex.length; i++) {
            if ((m_adjacency[i][index] > 0 || m_adjacency[index][i] > 0) && !vertex[i].Hit) {
                candidates.add(i);
            }
        }
        if (candidates.contains(toIndex)) {
            return toIndex;
        } else if (candidates.isEmpty()) {
            return null;
        } else {
            return candidates.stream().findAny().get();
        }
    }

    public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo) {
        if (vertex.length < 1) {
            return new ArrayList<>();
        }
        if (VFrom == VTo && m_adjacency[VFrom][VTo] == 1) {
            return new ArrayList<>(List.of(vertex[VFrom], vertex[VTo]));
        }
        clearHits();
        var edges = findAllEdges(VTo, new LinkedList<>(List.of(VFrom)), new HashSet<>());
        var route = findRoute(VFrom, VTo, edges);
        return reverseRoute(route);
    }

    private Set<Edge> findAllEdges(int VTo, Queue<Integer> queue, Set<Edge> edges) {
        if (queue.isEmpty()) {
            return new HashSet<>();
        }
        var polled = queue.poll();

        if (polled == VTo) {
            return new HashSet<>(edges);
        }

        findAllNotHitLinkedVertexIndexes(polled)
                .forEach(index -> {
                    vertex[index].Hit = true;
                    queue.offer(index);
                    edges.add(new Edge(polled, index));
                });
        return findAllEdges(VTo, queue, edges);
    }

    private List<Edge> findRoute(Integer start, Integer finish, Set<Edge> edges) {
        if (edges.isEmpty()) {
            return new ArrayList<>();
        }
        var lastEdge = findLastEdge(finish, edges);
        var result = new ArrayList<>(List.of(lastEdge));
        Integer currStart = lastEdge.getV1();
        Integer currFinish = lastEdge.getV1();

        while (!start.equals(currStart)) {
            var edge = start > finish
                    ? new Edge(Math.abs(++currStart), currFinish)
                    : new Edge(Math.abs(--currStart), currFinish);
        if (edges.contains(edge)) {
            result.add(edge);
            currStart = edge.getV1();
            currFinish = edge.getV1();
        }
    }
        return result;
}

    private ArrayList<Vertex> reverseRoute(List<Edge> edges) {
        if (edges.isEmpty()) {
            return new ArrayList<>();
        }
        var result = new ArrayList<>(List.of(vertex[edges.get(edges.size() - 1).getV1()]));
        for (int i = edges.size() - 1; i >= 0; i--) {
            result.add(vertex[edges.get(i).getV2()]);
        }
        return result;
    }

    private Edge findLastEdge(Integer finish, Set<Edge> edges) {
        return edges.stream().filter(e -> finish.equals(e.getV2())).findFirst().get();
    }

    public Set<Integer> findAllNotHitLinkedVertexIndexes(Integer index) {
        Set<Integer> indexes = new HashSet<>();
        for (int i = 0; i < vertex.length; i++) {
            if ((m_adjacency[i][index] == 1 || m_adjacency[index][i] == 1) && !vertex[i].Hit) {
                indexes.add(i);
            }
        }
        return indexes;
    }

private static class Edge {
    private final Integer v1;
    private final Integer v2;

    public Edge(Integer v1, Integer v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public Integer getV1() {
        return v1;
    }

    public Integer getV2() {
        return v2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        if (v1 != null ? !v1.equals(edge.v1) : edge.v1 != null) return false;
        return v2 != null ? v2.equals(edge.v2) : edge.v2 == null;
    }

    @Override
    public int hashCode() {
        int result = v1 != null ? v1.hashCode() : 0;
        result = 31 * result + (v2 != null ? v2.hashCode() : 0);
        return result;
    }
}
}
package data_structures.graph;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /* @invariant max_vertex >= 0 */
    /* @invariant m_adjacency != null */
    /* @invariant vertex != null */

    public SimpleGraph(int size) {
        max_vertex = size;
        m_adjacency = new int[size][size];
        vertex = new Vertex[size];
    }

    public void AddVertex(int value) {
        vertex[findEmptyVertexIndex()] = new Vertex(value);
    }

    private int findEmptyVertexIndex() {
        for (int i = 0; i < vertex.length; i++) {
            if (vertex[i] == null)
                return i;
        }
        throw new IllegalStateException("Graph is full");
    }

    /* @requires v >= 0 */
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

    /*
     в списке  vertex
    @
     @requires v1 >= 0
     @requires v2 >= 0
     @requires v1 <= max_vertex
     @requires v2 <= max_vertex
     @
    */
    public boolean IsEdge(int v1, int v2) {
        // true если есть ребро между вершинами v1 и v2
        return m_adjacency[v1][v2] == 1;
    }

    /*
     @requires v1 >= 0
     @requires v2 >= 0
     @requires v1 <= max_vertex
     @requires v2 <= max_vertex
     @
    */
    public void AddEdge(int v1, int v2) {
        m_adjacency[v1][v2] = 1;
        m_adjacency[v2][v1] = 1;
        // добавление ребра между вершинами v1 и v2
    }

    /*
     @requires v1 >= 0
     @requires v2 >= 0
     @requires v1 <= max_vertex
     @requires v2 <= max_vertex
    */
    public void RemoveEdge(int v1, int v2) {
        m_adjacency[v2][v1] = 0;
        m_adjacency[v1][v2] = 0;
        // удаление ребра между вершинами v1 и v2
    }

    /*
     @requires VFrom >= 0
     @requires VTo >= 0
     @requires VFrom <= max_vertex
     @requires VTo <= max_vertex
    */
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

    // Не проверяет stack на null и VTo на минусовые значения
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
            boolean isEdge = m_adjacency[i][index] > 0 || m_adjacency[index][i] > 0;
            if (isEdge && !vertex[i].Hit) {
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
        // Метод clearHits здесь очень важен, без него все последующие поиски будут работать не корректно
        clearHits();
        var edges = findAllEdges(VTo, new LinkedList<>(List.of(VFrom)), new HashSet<>());
        var route = findRoute(VFrom, VTo, edges);
        return reverseRoute(route);
    }

    private Set<Edge> findAllEdges(int VTo, Queue<Integer> queue, Set<Edge> edges) {
        if (queue.isEmpty()) {
            return new HashSet<>();
        }
        var polledIndex = queue.poll();

        if (polledIndex == VTo) {
            return new HashSet<>(edges);
        }

        findAllNotHitLinkedVertexIndexes(polledIndex)
                .forEach(index -> {
                    vertex[index].Hit = true;
                    queue.offer(index);
                    edges.add(new Edge(polledIndex, index));
                });
        return findAllEdges(VTo, queue, edges);
    }

    private List<Edge> findRoute(Integer start, Integer finish, Set<Edge> edges) {
        if (edges.isEmpty()) {
            return new ArrayList<>();
        }
        var lastEdge = findLastEdge(finish, edges);
        var result = new ArrayList<>(List.of(lastEdge));
        Integer currStart = lastEdge.getFromIndex();
        Integer currFinish = lastEdge.getFromIndex();

        while (!start.equals(currStart)) {
            var edge = start > finish
                    ? new Edge(Math.abs(++currStart), currFinish)
                    : new Edge(Math.abs(--currStart), currFinish);
            if (edges.contains(edge)) {
                result.add(edge);
                currStart = edge.getFromIndex();
                currFinish = edge.getFromIndex();
            }
        }
        return result;
    }

    private ArrayList<Vertex> reverseRoute(List<Edge> route) {
        if (route.isEmpty()) {
            return new ArrayList<>();
        }
        var result = new ArrayList<>(List.of(vertex[route.get(route.size() - 1).getFromIndex()]));
        for (int i = route.size() - 1; i >= 0; i--) {
            result.add(vertex[route.get(i).getToIndex()]);
        }
        return result;
    }

    // Поиск ребра, которое ссылается на последний элемент с индексом finish
    private Edge findLastEdge(Integer finish, Set<Edge> edges) {
        return edges.stream().filter(e -> finish.equals(e.getToIndex())).findFirst().get();
    }

    public Set<Integer> findAllNotHitLinkedVertexIndexes(Integer index) {
        Set<Integer> notHitLinkedIndexSet = new HashSet<>();
        for (int i = 0; i < vertex.length; i++) {
            // если есть ребро и вершина не посещена
            if ((m_adjacency[i][index] == 1 || m_adjacency[index][i] == 1) && !vertex[i].Hit) {
                notHitLinkedIndexSet.add(i);
            }
        }
        return notHitLinkedIndexSet;
    }

    public ArrayList<Vertex> WeakVertices() {
        var weakVerticesSet = Stream.iterate(0, i -> i < vertex.length, i -> i + 1)
                .collect(Collectors.toCollection(HashSet::new));

        for (int i = 0; i < vertex.length; i++) {
            var linkedVertexList = findLinkedVertexIndexes(i);
            var trianglePartsSet = findTriangleVertexIndexes(linkedVertexList);
            if (!trianglePartsSet.isEmpty()) {
                weakVerticesSet.remove(i);
            }
            weakVerticesSet.removeAll(trianglePartsSet);
        }
        return weakVerticesSet.stream().map(i -> vertex[i]).collect(Collectors.toCollection(ArrayList::new));
    }

    // нет проверки на минусовые значения
    private List<Integer> findLinkedVertexIndexes(int index) {
        var linkedVertexIndexList = new ArrayList<Integer>();
        for (int i = 0; i < vertex.length; i++) {
            if (m_adjacency[i][index] == 1 || m_adjacency[index][i] == 1) {
                linkedVertexIndexList.add(i);
            }
        }
        return linkedVertexIndexList;
    }

    // Нельзя передавать null
    private Set<Integer> findTriangleVertexIndexes(List<Integer> candidates) {
        var linkedCandidatesIndexes = new HashSet<Integer>();
        for (int i = 0; i < candidates.size(); i++) {
            for (int j = i; j < candidates.size(); j++) {
                boolean isEdge = m_adjacency[candidates.get(i)][candidates.get(j)] == 1
                        || m_adjacency[candidates.get(j)][candidates.get(i)] == 1;
                if (isEdge) {
                    linkedCandidatesIndexes.add(candidates.get(i));
                    linkedCandidatesIndexes.add(candidates.get(j));
                }
            }
        }
        return candidates.stream().filter(linkedCandidatesIndexes::contains).collect(Collectors.toSet());
    }

    private static class Edge {
        private final Integer fromIndex;
        private final Integer toIndex;

        public Edge(Integer fromIndex, Integer toIndex) {
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        public Integer getFromIndex() {
            return fromIndex;
        }

        public Integer getToIndex() {
            return toIndex;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Edge edge = (Edge) o;

            if (fromIndex != null ? !fromIndex.equals(edge.fromIndex) : edge.fromIndex != null) return false;
            return toIndex != null ? toIndex.equals(edge.toIndex) : edge.toIndex == null;
        }

        @Override
        public int hashCode() {
            int result = fromIndex != null ? fromIndex.hashCode() : 0;
            result = 31 * result + (toIndex != null ? toIndex.hashCode() : 0);
            return result;
        }
    }
}
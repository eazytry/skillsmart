package data_structures.tree;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleTreeNode<T> {
    public T NodeValue;
    public SimpleTreeNode<T> Parent;
    public List<SimpleTreeNode<T>> Children;

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        NodeValue = val;
        Parent = parent;
        Children = null;
    }
}

class SimpleTree<T> {
    public SimpleTreeNode<T> root;
    // Root не может быть NULL
    public SimpleTree(SimpleTreeNode<T> root) {
        this.root = root;
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
        NewChild.Parent = ParentNode;
        if (ParentNode.Children == null) {
            ParentNode.Children = new ArrayList<>();
        }
        ParentNode.Children.add(NewChild);
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
        if (NodeToDelete != null
                && NodeToDelete.Parent != null
                && NodeToDelete.Parent.Children != null
                && !NodeToDelete.equals(root))
            NodeToDelete.Parent.Children.remove(NodeToDelete);
    }

    public List<SimpleTreeNode<T>> GetAllNodes() {
        return getSubNodes(root);
    }

    private List<SimpleTreeNode<T>> getSubNodes(SimpleTreeNode<T> node) {
        List<SimpleTreeNode<T>> nodes = new ArrayList<>();
        nodes.add(node);
        if (node.Children == null || node.Children.isEmpty()) {
            return nodes;
        }
        for (SimpleTreeNode<T> subNode : node.Children) {
            nodes.addAll(getSubNodes(subNode));
        }
        return nodes;
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
        return findNodesByValue(root, val);
    }

    private List<SimpleTreeNode<T>> findNodesByValue(SimpleTreeNode<T> node, T val) {
        List<SimpleTreeNode<T>> nodes = new ArrayList<>();
        if (node.NodeValue != null && node.NodeValue.equals(val)) {
            nodes.add(node);
        }
        if (node.Children == null || node.Children.isEmpty()) {
            return nodes;
        }
        for (SimpleTreeNode<T> subNode : node.Children) {
            nodes.addAll(findNodesByValue(subNode, val));
        }
        return nodes;
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
        if (NewParent.Children == null) {
            NewParent.Children = new ArrayList<>();
        }
        if (OriginalNode.Parent != null) {
            OriginalNode.Parent.Children.remove(OriginalNode);
        }
        NewParent.Children.add(OriginalNode);
        OriginalNode.Parent = NewParent;
    }

    public int Count() {
        return GetAllNodes().size();
    }

    public int LeafCount() {
        return findLeafs(root).size();
    }

    private List<SimpleTreeNode<T>> findLeafs(SimpleTreeNode<T> node) {
        List<SimpleTreeNode<T>> nodeLeafsList = new ArrayList<>();
        if (node.Children == null || node.Children.isEmpty()) {
            nodeLeafsList.add(node);
            return nodeLeafsList;
        }
        for (SimpleTreeNode<T> subNode : node.Children) {
            nodeLeafsList.addAll(findLeafs(subNode));
        }
        return nodeLeafsList;
    }

    public ArrayList<T> EvenTrees() {
        var removeVertexes = new ArrayList<SimpleTreeNode<T>>();
        if (root != null)
            getVertexCount(root, removeVertexes);
        return removeVertexes.stream().map(vertex -> vertex.NodeValue).collect(Collectors.toCollection(ArrayList::new));
        // ...
    }

    private int getVertexCount(SimpleTreeNode<T> node, ArrayList<SimpleTreeNode<T>> removeEdges) {
        if (node.Children == null || node.Children.isEmpty())
            return 1;
        else {
            int vertexCount = node.Children
                    .stream()
                    .map(ch -> getVertexCount(ch, removeEdges))
                    .reduce(Integer::sum)
                    .orElse(0);
            if ((vertexCount + 1) % 2 == 0
                    && node.Parent != null
                    && node.Parent.Children.stream().filter(ch -> !removeEdges.contains(ch)).count() > 1) {
                removeEdges.addAll(List.of(node.Parent, node));
                return 0;
            } else {
                return vertexCount + 1;
            }
        }
    }
}
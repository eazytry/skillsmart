import java.util.*;

public class SimpleTreeNode<T> {
    public T NodeValue; // значение в узле
    public SimpleTreeNode<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        NodeValue = val;
        Parent = parent;
        Children = null;
    }
}

class SimpleTree<T> {
    public SimpleTreeNode<T> root; // корень, может быть null

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
        List<SimpleTreeNode<T>> nodes = new ArrayList<>();
        if (node.Children == null || node.Children.isEmpty()) {
            nodes.add(node);
            return nodes;
        }
        for (SimpleTreeNode<T> subNode : node.Children) {
            nodes.addAll(findLeafs(subNode));
        }
        return nodes;
    }
}
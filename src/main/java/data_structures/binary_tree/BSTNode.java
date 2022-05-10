package data_structures.binary_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


class BSTNode<T> {
    public int NodeKey;
    public T NodeValue;
    public BSTNode<T> Parent;
    public BSTNode<T> LeftChild;
    public BSTNode<T> RightChild;

    public BSTNode(int key, T val, BSTNode<T> parent) {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BSTFind<T> {
    public BSTNode<T> Node;

    public boolean NodeHasKey;

    public boolean ToLeft;

    public BSTFind() {
        Node = null;
    }
}

class BST<T> {
    BSTNode<T> Root; // корень дерева, или null
    int count = 0;

    public BST(BSTNode<T> node) {
        if (node != null)
            count++;
        Root = node;
    }

    public BSTFind<T> FindNodeByKey(int key) {
        BSTFind<T> found = new BSTFind<>();
        found.Node = Root;

        while (hasNext(key, found.Node)) {
            found.Node = key > found.Node.NodeKey ? found.Node.RightChild : found.Node.LeftChild;
        }

        if (found.Node != null) {
            if (found.Node.NodeKey == key) {
                found.NodeHasKey = true;
            } else {
                found.NodeHasKey = false;
                found.ToLeft = key < found.Node.NodeKey;
            }
        }
        return found;
    }

    private boolean hasNext(int key, BSTNode<T> node) {
        if (node == null)
            return false;
        return key != node.NodeKey && (key > node.NodeKey ? node.RightChild != null : node.LeftChild != null);
    }

    public boolean AddKeyValue(int key, T val) {
        var found = FindNodeByKey(key);

        if (!found.NodeHasKey) {
            if (found.Node == null) {
                Root = new BSTNode<>(key, val, null);
            } else {
                if (found.ToLeft)
                    found.Node.LeftChild = new BSTNode<>(key, val, found.Node);
                else
                    found.Node.RightChild = new BSTNode<>(key, val, found.Node);
            }
            count++;
            return true;
        }
        return false; // если ключ уже есть
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) {
        return FindMax ? findMax(FromNode) : findMin(FromNode);
    }

    private BSTNode<T> findMin(BSTNode<T> fromNode) {
        if (fromNode == null) {
            return null;
        }
        var found = fromNode;

        while (found.LeftChild != null) {
            found = found.LeftChild;
        }

        return found;
    }

    private BSTNode<T> findMax(BSTNode<T> fromNode) {
        if (fromNode == null) {
            return null;
        }
        var found = fromNode;

        while (found.RightChild != null) {
            found = found.RightChild;
        }

        return found;
    }

    public boolean DeleteNodeByKey(int key) {
        var found = FindNodeByKey(key);

        if (found.NodeHasKey) {
            var nodeToDelete = found.Node;
            BSTNode<T> candidate;

            if (nodeToDelete.LeftChild != null && nodeToDelete.RightChild != null) {
                candidate = findMin(nodeToDelete.RightChild);
                // Если у кандидата есть правый узел - заменяем кандидата на его правый узел
                if (candidate.RightChild != null) {
                    candidate.RightChild.Parent = candidate.Parent;
                }
                // Если у кандидата есть правые узел - присваиваем его в левого потомка родителя удаляемого узла
                candidate.Parent.LeftChild = candidate.RightChild;

                candidate.LeftChild = nodeToDelete.LeftChild;
                candidate.RightChild = nodeToDelete.RightChild;
            }
            else {
                candidate = nodeToDelete.LeftChild == null && nodeToDelete.RightChild == null
                        ? null
                        : nodeToDelete.LeftChild == null
                        ? nodeToDelete.RightChild
                        : nodeToDelete.LeftChild;
            }
            if (nodeToDelete == Root) {
                Root = candidate;
            }
            if (nodeToDelete.Parent != null) {
                if (isChildLeft(nodeToDelete.Parent, nodeToDelete)) {
                    nodeToDelete.Parent.LeftChild = candidate;
                } else {
                    nodeToDelete.Parent.RightChild = candidate;
                }
            }
            if (candidate != null) {
                candidate.Parent = nodeToDelete.Parent;
            }
            count--;
            return true;
        }
        return false; // если узел не найден
    }

    private boolean isChildLeft(BSTNode<T> parent, BSTNode<T> child) {
        assert parent != null;
        return parent.LeftChild == child;
    }

    public int Count() {
        return count; // количество узлов в дереве
    }

    public ArrayList<BSTNode> WideAllNodes() {
        return WideAllNodes(Root);
    }

    public ArrayList<BSTNode> WideAllNodes(BSTNode node) {
        if (node == null) {
            return new ArrayList<>();
        }
        Deque<BSTNode> queue = new ArrayDeque<>(List.of(node));
        ArrayList<BSTNode> list = new ArrayList<>();

        while (!queue.isEmpty()) {
            var polled = queue.pollLast();
            list.add(polled);
            if (polled.LeftChild != null) {
                queue.addFirst(polled.LeftChild);
            }
            if (polled.RightChild != null) {
                queue.addFirst(polled.RightChild);
            }
        }
        return list;
    }

    ArrayList<BSTNode> DeepAllNodes(final int order) {
        assert order == 0 || order == 1 || order == 2;
        if (Root == null) {
            return new ArrayList<>();
        }
        switch (order) {
            case 0:
                return inOrder(Root);
            case 1:
                return postOrder(Root);
            case 2:
                return preOrder(Root);
        }
        return new ArrayList<>();
    }

    ArrayList<BSTNode> inOrder(BSTNode node) {
        assert node != null;
        var list = new ArrayList<BSTNode>();
        if (node.LeftChild != null) {
            list.addAll(inOrder(node.LeftChild));
        }
        list.add(node);
        if (node.RightChild != null) {
            list.addAll(inOrder(node.RightChild));
        }
        return list;
    }

    ArrayList<BSTNode> preOrder(BSTNode node) {
        assert node != null;
        var list = new ArrayList<BSTNode>();
        list.add(node);
        if (node.LeftChild != null) {
            list.addAll(preOrder(node.LeftChild));
        }
        if (node.RightChild != null) {
            list.addAll(preOrder(node.RightChild));
        }
        return list;
    }

    ArrayList<BSTNode> postOrder(BSTNode node) {
        assert node != null;
        var list = new ArrayList<BSTNode>();
        if (node.LeftChild != null) {
            list.addAll(postOrder(node.LeftChild));
        }
        if (node.RightChild != null) {
            list.addAll(postOrder(node.RightChild));
        }
        list.add(node);
        return list;
    }
}
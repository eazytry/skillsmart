package data_structures.binary_tree;

import java.io.*;
import java.util.*;


class BSTNode<T> {
    public int NodeKey; // ключ узла
    public T NodeValue; // значение в узле
    public BSTNode<T> Parent; // родитель или null для корня
    public BSTNode<T> LeftChild; // левый потомок
    public BSTNode<T> RightChild; // правый потомок	

    public BSTNode(int key, T val, BSTNode<T> parent) {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

// промежуточный результат поиска
class BSTFind<T> {
    // null если в дереве вообще нету узлов
    public BSTNode<T> Node;

    // true если узел найден
    public boolean NodeHasKey;

    // true, если родительскому узлу надо добавить новый левым
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

        while (found.Node != null) {
            var temp = key >= found.Node.NodeKey ? found.Node.RightChild : found.Node.LeftChild;
            if (temp == null)
                break;
            found.Node = temp;
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

    public boolean AddKeyValue(int key, T val) {
        var found = FindNodeByKey(key);

        if (!found.NodeHasKey) {
            var newNode = new BSTNode<>(key, val, null);
            if (found.Node == null) {
                Root = newNode;
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
            var node = found.Node;

            if (node.LeftChild == null || node.RightChild == null) {
                if (node.LeftChild == null) {
                    moveNodeInsteadOfChild(node, node.RightChild);
                } else {
                    moveNodeInsteadOfChild(node, node.LeftChild);
                }
            } else {

            }
            count--;
            return true;
        } else {
            return false; // если узел не найден
        }
    }

    private boolean isChildLeft(BSTNode<T> parent, BSTNode<T> child) {
        return parent.LeftChild == child;
    }

    private void moveNodeInsteadOfChild(BSTNode<T> node, BSTNode<T> instead) {
        var parent = node.Parent;

        if (isChildLeft(parent, node)) {
            parent.RightChild = instead;
        } else {
            parent.LeftChild = instead;
        }
    }

    public int Count() {
        return count; // количество узлов в дереве
    }
}
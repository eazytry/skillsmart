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

    public BST(BSTNode<T> node) {
        Root = node;
    }

    public BSTFind<T> FindNodeByKey(int key) {
        BSTFind<T> found = new BSTFind<>();

        if (Root == null) {
            found.Node = null;
            return found;
        }

        BSTNode<T> prev = null;
        BSTNode<T> curr = Root;

        while (curr != null) {
            prev = curr;
            if (key >  curr.NodeKey) {
                curr = curr.RightChild;
            }
            curr = curr.LeftChild;
        }

        if (curr == null) {
            found.Node = prev;
            found.NodeHasKey = true;
            if (key > prev.NodeKey) {
                found.ToLeft = false;
            } else {
                found.ToLeft = true;
            }
            return found;
        } else {
            found.Node = curr;
            found.NodeHasKey = true;
            return found;
        }
    }

    public boolean AddKeyValue(int key, T val) {
        // добавляем ключ-значение в дерево
        return false; // если ключ уже есть
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) {
        // ищем максимальный/минимальный ключ в поддереве
        return null;
    }

    public boolean DeleteNodeByKey(int key) {
        // удаляем узел по ключу
        return false; // если узел не найден
    }

    public int Count() {
        return 0; // количество узлов в дереве
    }

}
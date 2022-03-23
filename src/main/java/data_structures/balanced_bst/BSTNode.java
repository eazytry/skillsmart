package data_structures.balanced_bst;

import java.util.Arrays;

class BSTNode {
    public int NodeKey; // ключ узла
    public BSTNode Parent; // родитель или null для корня
    public BSTNode LeftChild; // левый потомок
    public BSTNode RightChild; // правый потомок	
    public int Level; // глубина узла

    public BSTNode(int key, BSTNode parent) {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BalancedBST {
    public BSTNode Root; // корень дерева

    public BalancedBST() {
        Root = null;
    }

    public void GenerateTree(int[] a) {
        Arrays.sort(a);

        findNode(a, null, 0);
        // создаём дерево с нуля из неотсортированного массива a
        // ...
    }

    public BSTNode findNode(int[] a, BSTNode parent, int level) {
        if (a.length == 0) {
            return null;
        }
        var currentKeyIndex = a.length / 2;
        BSTNode current = new BSTNode(a[currentKeyIndex], parent);
        current.Level = level;
        if (this.Root == null) {
            this.Root = current;
        }
        if (a.length > 1) {
            current.LeftChild = findNode(Arrays.copyOfRange(a, 0, currentKeyIndex), current, level + 1);
            current.RightChild = findNode(Arrays.copyOfRange(a, currentKeyIndex + 1, a.length), current, level + 1);
        }

        return current;
    }

    public boolean IsBalanced(BSTNode root_node) {
        if (root_node == null) {
            return false;
        }
        if (root_node.LeftChild == null && root_node.RightChild == null) {
            return true;
        }
        if (root_node.LeftChild == null) {
            return root_node.RightChild.LeftChild == null && root_node.RightChild.RightChild == null;
        }
        if (root_node.RightChild == null){
            return root_node.LeftChild.LeftChild == null && root_node.LeftChild.RightChild == null;
        }

        var isLeftSubtreeBalanced = IsBalanced(root_node.LeftChild);
        var isRightSubtreeBalanced = IsBalanced(root_node.RightChild);


        return isLeftSubtreeBalanced && isRightSubtreeBalanced; // сбалансировано ли дерево с корнем root_node
    }
} 
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
        var foundNodeIndex = a.length / 2;
        BSTNode foundNode = new BSTNode(a[foundNodeIndex], parent);
        foundNode.Level = level;
        if (this.Root == null) {
            this.Root = foundNode;
        }
        if (a.length > 1) {
            foundNode.LeftChild = findNode(Arrays.copyOfRange(a, 0, foundNodeIndex), foundNode, level + 1);
            foundNode.RightChild = findNode(Arrays.copyOfRange(a, foundNodeIndex + 1, a.length), foundNode, level + 1);
        }

        return foundNode;
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
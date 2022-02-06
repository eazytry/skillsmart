package data_structures.binary_tree;

import data_structures.binary_tree.BST;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class BinaryTreeTest {
    @Test
    public void findNodeByKeyWhenExists() {
        //when
        var root = new BSTNode<>(5, "1234", null);
        var bst = new BST<>(root);

        var rootLeft = new BSTNode<>(4, "1234", root);
        var rootRight = new BSTNode<>(6, "1234", root);
        var rootLeftLeft = new BSTNode<>(3, "1234", rootLeft);
        root.LeftChild = rootLeft;
        root.RightChild = rootRight;
        rootLeft.LeftChild = rootLeftLeft;

        var expected = new BSTFind<String>();
        expected.NodeHasKey = true;
        expected.ToLeft = false;
        expected.Node = rootLeftLeft;

        var actual = bst.FindNodeByKey(3);
        Assertions.assertEquals(expected, actual);
    }
}

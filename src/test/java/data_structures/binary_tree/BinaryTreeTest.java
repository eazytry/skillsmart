package data_structures.binary_tree;

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

        var actual = bst.FindNodeByKey(3);

        Assertions.assertTrue(actual.NodeHasKey);
        Assertions.assertFalse(actual.ToLeft);
        Assertions.assertEquals(rootLeftLeft, actual.Node);
    }

    @Test
    public void findNodeByKeyWhenCollisionAndNotExists() {
        //when
        var root = new BSTNode<>(5, "1234", null);
        var bst = new BST<>(root);

        var rootLeft = new BSTNode<>(4, "1234", root);
        var rootRight = new BSTNode<>(6, "1234", root);
        var rootRightRight = new BSTNode<>(6, "123", rootRight);
        root.LeftChild = rootLeft;
        root.RightChild = rootRight;
        rootRight.RightChild = rootRightRight;

        var actual = bst.FindNodeByKey(7);

        Assertions.assertFalse(actual.NodeHasKey);
        Assertions.assertFalse(actual.ToLeft);
        Assertions.assertEquals(rootRightRight, actual.Node);
    }

    @Test
    public void findNodeByKeyWhenNotExistsAndMoreThanParent() {
        //when
        var root = new BSTNode<>(50, "1234", null);
        var bst = new BST<>(root);

        var rootLeft = new BSTNode<>(4, "1234", root);
        var rootRight = new BSTNode<>(100, "1234", root);
        var rootRightRight = new BSTNode<>(150, "123", rootRight);
        root.LeftChild = rootLeft;
        root.RightChild = rootRight;
        rootRight.RightChild = rootRightRight;

        var actual = bst.FindNodeByKey(200);

        Assertions.assertFalse(actual.NodeHasKey);
        Assertions.assertFalse(actual.ToLeft);
        Assertions.assertEquals(rootRightRight, actual.Node);
    }

    @Test
    public void findNodeByKeyWhenNotExistsAndLessThanParent() {
        //when
        var root = new BSTNode<>(50, "1234", null);
        var bst = new BST<>(root);

        var rootLeft = new BSTNode<>(4, "1234", root);
        var rootRight = new BSTNode<>(100, "1234", root);
        var rootRightRight = new BSTNode<>(150, "123", rootRight);
        root.LeftChild = rootLeft;
        root.RightChild = rootRight;
        rootRight.RightChild = rootRightRight;

        var actual = bst.FindNodeByKey(125);

        Assertions.assertFalse(actual.NodeHasKey);
        Assertions.assertTrue(actual.ToLeft);
        Assertions.assertEquals(rootRightRight, actual.Node);
    }

    @Test
    public void findNodeByKeyWhenRootIsNull() {
        //when
        var bst = new BST<>(null);


        var actual = bst.FindNodeByKey(125);

        Assertions.assertFalse(actual.NodeHasKey);
        Assertions.assertFalse(actual.ToLeft);
        Assertions.assertNull(actual.Node);
    }

    @Test
    public void addNodeWhenEmpty() {
        //when
        var bst = new BST<>(null);

        var actual = bst.AddKeyValue(125, "123");
        ;

        Assertions.assertTrue(actual);
        Assertions.assertEquals(bst.Root, bst.FindNodeByKey(125).Node);
    }

    @Test
    public void addNodeWhenToRight() {
        //when
        var root = new BSTNode<>(50, "1234", null);
        var bst = new BST<>(root);

        var rootLeft = new BSTNode<>(4, "1234", root);
        var rootRight = new BSTNode<>(100, "1234", root);

        root.LeftChild = rootLeft;
        root.RightChild = rootRight;

        var isNodeAdded = bst.AddKeyValue(125, "123123");
        var fstFind = bst.FindNodeByKey(125);

        Assertions.assertTrue(isNodeAdded);
        Assertions.assertTrue(fstFind.NodeHasKey);
        Assertions.assertEquals(125, rootRight.RightChild.NodeKey);
    }

    @Test
    public void addNodeWhenToLeft() {
        //when
        var root = new BSTNode<>(50, "1234", null);
        var bst = new BST<>(root);

        var rootLeft = new BSTNode<>(4, "1234", root);
        var rootRight = new BSTNode<>(100, "1234", root);

        root.LeftChild = rootLeft;
        root.RightChild = rootRight;

        var isNodeAdded = bst.AddKeyValue(75, "123123");
        var fstFind = bst.FindNodeByKey(75);

        Assertions.assertTrue(isNodeAdded);
        Assertions.assertTrue(fstFind.NodeHasKey);
        Assertions.assertEquals(75, rootRight.LeftChild.NodeKey);
    }

    @Test
    public void addNodeWhenExists() {
        //when
        var root = new BSTNode<>(50, "1234", null);
        var bst = new BST<>(root);

        var rootLeft = new BSTNode<>(4, "1234", root);
        var rootRight = new BSTNode<>(100, "1234", root);

        root.LeftChild = rootLeft;
        root.RightChild = rootRight;

        var isNodeAdded = bst.AddKeyValue(4, "123123");

        Assertions.assertFalse(isNodeAdded);
        Assertions.assertEquals(bst.FindNodeByKey(4).Node.NodeValue, rootLeft.NodeValue);
    }

    @Test
    public void findMinFromRoot() {
        //when
        var root = new BSTNode<>(50, "1234", null);
        var bst = new BST<>(root);

        var rootLeft = new BSTNode<>(4, "1234", root);
        var rootRight = new BSTNode<>(100, "1234", root);
        var rootLeftLeft = new BSTNode<>(3, "123", rootLeft);

        root.LeftChild = rootLeft;
        root.RightChild = rootRight;
        rootLeft.LeftChild = rootLeftLeft;

        var found = bst.FinMinMax(root, false);

        Assertions.assertEquals(found, rootLeftLeft);
    }

    @Test
    public void findMaxFromRoot() {
        //when
        var root = new BSTNode<>(50, "1234", null);
        var bst = new BST<>(root);

        var rootLeft = new BSTNode<>(4, "1234", root);
        var rootRight = new BSTNode<>(100, "1234", root);
        var rootRightRight = new BSTNode<>(150, "123", rootRight);

        root.LeftChild = rootLeft;
        root.RightChild = rootRight;
        rootRight.RightChild = rootRightRight;

        var found = bst.FinMinMax(root, true);

        Assertions.assertEquals(found, rootRightRight);
    }

    @Test
    public void findMinFromSubRoot() {
        //when
        var root = new BSTNode<>(50, "1234", null);
        var bst = new BST<>(root);

        var rootLeft = new BSTNode<>(4, "1234", root);
        var rootRight = new BSTNode<>(100, "1234", root);
        var rootLeftLeft = new BSTNode<>(3, "123", rootLeft);
        var rootLeftLeftLeft = new BSTNode<>(2, "123", rootLeftLeft);

        root.LeftChild = rootLeft;
        root.RightChild = rootRight;
        rootLeft.LeftChild = rootLeftLeft;
        rootLeftLeft.LeftChild = rootLeftLeftLeft;

        var found = bst.FinMinMax(rootLeft, false);

        Assertions.assertEquals(found, rootLeftLeftLeft);
    }

    @Test
    public void findMaxFromSubRoot() {
        //when
        var root = new BSTNode<>(50, "1234", null);
        var bst = new BST<>(root);

        var rootLeft = new BSTNode<>(4, "1234", root);
        var rootRight = new BSTNode<>(100, "1234", root);
        var rootRightRight = new BSTNode<>(150, "123", rootRight);
        var rootRightRightRight = new BSTNode<>(160, "123", rootRightRight);

        root.LeftChild = rootLeft;
        root.RightChild = rootRight;
        rootRight.RightChild = rootRightRight;
        rootRightRight.RightChild = rootRightRightRight;

        var found = bst.FinMinMax(rootRight, true);

        Assertions.assertEquals(found, rootRightRightRight);
    }

    @Test
    public void deleteByKeyWhenEmpty() {
        //when
        var bst = new BST<>(null);

        var isDelete = bst.DeleteNodeByKey(123123);

        Assertions.assertFalse(isDelete);
    }

    @Test
    public void deleteByKeyWhenNotExists() {
        //when
        var root = new BSTNode<>(50, "1234", null);
        var bst = new BST<>(root);

        var rootLeft = new BSTNode<>(4, "1234", root);
        var rootRight = new BSTNode<>(100, "1234", root);
        var rootRightRight = new BSTNode<>(150, "123", rootRight);
        var rootRightRightRight = new BSTNode<>(160, "123", rootRightRight);

        root.LeftChild = rootLeft;
        root.RightChild = rootRight;
        rootRight.RightChild = rootRightRight;
        rootRightRight.RightChild = rootRightRightRight;

        var isDelete = bst.DeleteNodeByKey(22222);

        Assertions.assertFalse(isDelete);
        Assertions.assertTrue(bst.FindNodeByKey(50).NodeHasKey);
        Assertions.assertTrue(bst.FindNodeByKey(4).NodeHasKey);
        Assertions.assertTrue(bst.FindNodeByKey(100).NodeHasKey);
        Assertions.assertTrue(bst.FindNodeByKey(150).NodeHasKey);
        Assertions.assertTrue(bst.FindNodeByKey(160).NodeHasKey);
    }

    @Test
    public void deleteByKeyWhenExists() {
        //when
        var root = new BSTNode<>(50, "1234", null);
        var bst = new BST<>(root);

        var rootLeft = new BSTNode<>(4, "1234", root);
        var rootRight = new BSTNode<>(100, "1234", root);
        var rootRightRight = new BSTNode<>(150, "123", rootRight);
        var rootRightRightRight = new BSTNode<>(160, "123", rootRightRight);

        root.LeftChild = rootLeft;
        root.RightChild = rootRight;
        rootRight.RightChild = rootRightRight;
        rootRightRight.RightChild = rootRightRightRight;

        var isDelete = bst.DeleteNodeByKey(150);

        Assertions.assertTrue(isDelete);
        Assertions.assertTrue(bst.FindNodeByKey(50).NodeHasKey);
        Assertions.assertTrue(bst.FindNodeByKey(4).NodeHasKey);
        Assertions.assertTrue(bst.FindNodeByKey(100).NodeHasKey);
        Assertions.assertFalse(bst.FindNodeByKey(150).NodeHasKey);
        Assertions.assertTrue(bst.FindNodeByKey(160).NodeHasKey);
    }

    @Test
    public void deleteByKeyWhenRootWithRightSubTree() {
        //when
        var root = new BSTNode<>(50, "1234", null);
        var bst = new BST<>(root);

        var rootLeft = new BSTNode<>(4, "1234", root);
        var rootRight = new BSTNode<>(100, "1234", root);
        var rootRightRight = new BSTNode<>(150, "123", rootRight);
        var rootRightLeft = new BSTNode<>(75, "333", rootRight);
        var rootRightRightRight = new BSTNode<>(160, "123", rootRightRight);

        root.LeftChild = rootLeft;
        root.RightChild = rootRight;
        rootRight.RightChild = rootRightRight;
        rootRight.LeftChild = rootRightLeft;
        rootRightRight.RightChild = rootRightRightRight;

        var isDelete = bst.DeleteNodeByKey(50);

        Assertions.assertTrue(isDelete);
        Assertions.assertFalse(bst.FindNodeByKey(50).NodeHasKey);
        Assertions.assertTrue(bst.FindNodeByKey(4).NodeHasKey);
        Assertions.assertTrue(bst.FindNodeByKey(100).NodeHasKey);
        Assertions.assertTrue(bst.FindNodeByKey(75).NodeHasKey);
        Assertions.assertTrue(bst.FindNodeByKey(150).NodeHasKey);
        Assertions.assertTrue(bst.FindNodeByKey(160).NodeHasKey);
    }

    @Test
    public void deleteByKeyWhenRootWithRightSubTreeMinHasRightSubtree() {
        //when
        var root = new BSTNode<>(50, "1234", null);
        var bst = new BST<>(root);

        var rootLeft = new BSTNode<>(4, "1234", root);
        var rootRight = new BSTNode<>(100, "1234", root);
        var rootRightRight = new BSTNode<>(150, "123", rootRight);
        var rootRightLeft = new BSTNode<>(75, "333", rootRight);
        var rootRightLeftRight = new BSTNode<>(80, "11", rootRightLeft);
        var rootRightRightRight = new BSTNode<>(160, "123", rootRightRight);

        root.LeftChild = rootLeft;
        root.RightChild = rootRight;
        rootRight.RightChild = rootRightRight;
        rootRight.LeftChild = rootRightLeft;
        rootRightLeft.RightChild = rootRightLeftRight;
        rootRightRight.RightChild = rootRightRightRight;

        var isDelete = bst.DeleteNodeByKey(50);

        Assertions.assertTrue(isDelete);
        Assertions.assertFalse(bst.FindNodeByKey(50).NodeHasKey);
        Assertions.assertTrue(bst.FindNodeByKey(4).NodeHasKey);
        Assertions.assertTrue(bst.FindNodeByKey(100).NodeHasKey);
        Assertions.assertTrue(bst.FindNodeByKey(75).NodeHasKey);
        Assertions.assertTrue(bst.FindNodeByKey(150).NodeHasKey);
        Assertions.assertTrue(bst.FindNodeByKey(160).NodeHasKey);
        Assertions.assertTrue(bst.FindNodeByKey(80).NodeHasKey);
    }

    @Test
    public void deleteByKeyWhenRootWithLeftChild() {
        //when
        var root = new BSTNode<>(50, "1234", null);
        var bst = new BST<>(root);

        var rootLeft = new BSTNode<>(4, "1234", root);

        root.LeftChild = rootLeft;

        var isDelete = bst.DeleteNodeByKey(50);

        Assertions.assertTrue(isDelete);
        Assertions.assertFalse(bst.FindNodeByKey(50).NodeHasKey);
        Assertions.assertTrue(bst.FindNodeByKey(4).NodeHasKey);
        Assertions.assertEquals(bst.Root, rootLeft);
    }

    @Test
    public void deleteByKeyWhenRootWithRightChild() {
        //when
        var root = new BSTNode<>(50, "1234", null);
        var bst = new BST<>(root);

        var rootRight = new BSTNode<>(4, "1234", root);

        root.RightChild = rootRight;

        var isDelete = bst.DeleteNodeByKey(50);

        Assertions.assertTrue(isDelete);
        Assertions.assertFalse(bst.FindNodeByKey(50).NodeHasKey);
        Assertions.assertTrue(bst.FindNodeByKey(4).NodeHasKey);
        Assertions.assertEquals(bst.Root, rootRight);
    }

    @Test
    public void deleteByKeyWhenRootWithLeftSubtree() {
        //when
        var root = new BSTNode<>(50, "1234", null);
        var bst = new BST<>(root);

        var rootLeft = new BSTNode<>(4, "1234", root);
        var rootLeftLeft = new BSTNode<>(3, "1234", rootLeft);
        var rootLeftRight = new BSTNode<>(5, "321", rootLeft);

        rootLeft.LeftChild = rootLeftLeft;
        rootLeft.RightChild = rootLeftRight;
        root.LeftChild = rootLeft;

        var isDelete = bst.DeleteNodeByKey(50);

        Assertions.assertTrue(isDelete);
        Assertions.assertFalse(bst.FindNodeByKey(50).NodeHasKey);
        Assertions.assertTrue(bst.FindNodeByKey(4).NodeHasKey);
        Assertions.assertTrue(bst.FindNodeByKey(5).NodeHasKey);
        Assertions.assertEquals(bst.Root, rootLeft);
    }

    @Test
    public void deleteByKeyWhenRootWithNullChildren() {
        //when
        var root = new BSTNode<>(50, "1234", null);
        var bst = new BST<>(root);

        var isDelete = bst.DeleteNodeByKey(50);

        Assertions.assertTrue(isDelete);
        Assertions.assertNull(bst.Root);
        Assertions.assertFalse(bst.FindNodeByKey(50).NodeHasKey);
    }

    @Test
    public void wideAllNodes() {
        //when
        var root = new BSTNode<>(50, "1234", null);
        var bst = new BST<>(root);

        var rootLeft = new BSTNode<>(4, "1234", root);
        var rootRight = new BSTNode<>(100, "1234", root);
        var rootRightRight = new BSTNode<>(150, "123", rootRight);
        var rootRightLeft = new BSTNode<>(75, "333", rootRight);
        var rootRightLeftRight = new BSTNode<>(80, "11", rootRightLeft);
        var rootRightRightRight = new BSTNode<>(160, "123", rootRightRight);

        root.LeftChild = rootLeft;
        root.RightChild = rootRight;
        rootRight.RightChild = rootRightRight;
        rootRight.LeftChild = rootRightLeft;
        rootRightLeft.RightChild = rootRightLeftRight;
        rootRightRight.RightChild = rootRightRightRight;

        var nodes = bst.WideAllNodes();

        Assertions.assertEquals(root, nodes.get(0));
        Assertions.assertEquals(rootLeft, nodes.get(1));
        Assertions.assertEquals(rootRight, nodes.get(2));
        Assertions.assertEquals(rootRightLeft, nodes.get(3));
        Assertions.assertEquals(rootRightRight, nodes.get(4));
        Assertions.assertEquals(rootRightLeftRight, nodes.get(5));
        Assertions.assertEquals(rootRightRightRight, nodes.get(6));
    }

    @Test
    public void DeepAllNodesInOrder() {
        //when
        var six = new BSTNode<>(6, "123", null);
        var bst = new BST<>(six);

        var two = new BSTNode<>(2, "1234", six);
        var eight = new BSTNode<>(8, "123", six);
        var one = new BSTNode<>(1, "1234", two);
        var four = new BSTNode<>(4, "333", two);
        var three = new BSTNode<>(3, "123", four);
        var five = new BSTNode<>(5, "11", four);
        var seven = new BSTNode<>(7, "123", eight);
        var nine = new BSTNode<>(9, "1234", eight);

        six.LeftChild = two;
        six.RightChild = eight;
        two.LeftChild = one;
        two.RightChild = four;
        four.LeftChild = three;
        four.RightChild = five;
        eight.LeftChild = seven;
        eight.RightChild = nine;

        var nodes = bst.DeepAllNodes(0);

        Assertions.assertEquals(nodes.get(0), one);
        Assertions.assertEquals(nodes.get(1), two);
        Assertions.assertEquals(nodes.get(2), three);
        Assertions.assertEquals(nodes.get(3), four);
        Assertions.assertEquals(nodes.get(4), five);
        Assertions.assertEquals(nodes.get(5), six);
        Assertions.assertEquals(nodes.get(6), seven);
        Assertions.assertEquals(nodes.get(7), eight);
        Assertions.assertEquals(nodes.get(8), nine);
    }
    @Test
    public void DeepAllNodesPostOrder() {
        //when

        var nine = new BSTNode<>(9, "1234", null);
        var bst = new BST<>(nine);
        var five = new BSTNode<>(5, "11", nine);
        var eight = new BSTNode<>(8, "123", nine);
        var one = new BSTNode<>(1, "1234", five);
        var four = new BSTNode<>(4, "333", five);
        var two = new BSTNode<>(2, "1234", four);
        var three = new BSTNode<>(3, "123", four);
        var six = new BSTNode<>(6, "123", eight);
        var seven = new BSTNode<>(7, "123", eight);

        nine.LeftChild = five;
        nine.RightChild = eight;
        five.LeftChild = one;
        five.RightChild = four;
        four.LeftChild = two;
        four.RightChild = three;
        eight.LeftChild = six;
        eight.RightChild = seven;

        var nodes = bst.DeepAllNodes(1);

        Assertions.assertEquals(nodes.get(0), one);
        Assertions.assertEquals(nodes.get(1), two);
        Assertions.assertEquals(nodes.get(2), three);
        Assertions.assertEquals(nodes.get(3), four);
        Assertions.assertEquals(nodes.get(4), five);
        Assertions.assertEquals(nodes.get(5), six);
        Assertions.assertEquals(nodes.get(6), seven);
        Assertions.assertEquals(nodes.get(7), eight);
        Assertions.assertEquals(nodes.get(8), nine);
    }
}

package data_structures.balanced_bst;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class BalancedBSTTest {
    @Test
    public void whenEmpty_shouldCreate() {
        var balancedBST = new BalancedBST();

        balancedBST.GenerateTree(new int[]{});

        Assertions.assertNull(balancedBST.Root);
    }

    @Test
    public void whenArraySizeIsOne_shouldCreate() {
        var balancedBST = new BalancedBST();

        balancedBST.GenerateTree(new int[]{1});

        Assertions.assertEquals(1, balancedBST.Root.NodeKey);
        Assertions.assertEquals(0, balancedBST.Root.Level);
        Assertions.assertNull(balancedBST.Root.LeftChild);
        Assertions.assertNull(balancedBST.Root.RightChild);
    }

    @Test
    public void whenArraySizeIsTwo_shouldCreate() {
        var balancedBST = new BalancedBST();

        balancedBST.GenerateTree(new int[]{1, 2});

        Assertions.assertEquals(2, balancedBST.Root.NodeKey);
        Assertions.assertEquals(0, balancedBST.Root.Level);
        Assertions.assertNotNull(balancedBST.Root.LeftChild);
        Assertions.assertNull(balancedBST.Root.RightChild);
        Assertions.assertEquals(1, balancedBST.Root.LeftChild.NodeKey);
        Assertions.assertEquals(1, balancedBST.Root.LeftChild.Level);
    }

    @Test
    public void whenArraySizeIsThree_shouldCreate() {
        var balancedBST = new BalancedBST();

        balancedBST.GenerateTree(new int[]{1, 2, 3});

        Assertions.assertEquals(2, balancedBST.Root.NodeKey);
        Assertions.assertEquals(0, balancedBST.Root.Level);
        Assertions.assertNull(balancedBST.Root.Parent);
        Assertions.assertNotNull(balancedBST.Root.LeftChild);
        Assertions.assertNotNull(balancedBST.Root.RightChild);
        Assertions.assertEquals(1, balancedBST.Root.LeftChild.NodeKey);
        Assertions.assertEquals(1, balancedBST.Root.LeftChild.Level);
        Assertions.assertEquals(balancedBST.Root, balancedBST.Root.LeftChild.Parent);
        Assertions.assertNull(balancedBST.Root.LeftChild.LeftChild);
        Assertions.assertNull(balancedBST.Root.LeftChild.RightChild);
        Assertions.assertEquals(3, balancedBST.Root.RightChild.NodeKey);
        Assertions.assertEquals(1, balancedBST.Root.RightChild.Level);
        Assertions.assertEquals(balancedBST.Root, balancedBST.Root.RightChild.Parent);
        Assertions.assertNull(balancedBST.Root.RightChild.LeftChild);
        Assertions.assertNull(balancedBST.Root.RightChild.RightChild);
    }

    @Test
    public void whenIsBalancedShouldTrueWhenOneElement() {
        var bst = new BalancedBST();
        bst.GenerateTree(new int[]{1});

        Assertions.assertTrue(bst.IsBalanced(bst.Root));
    }

    @Test
    public void whenIsBalancedShouldTrueWhenTwoElements() {
        var bst = new BalancedBST();
        bst.GenerateTree(new int[]{1, 2});

        Assertions.assertTrue(bst.IsBalanced(bst.Root));
    }

    @Test
    public void whenIsBalancedShouldTrueWhenTreeElements() {
        var bst = new BalancedBST();
        bst.GenerateTree(new int[]{1, 2, 3});

        Assertions.assertTrue(bst.IsBalanced(bst.Root));
    }

    @Test
    public void whenIsBalancedWithNoRootShouldTrueWhenTreeElements() {
        var bst = new BalancedBST();
        bst.GenerateTree(new int[]{1, 2, 3});

        Assertions.assertTrue(bst.IsBalanced(bst.Root.LeftChild));
    }

    @Test
    public void whenIsBalancedShouldFalseWhenTreeElements() {
        var bst = new BalancedBST();
        bst.Root = new BSTNode(3, null);
        bst.Root.Level = 0;
        bst.Root.LeftChild = new BSTNode(2, bst.Root);
        bst.Root.LeftChild.Level = 1;
        bst.Root.LeftChild.LeftChild = new BSTNode(1, bst.Root.LeftChild);
        bst.Root.LeftChild.LeftChild.Level = 2;

        Assertions.assertFalse(bst.IsBalanced(bst.Root));
    }

    @Test
    public void whenIsBalancedShouldFalseWhenManyElements() {
        var bst = new BalancedBST();
        bst.GenerateTree(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16});

        Assertions.assertTrue(bst.IsBalanced(bst.Root.LeftChild));
        Assertions.assertTrue(bst.IsBalanced(bst.Root.RightChild));
        Assertions.assertTrue(bst.IsBalanced(bst.Root));
    }
}

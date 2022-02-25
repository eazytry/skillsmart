package data_structures.array_based_tree;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class aBSTTest {
    @Test
    public void searchWhenEmptyTest() {
        var aBST = new aBST(0);

        var actual = aBST.FindKeyIndex(123);

        Assertions.assertEquals(1, aBST.Tree.length);
        Assertions.assertEquals(0, actual);
    }

    @Test
    public void searchWhenFull() {
        var aBST = new aBST(0);

        aBST.Tree[0] = 1;

        var actual = aBST.FindKeyIndex(123);

        Assertions.assertNull(actual);
    }

    @Test
    public void searchWhenExistsTest() {
        var aBST = new aBST(1);
        aBST.Tree[0] = 50;
        aBST.Tree[1] = 25;
        aBST.Tree[2] = 75;

        var actual = aBST.FindKeyIndex(75);

        Assertions.assertEquals(3, aBST.Tree.length);
        Assertions.assertEquals(2, actual);
    }

    @Test
    public void addWhenEmpty() {
        var aBST = new aBST(1);

        var actual = aBST.AddKey(75);

        Assertions.assertEquals(3, aBST.Tree.length);
        Assertions.assertEquals(0, actual);
    }

    @Test
    public void addWhenFull() {
        var aBST = new aBST(0);

        var added = aBST.AddKey(75);
        var notAdded = aBST.AddKey(85);

        Assertions.assertEquals(1, aBST.Tree.length);
        Assertions.assertEquals(0, added);
        Assertions.assertEquals(-1, notAdded);
    }

    @Test
    public void addWhenNotFull() {
        var aBST = new aBST(2);

        var first = aBST.AddKey(50);
        var firstLeft = aBST.AddKey(25);
        var firstRight = aBST.AddKey(75);
        var firstLeftLeft = aBST.AddKey(10);

        Assertions.assertEquals(7, aBST.Tree.length);
        Assertions.assertEquals(0, first);
        Assertions.assertEquals(1, firstLeft);
        Assertions.assertEquals(2, firstRight);
        Assertions.assertEquals(3, firstLeftLeft);
    }
}

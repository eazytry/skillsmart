package data_structures.tree;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class SimpleTreeTest {
        @Test
        public void getAllWhenFull() {
            //when
            var root = new SimpleTreeNode<>("123", null);
            var second = new SimpleTreeNode<>("321", root);
            var third = new SimpleTreeNode<>("321", root);
            var fourth = new SimpleTreeNode<>("321", second);
            var fifth = new SimpleTreeNode<>("213213", third);
            var stringSimpleTree = new SimpleTree<>(root);
            stringSimpleTree.AddChild(root, second);
            stringSimpleTree.AddChild(root, third);
            stringSimpleTree.AddChild(second, fourth);
            stringSimpleTree.AddChild(third, fifth);

            var nodes = stringSimpleTree.GetAllNodes();

            Assertions.assertTrue(nodes.contains(root));
            Assertions.assertTrue(nodes.contains(second));
            Assertions.assertTrue(nodes.contains(third));
            Assertions.assertTrue(nodes.contains(fourth));
            Assertions.assertTrue(nodes.contains(fifth));
        }

        @Test
        public void deleteNodeWhenExists() {
            //when
            var root = new SimpleTreeNode<>("123", null);
            var second = new SimpleTreeNode<>("321", root);
            var third = new SimpleTreeNode<>("321", root);
            var fourth = new SimpleTreeNode<>("321", second);
            var fifth = new SimpleTreeNode<>("213213", third);
            var stringSimpleTree = new SimpleTree<>(root);
            stringSimpleTree.AddChild(root, second);
            stringSimpleTree.AddChild(root, third);
            stringSimpleTree.AddChild(second, fourth);
            stringSimpleTree.AddChild(third, fifth);

            stringSimpleTree.DeleteNode(third);
            var actual = stringSimpleTree.GetAllNodes();

            Assertions.assertTrue(actual.contains(root));
            Assertions.assertTrue(actual.contains(second));
            Assertions.assertTrue(actual.contains(fourth));
            Assertions.assertFalse(actual.contains(third));
            Assertions.assertFalse(actual.contains(fifth));
        }

        @Test
        public void deleteNodeWhenNotExists() {
            //when
            var root = new SimpleTreeNode<>("123", null);
            var second = new SimpleTreeNode<>("321", root);
            var third = new SimpleTreeNode<>("321", root);
            var fourth = new SimpleTreeNode<>("321", second);
            var fifth = new SimpleTreeNode<>("213213", third);
            var sixth = new SimpleTreeNode<>("213213", null);

            var stringSimpleTree = new SimpleTree<>(root);
            stringSimpleTree.AddChild(root, second);
            stringSimpleTree.AddChild(root, third);
            stringSimpleTree.AddChild(second, fourth);
            stringSimpleTree.AddChild(third, fifth);

            stringSimpleTree.DeleteNode(sixth);
            var actual = stringSimpleTree.GetAllNodes();

            Assertions.assertTrue(actual.contains(root));
            Assertions.assertTrue(actual.contains(second));
            Assertions.assertTrue(actual.contains(third));
            Assertions.assertTrue(actual.contains(fourth));
            Assertions.assertTrue(actual.contains(fifth));
        }

        @Test
        public void deleteNodeWhenRoot() {
            //when
            var root = new SimpleTreeNode<>("123", null);
            var second = new SimpleTreeNode<>("321", root);
            var third = new SimpleTreeNode<>("321", root);
            var fourth = new SimpleTreeNode<>("321", second);
            var fifth = new SimpleTreeNode<>("213213", third);

            var stringSimpleTree = new SimpleTree<>(root);
            stringSimpleTree.AddChild(root, second);
            stringSimpleTree.AddChild(root, third);
            stringSimpleTree.AddChild(second, fourth);
            stringSimpleTree.AddChild(third, fifth);

            stringSimpleTree.DeleteNode(root);
            var actual = stringSimpleTree.GetAllNodes();

            Assertions.assertTrue(actual.contains(root));
            Assertions.assertTrue(actual.contains(second));
            Assertions.assertTrue(actual.contains(third));
            Assertions.assertTrue(actual.contains(fourth));
            Assertions.assertTrue(actual.contains(fifth));
        }

        @Test
        public void getAllNodesAfterMoveNode() {
            //when
            var root = new SimpleTreeNode<>("123", null);
            var second = new SimpleTreeNode<>("321", root);
            var third = new SimpleTreeNode<>("321", root);
            var fourth = new SimpleTreeNode<>("321", second);
            var fifth = new SimpleTreeNode<>("213213", third);

            var stringSimpleTree = new SimpleTree<>(root);
            stringSimpleTree.AddChild(root, second);
            stringSimpleTree.AddChild(root, third);
            stringSimpleTree.AddChild(second, fourth);
            stringSimpleTree.AddChild(third, fifth);

            stringSimpleTree.MoveNode(fifth, root);
            var actual = stringSimpleTree.GetAllNodes();

            Assertions.assertTrue(actual.contains(root));
            Assertions.assertTrue(actual.contains(second));
            Assertions.assertTrue(actual.contains(third));
            Assertions.assertTrue(actual.contains(fourth));
            Assertions.assertTrue(actual.contains(fifth));
            Assertions.assertFalse(third.Children.contains(fifth));
            Assertions.assertNotEquals(fifth.Parent, third);
            Assertions.assertTrue(root.Children.contains(fifth));
            Assertions.assertEquals(fifth.Parent, root);
        }

        @Test
        public void getAllWhenEmpty() {
            //when
            var root = new SimpleTreeNode<>("123", null);
            var stringSimpleTree = new SimpleTree<>(root);

            var nodes = stringSimpleTree.GetAllNodes();

            Assertions.assertEquals(1, nodes.size());
            Assertions.assertTrue(nodes.contains(root));
        }

        @Test
        public void findByValueWhenFull() {
            //when
            var root = new SimpleTreeNode<>("123", null);
            var second = new SimpleTreeNode<>("321", null);
            var third = new SimpleTreeNode<>("321", null);
            var fourth = new SimpleTreeNode<>("321", null);
            var fifth = new SimpleTreeNode<>("213213", null);
            var stringSimpleTree = new SimpleTree<>(root);
            stringSimpleTree.AddChild(root, second);
            stringSimpleTree.AddChild(root, third);
            stringSimpleTree.AddChild(second, fourth);
            stringSimpleTree.AddChild(third, fifth);

            var nodes = stringSimpleTree.FindNodesByValue("321");

            Assertions.assertTrue(nodes.contains(second));
            Assertions.assertTrue(nodes.contains(third));
            Assertions.assertTrue(nodes.contains(fourth));
        }

        @Test
        public void findByValueWhenLeftFull() {
            //when
            var root = new SimpleTreeNode<>("123", null);
            var second = new SimpleTreeNode<>("321", null);
            var third = new SimpleTreeNode<>("321", null);
            var fourth = new SimpleTreeNode<>("321", null);
            var fifth = new SimpleTreeNode<>("213213", null);
            var stringSimpleTree = new SimpleTree<>(root);
            stringSimpleTree.AddChild(root, second);
            stringSimpleTree.AddChild(second, third);
            stringSimpleTree.AddChild(third, fourth);
            stringSimpleTree.AddChild(fourth, fifth);

            var nodes = stringSimpleTree.GetAllNodes();

            Assertions.assertTrue(nodes.contains(root));
            Assertions.assertTrue(nodes.contains(second));
            Assertions.assertTrue(nodes.contains(third));
            Assertions.assertTrue(nodes.contains(fourth));
            Assertions.assertTrue(nodes.contains(fifth));
        }

        @Test
        public void findLeafsWhenFull() {
            //when
            var root = new SimpleTreeNode<>("123", null);
            var second = new SimpleTreeNode<>("321", null);
            var third = new SimpleTreeNode<>("321", null);
            var fourth = new SimpleTreeNode<>("321", null);
            var fifth = new SimpleTreeNode<>("213213", null);
            var stringSimpleTree = new SimpleTree<>(root);
            stringSimpleTree.AddChild(root, second);
            stringSimpleTree.AddChild(root, third);
            stringSimpleTree.AddChild(second, fourth);
            stringSimpleTree.AddChild(third, fifth);

            var count = stringSimpleTree.LeafCount();

            Assertions.assertEquals(2, count);
        }

        @Test
        public void whenEvenTreeShouldReturnEmpty() {
            //when
            var root = new SimpleTreeNode<>("1", null);
            var second = new SimpleTreeNode<>("2", null);
            var third = new SimpleTreeNode<>("3", null);

            var stringSimpleTree = new SimpleTree<>(root);
            stringSimpleTree.AddChild(root, second);
            stringSimpleTree.AddChild(root, third);

            var list = stringSimpleTree.EvenTrees();

            Assertions.assertTrue(list.isEmpty());
        }

        @Test
        public void whenEvenTreeShouldReturnOneEdge() {
            //when
            var root = new SimpleTreeNode<>("1", null);
            var second = new SimpleTreeNode<>("2", null);
            var third = new SimpleTreeNode<>("3", null);
            var fourth = new SimpleTreeNode<>("4", null);

            var stringSimpleTree = new SimpleTree<>(root);
            stringSimpleTree.AddChild(root, second);
            stringSimpleTree.AddChild(root, third);
            stringSimpleTree.AddChild(third, fourth);

            var list = stringSimpleTree.EvenTrees();

            Assertions.assertFalse(list.isEmpty());
            Assertions.assertEquals("1", list.get(0));
            Assertions.assertEquals("3", list.get(1));
        }

        @Test
        public void whenEvenTreeShouldReturnOneEdge_2() {
            //when
            var root = new SimpleTreeNode<>("1", null);
            var second = new SimpleTreeNode<>("2", null);
            var third = new SimpleTreeNode<>("3", null);
            var fourth = new SimpleTreeNode<>("4", null);
            var fifth = new SimpleTreeNode<>("5", null);

            var stringSimpleTree = new SimpleTree<>(root);
            stringSimpleTree.AddChild(root, second);
            stringSimpleTree.AddChild(root, third);
            stringSimpleTree.AddChild(third, fourth);
            stringSimpleTree.AddChild(second, fifth);

            var list = stringSimpleTree.EvenTrees();

            Assertions.assertFalse(list.isEmpty());
            Assertions.assertEquals(2, list.size());
            Assertions.assertEquals("1", list.get(0));
            Assertions.assertEquals("2", list.get(1));
        }

        @Test
        public void whenEvenTreeShouldReturnTwoEdges() {
            //when
            var root = new SimpleTreeNode<>("1", null);
            var rFirst = new SimpleTreeNode<>("2", null);
            var rSecond = new SimpleTreeNode<>("3", null);
            var rThird = new SimpleTreeNode<>("4", null);
            var rfFirst = new SimpleTreeNode<>("5", null);
            var rsFirst = new SimpleTreeNode<>("6", null);

            var stringSimpleTree = new SimpleTree<>(root);
            stringSimpleTree.AddChild(root, rFirst);
            stringSimpleTree.AddChild(root, rSecond);
            stringSimpleTree.AddChild(root, rThird);
            stringSimpleTree.AddChild(rFirst, rfFirst);
            stringSimpleTree.AddChild(rSecond, rsFirst);

            var list = stringSimpleTree.EvenTrees();

            Assertions.assertFalse(list.isEmpty());
            Assertions.assertEquals(4, list.size());
            Assertions.assertEquals("1", list.get(0));
            Assertions.assertEquals("2", list.get(1));
            Assertions.assertEquals("1", list.get(2));
            Assertions.assertEquals("3", list.get(3));
        }
        @Test
        public void whenEvenTreeShouldReturnTwoEdges_2() {
            //when
            var root = new SimpleTreeNode<>("1", null);
            var rFirst = new SimpleTreeNode<>("2", null);
            var rSecond = new SimpleTreeNode<>("3", null);
            var rThird = new SimpleTreeNode<>("4", null);
            var rfFirst = new SimpleTreeNode<>("5", null);
            var rfSecond = new SimpleTreeNode<>("6", null);
            var rsFirst = new SimpleTreeNode<>("7", null);
            var rtFirst = new SimpleTreeNode<>("8", null);
            var rtfFirst = new SimpleTreeNode<>("9", null);
            var rtfSecond = new SimpleTreeNode<>("10", null);

            var stringSimpleTree = new SimpleTree<>(root);
            stringSimpleTree.AddChild(root, rFirst);
            stringSimpleTree.AddChild(root, rSecond);
            stringSimpleTree.AddChild(root, rThird);
            stringSimpleTree.AddChild(rFirst, rfFirst);
            stringSimpleTree.AddChild(rFirst, rfSecond);
            stringSimpleTree.AddChild(rSecond, rsFirst);
            stringSimpleTree.AddChild(rThird, rtFirst);
            stringSimpleTree.AddChild(rtFirst, rtfFirst);
            stringSimpleTree.AddChild(rtFirst, rtfSecond);

            var list = stringSimpleTree.EvenTrees();

            Assertions.assertFalse(list.isEmpty());
            Assertions.assertEquals(4, list.size());
            Assertions.assertEquals("1", list.get(0));
            Assertions.assertEquals("3", list.get(1));
            Assertions.assertEquals("1", list.get(2));
            Assertions.assertEquals("4", list.get(3));
        }
}

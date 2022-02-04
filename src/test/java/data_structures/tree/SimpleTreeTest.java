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
            var second = new SimpleTreeNode<>("321", null);
            var third = new SimpleTreeNode<>("321", null);
            var fourth = new SimpleTreeNode<>("321", null);
            var fifth = new SimpleTreeNode<>("213213", null);
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
}

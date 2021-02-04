import dataStructures.linkedList2.LinkedList2;
import org.junit.jupiter.api.Test;
import dataStructures.linkedList2.LinkedList2.Node;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LinkedList2Test {

    @Test
    void addInTail() {
        LinkedList2 ll = new LinkedList2();
        Node tailNode = new Node(3);
        ll.addInTail(new Node(1));
        ll.addInTail(tailNode);

        assertThat(ll.tail).isEqualTo(tailNode);
    }

    @Test
    void find() {
        LinkedList2 ll = new LinkedList2();
        Node searchingNode = new Node(2);
        ll.addInTail(new Node(1));
        ll.addInTail(searchingNode);
        ll.addInTail(new Node(3));

        Node node = ll.find(2);
        assertThat(searchingNode).isEqualTo(node);
    }

    @Test
    void findAll() {
        LinkedList2 ll = new LinkedList2();
        List<Node> expectedList = new ArrayList<Node>() {{
            add(new Node(2));
            add(new Node(2));
        }};
        expectedList.forEach(ll::addInTail);

        Node node = ll.head;
        while (node != null) {
            assertThat(expectedList).contains(node);
            node = node.next;
        }
    }

    @Test
    void remove() {
        LinkedList2 ll = new LinkedList2();
        ll.addInTail(new Node(1));
        ll.addInTail(new Node(2));
        ll.addInTail(new Node(13));

        assertThat(ll.remove(111)).isFalse();
        assertThat(ll.remove(1)).isTrue();
        assertThat(ll.remove(13)).isTrue();
        assertThat(ll.remove(2)).isTrue();
        assertThat(ll.remove(1)).isFalse();
    }

    @Test
    void removeAll() {
        LinkedList2 ll = new LinkedList2();
        ll.addInTail(new Node(1));
        ll.addInTail(new Node(2));
        ll.addInTail(new Node(2));

        ll.removeAll(2);

        assertThat(ll.count()).isEqualTo(1);
        assertThat(ll.find(2)).isNull();
    }

    @Test
    void clear() {
        LinkedList2 ll = new LinkedList2();

        ll.addInTail(new Node(1));
        ll.addInTail(new Node(2));
        ll.addInTail(new Node(2));

        ll.clear();

        assertThat(ll.count()).isEqualTo(0);
    }

    @Test
    void count() {
        LinkedList2 ll = new LinkedList2();

        ll.addInTail(new Node(1));
        ll.addInTail(new Node(2));
        ll.addInTail(new Node(2));

        assertThat(ll.count()).isEqualTo(3);
    }

    @Test
    void insertAfter() {
        LinkedList2 ll = new LinkedList2();
        Node node1 = new Node(0);
        Node nodeAfter = new Node(1);
        Node nodeToInsert = new Node(7);
        ll.addInTail(nodeAfter);
        ll.addInTail(new Node(2));
        ll.addInTail(new Node(2));
        ll.insertAfter(nodeAfter, nodeToInsert);
        ll.insertAfter(null, node1);

        assertThat(ll.head).isEqualTo(node1);
        assertThat(ll.head.next.next).isEqualTo(nodeToInsert);
        assertThat(ll.count()).isEqualTo(5);
    }
}

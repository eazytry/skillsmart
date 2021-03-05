import dataStructures.orderedList.OrderedList;
import dataStructures.orderedList.OrderedList.Node;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

public class OrderedListTest {
    @Test
    public void add_When_oneObject() {
        OrderedList<Integer> orderedList = new OrderedList<>(true);
        Integer firstInt = 4;

        orderedList.add(firstInt);

        Assertions.assertThat(orderedList.head.value).isEqualTo(firstInt);
        Assertions.assertThat(orderedList.head.next).isNull();
        Assertions.assertThat(orderedList.head.prev).isNull();
        Assertions.assertThat(orderedList.count()).isEqualTo(1);
    }

    @Test
    public void add_When_twoObjects() {
        OrderedList<Integer> orderedList = new OrderedList<>(true);
        Integer firstInt = 4;
        Integer secondObject = 5;

        orderedList.add(firstInt);
        orderedList.add(secondObject);

        Assertions.assertThat(orderedList.head.value).isEqualTo(firstInt);
        Assertions.assertThat(orderedList.tail.value).isEqualTo(secondObject);
        Assertions.assertThat(orderedList.head.next.value).isEqualTo(secondObject);
        Assertions.assertThat(orderedList.tail.prev.value).isEqualTo(firstInt);
        Assertions.assertThat(orderedList.tail.next).isNull();
        Assertions.assertThat(orderedList.head.prev).isNull();
        Assertions.assertThat(orderedList.count()).isEqualTo(2);
    }

    @Test
    public void add_When_twoObjectsAreSame() {
        OrderedList<Integer> orderedList = new OrderedList<>(true);
        Integer firstInt = new Integer(4);
        Integer secondObject = new Integer(4);

        orderedList.add(firstInt);
        orderedList.add(secondObject);

        Assertions.assertThat(orderedList.head.value).isEqualTo(firstInt);
        Assertions.assertThat(orderedList.tail.value).isEqualTo(secondObject);
        Assertions.assertThat(orderedList.head.next.value).isEqualTo(secondObject);
        Assertions.assertThat(orderedList.tail.prev.value).isEqualTo(firstInt);
        Assertions.assertThat(orderedList.tail.next).isNull();
        Assertions.assertThat(orderedList.head.prev).isNull();
        Assertions.assertThat(orderedList.count()).isEqualTo(2);
    }
    @Test
    public void add_When_someObjectsAreSame() {
        OrderedList<Integer> orderedList = new OrderedList<>(true);
        Integer firstInt = new Integer(4);
        Integer secondInt = new Integer(4);
        Integer thirdInt = new Integer(1);

        orderedList.add(firstInt);
        orderedList.add(secondInt);
        orderedList.add(thirdInt);

        Assertions.assertThat(orderedList.head.value).isEqualTo(thirdInt);
        Assertions.assertThat(orderedList.tail.value).isEqualTo(secondInt);
        Assertions.assertThat(orderedList.head.next.value).isEqualTo(firstInt);
        Assertions.assertThat(orderedList.head.next.next.value).isEqualTo(secondInt);
        Assertions.assertThat(orderedList.tail.prev.prev.value).isEqualTo(thirdInt);
        Assertions.assertThat(orderedList.tail.next).isNull();
        Assertions.assertThat(orderedList.head.prev).isNull();
        Assertions.assertThat(orderedList.count()).isEqualTo(3);
    }

    @Test
    public void delete_When_OneObject() {
        OrderedList<Integer> orderedList = new OrderedList<>(true);
        Integer firstInt = 4;

        // when
        orderedList.add(firstInt);
        orderedList.delete(firstInt);

        //than
        Assertions.assertThat(orderedList.head).isNull();
        Assertions.assertThat(orderedList.tail).isNull();
        Assertions.assertThat(orderedList.count()).isEqualTo(0);
    }
    @Test
    public void delete_When_TwoObjects() {
        OrderedList<Integer> orderedList = new OrderedList<>(true);
        Integer firstInt = 4;
        Integer secondInt = 1;

        // when
        orderedList.add(firstInt);
        orderedList.add(secondInt);
        orderedList.delete(firstInt);

        //than
        Assertions.assertThat(orderedList.head.value).isEqualTo(secondInt);
        Assertions.assertThat(orderedList.tail.value).isEqualTo(secondInt);
        Assertions.assertThat(orderedList.head.prev).isNull();
        Assertions.assertThat(orderedList.head.next).isNull();
        Assertions.assertThat(orderedList.tail.next).isNull();
        Assertions.assertThat(orderedList.tail.prev).isNull();
        Assertions.assertThat(orderedList.count()).isEqualTo(1);
    }

    @Test
    public void find_When_ListEmpty_ThanNull() {
        OrderedList<Integer> orderedList = new OrderedList<>(true);

        // when
        Node<Integer> actualNode = orderedList.find(new Integer("5"));

        //than
        Assertions.assertThat(actualNode).isNull();
    }
    @Test
    public void find_When_ListFilled() {
        OrderedList<Integer> orderedList = new OrderedList<>(true);
        Integer firstInt = 4;
        Integer secondInt = 1;

        // when
        orderedList.add(firstInt);
        orderedList.add(secondInt);
        Node<Integer> actualNode = orderedList.find(secondInt);

        //than
        Assertions.assertThat(actualNode).isNotNull();
        Assertions.assertThat(actualNode.value).isEqualTo(secondInt);
    }

    @Test
    public void getAll_When_ascIsTrue() {
        // given
        OrderedList<Integer> orderedList = new OrderedList<>(true);
        Integer firstInt = 4;
        Integer secondInt = 1;
        Integer thirdInt = 9;
        orderedList.add(firstInt);
        orderedList.add(secondInt);
        orderedList.add(thirdInt);

        //when
        List<Node<Integer>> list = orderedList.getAll();

        //than
        Assertions.assertThat(orderedList.head.value).isEqualTo(secondInt);
        Assertions.assertThat(orderedList.tail.value).isEqualTo(thirdInt);
        Assertions.assertThat(orderedList.head.next.value).isEqualTo(firstInt);
        Assertions.assertThat(orderedList.head.next.next.value).isEqualTo(thirdInt);
        Assertions.assertThat(orderedList.tail.prev.prev.value).isEqualTo(secondInt);
        Assertions.assertThat(orderedList.count()).isEqualTo(3);
    }

    @Test
    public void getAll_When_ascIsFalse() {
        // given
        OrderedList<Integer> orderedList = new OrderedList<>(false);
        Integer firstInt = 4;
        Integer secondInt = 1;
        Integer thirdInt = 9;
        orderedList.add(firstInt);
        orderedList.add(secondInt);
        orderedList.add(thirdInt);

        //when
        List<Node<Integer>> list = orderedList.getAll();

        //than
        Assertions.assertThat(orderedList.head.value).isEqualTo(thirdInt);
        Assertions.assertThat(orderedList.tail.value).isEqualTo(secondInt);
        Assertions.assertThat(orderedList.head.next.value).isEqualTo(firstInt);
        Assertions.assertThat(orderedList.head.next.next.value).isEqualTo(secondInt);
        Assertions.assertThat(orderedList.tail.prev.prev.value).isEqualTo(thirdInt);
        Assertions.assertThat(orderedList.count()).isEqualTo(3);
    }

}

import data_structures.queue.Queue;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class QueueTest {
    @Test
    public void enqueue_queueIsEmpty() {
        Queue<String> queue = new Queue<>();

        queue.enqueue("123");
    }

    @Test
    public void enqueue_queueIsNotEmpty() {
        Queue<String> queue = new Queue<>();

        queue.enqueue("123");
        queue.enqueue("321");
    }

    @Test
    public void dequeue_queueIsEmpty_returnNull() {
        Queue<String> queue = new Queue<>();

        String actualValue = queue.dequeue();

        Assertions.assertEquals(null, actualValue);
    }

    @Test
    public void dequeue_queueIsNotEmpty() {
        Queue<String> queue = new Queue<>();

        String expectedValue = "123";
        String actualValue;

        queue.enqueue(expectedValue);
        actualValue = queue.dequeue();

        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void size_queueIsEmpty() {
        Queue<String> queue = new Queue<>();

        Assertions.assertEquals(0, queue.size());
    }

    @Test
    public void size_queueIsNotEmpty() {
        Queue<String> queue = new Queue<>();

        queue.enqueue("123");

        Assertions.assertEquals(1, queue.size());
    }

    @Test
    public void Given_FilledQueue_When_Dequeuing_Than_OrderIsCorrect() {
        Queue<String> queue = new Queue<>();

        String firstExpectedValue = "1";
        String secondExpectedValue = "2";
        String thirdExpectedValue = "3";
        String fourthExpectedValue = "4";

        queue.enqueue(firstExpectedValue);
        queue.enqueue(secondExpectedValue);
        queue.enqueue(thirdExpectedValue);
        queue.enqueue(fourthExpectedValue);

        Assertions.assertEquals(firstExpectedValue, queue.dequeue());
        Assertions.assertEquals(secondExpectedValue, queue.dequeue());
        Assertions.assertEquals(thirdExpectedValue, queue.dequeue());
        Assertions.assertEquals(fourthExpectedValue, queue.dequeue());
        Assertions.assertEquals(0, queue.size());
    }
}

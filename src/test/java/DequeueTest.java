import data_structures.deque.Deque;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class DequeueTest {
    @Test
    public void initDeque() {
        Deque<String> deque = new Deque<>();

        Assertions.assertThat(deque.isEmpty()).isTrue();
    }

    @Test
    public void given_EmptyDeque_when_AddFrontAndRemoveFront_than_RemoveElementIsSameAsAdd() {
        Deque<String> deque = new Deque<>();

        String expectedValue = "123";

        deque.addFront(expectedValue);

        Assertions.assertThat(deque.size()).isEqualTo(1);

        String actualValue = deque.removeFront();

        Assertions.assertThat(actualValue).isEqualTo(expectedValue);
        Assertions.assertThat(deque.isEmpty()).isTrue();
    }

    @Test
    public void given_FilledDeque_when_RemoveFront_than_RemovingOrderIsCorrect() {
        // given
        String firstExpectedValue = "1";
        String secondExpectedValue = "2";
        String thirdExpectedValue = "3";
        Deque<String> deque = new Deque<>();
        deque.addFront(firstExpectedValue);
        deque.addFront(secondExpectedValue);
        deque.addFront(thirdExpectedValue);
        Assertions.assertThat(deque.size()).isEqualTo(3);

        //when
        String firstActualValue = deque.removeFront();
        String secondActualValue = deque.removeFront();
        String thirdActualValue = deque.removeFront();
        Assertions.assertThat(deque.size()).isEqualTo(0);

        //than
        Assertions.assertThat(firstActualValue).isEqualTo(thirdExpectedValue);
        Assertions.assertThat(secondActualValue).isEqualTo(secondExpectedValue);
        Assertions.assertThat(thirdActualValue).isEqualTo(firstExpectedValue);
    }

    @Test
    public void given_EmptyDeque_when_AddTailRemoveTail_than_RemoveValueIsSameAsAdd() {
        //given
        String expectedValue = "123";
        Deque<String> deque = new Deque<>();

        //when
        deque.addTail(expectedValue);
        String actualValue = deque.removeTail();

        //than
        Assertions.assertThat(actualValue).isEqualTo(expectedValue);
        Assertions.assertThat(deque.isEmpty()).isTrue();
    }

    @Test
    public void given_FilledDeque_when_RemoveTail_than_RemovingOrderIsCorrect() {
        // given
        String firstExpectedValue = "1";
        String secondExpectedValue = "2";
        String thirdExpectedValue = "3";
        Deque<String> deque = new Deque<>();
        deque.addTail(firstExpectedValue);
        deque.addTail(secondExpectedValue);
        deque.addTail(thirdExpectedValue);
        Assertions.assertThat(deque.size()).isEqualTo(3);

        //when
        String firstActualValue = deque.removeTail();
        String secondActualValue = deque.removeTail();
        String thirdActualValue = deque.removeTail();
        Assertions.assertThat(deque.size()).isEqualTo(0);

        //than
        Assertions.assertThat(firstActualValue).isEqualTo(thirdExpectedValue);
        Assertions.assertThat(secondActualValue).isEqualTo(secondExpectedValue);
        Assertions.assertThat(thirdActualValue).isEqualTo(firstExpectedValue);
    }

    @Test
    public void given_EmptyDeque_when_AddFrontAndTail_than_RemovingFromFrondAndTailIsCorrect() {
        //given
        Deque<String> deque = new Deque<>();
        String firstExpectedValue = "1";
        String secondExpectedValue = "2";
        String thirdExpectedValue = "3";
        String fourthExpectedValue = "4";

        //when
        deque.addTail(firstExpectedValue);
        deque.addFront(secondExpectedValue);
        deque.addTail(thirdExpectedValue);
        deque.addFront(fourthExpectedValue);

        //than
        String firstActualValue = deque.removeFront();
        String secondActualValue = deque.removeTail();
        String thirdActualValue = deque.removeFront();
        String fourthActualValue = deque.removeTail();
        Assertions.assertThat(firstActualValue).isEqualTo(fourthExpectedValue);
        Assertions.assertThat(secondActualValue).isEqualTo(thirdExpectedValue);
        Assertions.assertThat(thirdActualValue).isEqualTo(secondExpectedValue);
        Assertions.assertThat(fourthActualValue).isEqualTo(firstExpectedValue);
    }
}

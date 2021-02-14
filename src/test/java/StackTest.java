import dataStructures.stack.Stack;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StackTest {
    @Test
    public void init() {
        Stack<String> stringStack = new Stack<>();

        assertEquals(0, stringStack.size());
        assertNull(stringStack.peek());
        assertNull(stringStack.peek());
    }

    @Test
    public void push_cashIsEmpty() {
        Stack<String> stringStack = new Stack<>();
        String expectedString = "123";

        stringStack.push(expectedString);

        assertEquals(1, stringStack.size());
        assertEquals(expectedString, stringStack.head.getT());
        assertEquals(stringStack.head, stringStack.tail);
        assertEquals(stringStack.head.t, stringStack.tail.t);
        assertNull(stringStack.tail.nextNode);
    }

    @Test
    public void push_2Elements() {
        Stack<String> stringStack = new Stack<>();
        String firstString = "123";
        String secondString = "321";

        stringStack.push(firstString);
        stringStack.push(secondString);

        assertEquals(2, stringStack.size());
        assertEquals(secondString, stringStack.head.t);
        assertEquals(firstString, stringStack.tail.t);
        assertEquals(stringStack.head.nextNode, stringStack.tail);
        assertNull(stringStack.tail.nextNode);
    }

    @Test
    public void push_3Elements() {
        Stack<String> stringStack = new Stack<>();
        String firstString = "123";
        String secondString = "321";
        String thirdString = "000";

        stringStack.push(firstString);
        stringStack.push(secondString);
        stringStack.push(thirdString);

        assertEquals(3, stringStack.size());
        assertEquals(thirdString, stringStack.head.t);
        assertEquals(firstString, stringStack.tail.t);
        assertEquals(stringStack.head.nextNode.t, secondString);
        assertNull(stringStack.tail.nextNode);
    }

    @Test
    public void pop_stackIsEmpty() {
        Stack<String> stringStack = new Stack<>();

        String val = stringStack.pop();

        assertEquals(0, stringStack.size());
        assertNull(val);
        assertNull(stringStack.head);
        assertNull(stringStack.tail);
    }

    @Test
    public void pop_countIsOne() {
        Stack<String> stringStack = new Stack<>();

        String expectedVal = "11";
        stringStack.push(expectedVal);
        String actualVal = stringStack.pop();

        assertEquals(0, stringStack.size());
        assertEquals(expectedVal, actualVal);
        assertNull(stringStack.head);
        assertNull(stringStack.tail);
    }

    @Test
    public void pop_countIsTwo() {
        Stack<String> stringStack = new Stack<>();

        String firstVal = "11";
        String secondVal = "12";

        stringStack.push(firstVal);
        stringStack.push(secondVal);

        String actualVal = stringStack.pop();

        assertEquals(1, stringStack.size());
        assertEquals(secondVal, actualVal);
        assertEquals(firstVal, stringStack.head.getT());
        assertEquals(firstVal, stringStack.tail.getT());
    }

    @Test
    public void pop_countIsThree() {
        Stack<String> stringStack = new Stack<>();

        String firstVal = "11";
        String secondVal = "12";
        String thirdVal = "12";

        stringStack.push(firstVal);
        stringStack.push(secondVal);
        stringStack.push(thirdVal);

        String actualVal = stringStack.pop();

        assertEquals(2, stringStack.size());
        assertEquals(thirdVal, actualVal);
        assertEquals(secondVal, stringStack.head.getT());
        assertEquals(firstVal, stringStack.tail.getT());
        assertEquals(stringStack.head.nextNode, stringStack.tail);
        assertNull(stringStack.tail.nextNode);
    }

    @Test
    public void peek_stackIsEmpty() {
        Stack<String> stringStack = new Stack<>();

        String actualVal = stringStack.peek();

        assertNull(actualVal);
    }

    @Test
    public void peek_stackIsNotEmpty() {
        Stack<String> stringStack = new Stack<>();

        String expectedVal = "123";

        stringStack.push("fff");
        stringStack.push("432432");
        stringStack.push(expectedVal);

        String actualVal = "";

        for (int i = 0; i < 10; i++) {
            actualVal = stringStack.peek();
        }

        assertEquals(expectedVal, actualVal);
        assertEquals(3, stringStack.size());
    }
}

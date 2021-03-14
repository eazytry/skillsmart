import data_structures.array_list.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ArrayListTest {
    @Test
    public void testCreateNewArray() {
        ArrayList<Integer> array = new ArrayList<>(Integer.class);

        Assertions.assertEquals(array.array.length, 16);
        Assertions.assertEquals(array.clazz, Integer.class);
        Assertions.assertEquals(array.count, 0);
        Assertions.assertEquals(array.capacity, 16);
    }

    @Test(expected = RuntimeException.class)
    public void append_IndexIsOutOfBound_ThrowRuntimeException() {
        ArrayList<Integer> array = new ArrayList<>(Integer.class);
        array.append(1);
        array.getItem(1);
    }

    @Test
    public void append_ArrayIsEmpty() {
        ArrayList<Integer> array = new ArrayList<>(Integer.class);
        array.append(5);

        Assertions.assertEquals(array.getItem(0), 5);
        Assertions.assertEquals(array.clazz, Integer.class);
        Assertions.assertEquals(array.count, 1);
        Assertions.assertEquals(array.capacity, 16);
    }

    @Test(expected = RuntimeException.class)
    public void getItem_IndexLessThanZero_ThrowRuntimeException() {
        ArrayList<Integer> array = new ArrayList<>(Integer.class);
        array.getItem(-5);
    }

    @Test
    public void append_BufferIsFull() {
        ArrayList<Integer> array = new ArrayList<>(Integer.class);
        for (int i = 0; i < 17; i++) {
            array.append(i);
        }

        Assertions.assertEquals(array.clazz, Integer.class);
        Assertions.assertEquals(array.count, 17);
        Assertions.assertEquals(array.capacity, 32);
    }

    @Test(expected = RuntimeException.class)
    public void insert_IndexLessThanZero_ThrowRuntimeException() {
        ArrayList<Integer> array = new ArrayList<>(Integer.class);
        array.insert(5, -5);
    }

    @Test(expected = RuntimeException.class)
    public void insert_IndexIsOutOfBound_ThrowRuntimeException() {
        ArrayList<Integer> array = new ArrayList<>(Integer.class);

        array.insert(5, 5);
    }

    @Test(expected = RuntimeException.class)
    public void insert_IndexIsOutOfBuffer_ThrowRuntimeException() {
        ArrayList<Integer> array = new ArrayList<>(Integer.class);

        array.insert(5, 100);
    }

    @Test
    public void insert_EmptyArray() {
        ArrayList<Integer> array = new ArrayList<>(Integer.class);

        array.insert(5, 0);

        Assert.assertEquals(array.count, 1);
        Assert.assertEquals(array.capacity, 16);
        Assert.assertEquals(array.clazz, Integer.class);
        Assert.assertEquals(array.array[0], Integer.valueOf(5));
    }

    @Test
    public void insert_NotEmptyArray() {
        ArrayList<Integer> array = new ArrayList<>(Integer.class);

        array.append(2);
        array.insert(1, 1);

        Assert.assertEquals(array.count, 2);
        Assert.assertEquals(array.capacity, 16);
        Assert.assertEquals(array.clazz, Integer.class);
        Assert.assertEquals(array.array[1], Integer.valueOf(1));
    }

    @Test
    public void insert_BufferIsFull() {
        ArrayList<Integer> array = new ArrayList<>(Integer.class);

        for (int i = 0; i < 16; i++) {
            array.append(i);
        }
        array.insert(111, 10);

        Assert.assertEquals(array.count, 17);
        Assert.assertEquals(array.capacity, 32);
        Assert.assertEquals(array.clazz, Integer.class);
        Assert.assertEquals(array.array[10], Integer.valueOf(111));
    }

    @Test(expected = RuntimeException.class)
    public void remove_ArrayIsEmpty() {
        ArrayList<Integer> array = new ArrayList<>(Integer.class);

        array.remove(0);
    }

    @Test(expected = RuntimeException.class)
    public void remove_IndexIsOutOfBound() {
        ArrayList<Integer> array = new ArrayList<>(Integer.class);

        array.remove(0);
    }

    @Test
    public void remove_IndexIsZero() {
        ArrayList<Integer> array = new ArrayList<>(Integer.class);

        array.append(1);
        array.append(2);
        array.remove(0);

        Assertions.assertEquals(1, array.count);
        Assertions.assertEquals(16, array.capacity);
        Assertions.assertEquals(Integer.valueOf(2), array.getItem(0));
    }

    @Test
    public void remove_IndexAroundTheMiddle() {
        ArrayList<Integer> array = new ArrayList<>(Integer.class);

        array.append(1);
        array.append(2);
        array.append(3);
        array.remove(1);

        Assertions.assertEquals(2, array.count);
        Assertions.assertEquals(16, array.capacity);
        Assertions.assertEquals(Integer.valueOf(3), array.getItem(1));
    }

    @Test
    public void remove_BufferIsMoreThanCountOverFiftyPercents() {
        ArrayList<Integer> array = new ArrayList<>(Integer.class);

        for (int i = 0; i < 17; i++) {
            array.append(i);
        }
        array.remove(4);
        array.remove(3);

        Assertions.assertEquals(15, array.count);
        Assertions.assertEquals(21, array.capacity);
        Assertions.assertEquals(Integer.valueOf(6), array.getItem(4));
    }

    @Test
    public void remove_IndexIsEndOfArray() {
        ArrayList<Integer> array = new ArrayList<>(Integer.class);

        for (int i = 0; i < 16; i++) {
            array.append(i);
        }
        array.remove(15);

        Assertions.assertEquals(15, array.count);
        Assertions.assertEquals(16, array.capacity);
    }
}


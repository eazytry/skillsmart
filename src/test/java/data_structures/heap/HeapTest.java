package data_structures.heap;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

public class HeapTest {
    @Test
    public void createHeapWhenOneTest() {
        var heap = new Heap();
        heap.MakeHeap(new int[]{1}, 0);

        Assertions.assertEquals(1, heap.HeapArray.length);
        Assertions.assertEquals(1, heap.HeapArray[0]);
    }

    @Test
    public void createHeapWhenTwoTest() {
        var heap = new Heap();
        heap.MakeHeap(new int[]{1, 2}, 1);

        Assertions.assertEquals(3, heap.HeapArray.length);
        Assertions.assertEquals(2, heap.HeapArray[0]);
        Assertions.assertEquals(1, heap.HeapArray[1]);
    }

    @Test
    public void createHeapWhenThreeTest() {
        var heap = new Heap();
        heap.MakeHeap(new int[]{1, 2, 3}, 1);

        Assertions.assertEquals(3, heap.HeapArray.length);
        Assertions.assertEquals(3, heap.HeapArray[0]);
        Assertions.assertEquals(1, heap.HeapArray[1]);
        Assertions.assertEquals(2, heap.HeapArray[2]);
    }

    @Test
    public void createHeapWhenManyTest() {
        var heap = new Heap();
        heap.MakeHeap(new int[]{10, 2, 4, 5, 11, 40}, 2);

        Assertions.assertEquals(7, heap.HeapArray.length);
        Assertions.assertEquals(40, heap.HeapArray[0]);
        Assertions.assertEquals(10, heap.HeapArray[1]);
        Assertions.assertEquals(11, heap.HeapArray[2]);
        Assertions.assertEquals(2, heap.HeapArray[3]);
        Assertions.assertEquals(5, heap.HeapArray[4]);
        Assertions.assertEquals(4, heap.HeapArray[5]);
    }

    @Test
    public void addKeyTest() {
        var heap = new Heap();
        heap.MakeHeap(new int[]{}, 2);
        heap.Add(1);
        heap.Add(3);
        heap.Add(2);

        Assertions.assertEquals(7, heap.HeapArray.length);
        Assertions.assertEquals(3, heap.HeapArray[0]);
        Assertions.assertEquals(2, heap.HeapArray[1]);
        Assertions.assertEquals(1, heap.HeapArray[2]);
    }

    @Test
    public void addZeroWhenEmptyAndDepthIsZeroTest() {
        var heap = new Heap();
        heap.MakeHeap(new int[]{}, 0);
        heap.Add(0);

        Assertions.assertEquals(1, heap.HeapArray.length);
        Assertions.assertEquals(0, heap.HeapArray[0]);
    }

    @Test
    public void getMaxTest() {
        var heap = new Heap();
        heap.MakeHeap(new int[]{1, 2, 3, 4, 5, 6}, 2);

        System.out.println(Arrays.toString(heap.HeapArray));

        var max = heap.GetMax();

        Assertions.assertEquals(max, 6);
        System.out.println(Arrays.toString(heap.HeapArray));

        max = heap.GetMax();

        Assertions.assertEquals(max, 5);
        System.out.println(Arrays.toString(heap.HeapArray));
    }
}

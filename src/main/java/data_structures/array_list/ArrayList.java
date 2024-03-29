package data_structures.array_list;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayList<T> {
    public T[] array;
    public int count;
    public int capacity;
    public Class clazz;

    /* @ invariant array != null @ */
    /* @ invariant count >= 0 @ */
    /* @ invariant capacity >= 0 @ */
    /* @ invariant clazz != null @ */


    private final static int INIT_ARR_SIZE = 16;

    public ArrayList(Class clz) {
        clazz = clz; // нужен для безопасного приведения типов
        count = 0;
        makeArray(INIT_ARR_SIZE);
        capacity = INIT_ARR_SIZE;
    }

    public void makeArray(int new_capacity) {
        if (array == null) {
            array = (T[]) Array.newInstance(clazz, 16);
        } else {
            array = Arrays.copyOf(array, capacity = Math.max(new_capacity, 16));
        }
    }

    public T getItem(int index) {
        if (index < 0 || index > count - 1) {
            throw new RuntimeException();
        }
        return array[index];
    }

    public void append(T itm) {
        if (array.length == count) {
            makeArray(count * 2);
        }
        array[count++] = itm;
    }

    public void insert(T itm, int index) {
        if (index < 0 || index > count) {
            throw new RuntimeException();
        }
        // если уперлись в границы массива - увеличиваем массив
        if (count == array.length) {
            makeArray(array.length * 2);
        }
        if (count == index) {
            append(itm);
        } else {
            T[] buffer = Arrays.copyOfRange(array, index, count);
            for (int i = 0; i < buffer.length; i++) {
                array[index + 1 + i] = buffer[i];
            }
            buffer = null;
            array[index] = itm;
            count++;
        }
    }

    public void remove(int index) {
        if (index < 0 || index > count) {
            throw new RuntimeException();
        }
        if ((double) array.length / (count - 1) > 2d) {
            int newCapacity = (int) (capacity / 1.5f);
            makeArray(newCapacity);
        }
        T[] buffer = Arrays.copyOfRange(array, index + 1, count--);
        System.arraycopy(buffer, 0, array, index, buffer.length);;
    }
}

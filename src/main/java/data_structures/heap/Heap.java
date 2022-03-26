package data_structures.heap;

import java.util.*;

class Heap {
    public int[] HeapArray; // хранит неотрицательные числа-ключи

    public Heap() {
        HeapArray = null;
    }

    public void MakeHeap(int[] a, int depth) {
        createEmptyArr(depth);

        for (int i = 1; i <= a.length; i++) {
            Add(a[i - 1], i);
        }
        // создаём массив кучи HeapArray из заданного
        // размер массива выбираем на основе глубины depth
        // ...
    }

    private void createEmptyArr(int depth) {
        this.HeapArray = new int[calcArrSize(depth)];
        for (int i = 0; i < HeapArray.length; i++) {
            HeapArray[i] = Math.negateExact(i + 1);
        }
    }

    private int calcArrSize(int depth) {
        int size = 0;
        for (int i = 0, j = 1; i <= depth; j = j * 2, i++) {
            size += j;
        }
        return size;
    }

    public int GetMax() {
        int minIndex = findMinIndex();
        if (minIndex < 0) {
            return -1; // если куча пуста
        }
        int root = HeapArray[0];
        int currIndex = 0;
        HeapArray[0] = HeapArray[minIndex];
        HeapArray[minIndex] = Math.negateExact(minIndex + 1);
        while(currIndex * 2 + 2 < HeapArray.length) {
            int switchCandidateIndex;
            var leftChildIndex = currIndex * 2 + 1;
            var rightChildIndex = currIndex * 2 + 2;
            var leftChild = HeapArray[leftChildIndex];
            var rightChild = HeapArray[rightChildIndex];
            if (HeapArray[currIndex] < leftChild || HeapArray[currIndex] < rightChild) {
                switchCandidateIndex = leftChild > rightChild ? leftChildIndex : rightChildIndex;
            } else {
                break;
            }
            int tmp = HeapArray[currIndex];
            HeapArray[currIndex] = HeapArray[switchCandidateIndex];
            HeapArray[switchCandidateIndex] = tmp;
            currIndex = switchCandidateIndex;
        }
        return root;
        // вернуть значение корня и перестроить кучу
    }

    public int findMinIndex() {
        for (int i = HeapArray.length - 1; i > 0; i--) {
            if (HeapArray[i] >= 0) {
                return i;
            }
        }
        return -1;
    }

    public boolean Add(int key) {
        if (HeapArray[HeapArray.length - 1] < 0) {
            Add(key, findEmptyIndex() + 1);
        }
        // добавляем новый элемент key в кучу и перестраиваем её
        return false; // если куча вся заполнена
    }

    private void Add(int key, int index) {
        HeapArray[index - 1] = key;
        int currIndex = index;
        int rootIndex = Math.max(currIndex / 2 - 1, 0);
        while (HeapArray[rootIndex] < HeapArray[currIndex - 1]) {
            int tmp = HeapArray[rootIndex];
            HeapArray[rootIndex] = HeapArray[currIndex - 1];
            HeapArray[currIndex - 1] = tmp;
            currIndex = currIndex / 2;
            rootIndex = Math.max(currIndex / 2 - 1, 0);
        }
    }

    private int findEmptyIndex() {
        for (int i = HeapArray.length - 1; i > 0; i--) {
            if (HeapArray[i - 1] >= 0) {
                return i;
            }
        }
        return 0;
    }
}
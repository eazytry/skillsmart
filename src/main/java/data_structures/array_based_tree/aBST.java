package data_structures.array_based_tree;

import java.util.*;

class aBST {
    public Integer Tree[]; // массив ключей

    public aBST(int depth) {
        // правильно рассчитайте размер массива для дерева глубины depth:
        int tree_size = 1;
        for (int i = 1, j = 2; i <= depth; i++, j = j * 2) {
            tree_size += j;
        }
        Tree = new Integer[tree_size];
        for (int i = 0; i < tree_size; i++) Tree[i] = null;
    }

    public Integer FindKeyIndex(int key) {
        for (int i = 0; i < Tree.length; ) {
            if (Tree[i] == null) {
                return Math.negateExact(i);
            }
            if (Tree[i] == key) {
                return i;
            }
            if (key < Tree[i]) {
                i = i * 2 + 1;
            } else {
                i = i * 2 + 2;
            }
        }
        return null; // не найден
    }

    public int AddKey(int key) {
        for (int i = 0; i < Tree.length; ) {
            if (Tree[i] == null) {
                Tree[i] = key;
                return i;
            }
            if (Tree[i] == key) {
                return i;
            }
            if (key < Tree[i]) {
                i = i * 2 + 1;
            } else {
                i = i * 2 + 2;
            }
        }
        return -1;
        // индекс добавленного/существующего ключа или -1 если не удалось
    }

}
package data_structures.binary_tree;

import java.util.*;

public class AlgorithmsDataStructures2 {
    public static int[] GenerateBBSTArray(int[] a) {
        assert a != null;
        Arrays.sort(a);

        int[] tree = new int[findArrSize(a.length)];

        generateBST(a, tree, 0);
        return tree;
    }

    private static int findArrSize(int number) {
        int degree = 1;
        int summ = 0;
        while (degree <= number) {
            summ += degree;
            degree = degree * 2;
        }
        return summ;
    }

    private static void generateBST(int[] a, int[] tree, int currTreeIndex) {
        if (a.length == 0) {
            if (currTreeIndex < tree.length) {
                tree[currTreeIndex] = Math.negateExact(currTreeIndex);
            }
            return;
        }
        int rootIndex = a.length / 2;
        tree[currTreeIndex] = a[rootIndex];

        generateBST(Arrays.copyOfRange(a, 0, rootIndex), tree, currTreeIndex * 2 + 1);
        generateBST(Arrays.copyOfRange(a, rootIndex + 1, a.length), tree, currTreeIndex * 2 + 2);
    }
}
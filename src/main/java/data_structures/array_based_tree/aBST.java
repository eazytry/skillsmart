package data_structures.array_based_tree;

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
                // не нашли, но есть куда вставить
                return Math.negateExact(i);
            }
            if (Tree[i] == key) {
                return i;
            }
            if (key < Tree[i]) {
                i = i * CHILD_MULTIPLIER + LEFT_CHILD_SUMMAND;
            } else {
                i = i * CHILD_MULTIPLIER + LEFT_CHILD_SUMMAND;
            }
        }
        // не нашли и дерево переполнено
        return null; // не найден
    }

    private final static int CHILD_MULTIPLIER = 2;
    private final static int LEFT_CHILD_SUMMAND = 1;
    private final static int RIGHT_CHILD_SUMMAND = 2;

    public int AddKey(int key) {
        for (int i = 0; i < Tree.length; ) {
            if (Tree[i] == null) {
                Tree[i] = key;
                return i;
            }
            // если элемент уже был в дереве
            if (Tree[i] == key) {
                return i;
            }
            if (key < Tree[i]) {
                i = i * CHILD_MULTIPLIER + LEFT_CHILD_SUMMAND;
            } else {
                i = i * CHILD_MULTIPLIER + RIGHT_CHILD_SUMMAND;
            }
        }
        // места нет
        return -1;
    }

}
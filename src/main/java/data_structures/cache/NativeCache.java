package data_structures.cache;

import java.lang.reflect.Array;

public class NativeCache<T> {
    private final Node<T>[] values;
    private int size;

    public NativeCache(int initSize) {
        this.values = (Node<T>[]) Array.newInstance(Node.class, initSize);
        this.size = 0;
    }

    public T get(String key) {
        int currentIndex = hash(key);
        int index = hash(key);

        do {
            if (values[currentIndex] != null && values[currentIndex].getKey().equals(key)) {
                values[currentIndex].incrementHitsCount();
                return values[currentIndex].getValue();
            }
            currentIndex = nextNotNull(currentIndex);
        } while (currentIndex != index
                && currentIndex != -1);

        return null;
    }

    public void put(String key, T value) {
        var index = seek(key);
        values[index] = new Node<>(key, value);
        if (size < values.length)
            size++;
    }

    public int seek(String key) {
        // находит индекс для вставки или замены ключа
        int index = hash(key);
        int currIndex = index;

        do {
            if (values[currIndex] == null || values[currIndex].getKey().equals(key)) {
                return currIndex;
            }
            currIndex = (currIndex + 1) < size ? currIndex + 1 : 0;
        } while (currIndex != index);

        index = -1;
        currIndex = -1;

        int minHitsIndex = 0;

        for (int i = 0; i < this.size; i++) {
            if (values[i] != null && values[i].getHitsCount() < values[minHitsIndex].getHitsCount()) {
                minHitsIndex = i;
            }
        }
        values[minHitsIndex] = null;

        return minHitsIndex;
    }

    private int nextNotNull(int from) {
        int index;

        if (from + 1 < this.size)
            index = from + 1;
        else
            index = 0;

        return values[index] == null ? -1 : index;
    }

    private int hash(String key) {
        return key.hashCode() % values.length;
    }

    class Node<T> {
        private final String key;
        private final T value;
        private int hitsCount;

        public Node(String key, T value) {
            this.key = key;
            this.value = value;
            hitsCount = 1;
        }

        public void incrementHitsCount() {
            this.hitsCount++;
        }

        public int getHitsCount() {
            return this.hitsCount;
        }

        public T getValue() {
            return this.value;
        }

        public String getKey() {
            return this.key;
        }
    }
}

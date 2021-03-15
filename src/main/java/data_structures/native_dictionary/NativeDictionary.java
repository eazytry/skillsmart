package data_structures.native_dictionary;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class NativeDictionary<T> {
    public int size;
    public String[] slots;
    public T[] values;

    public NativeDictionary(int sz, Class clazz) {
        size = sz;
        slots = new String[size];
        values = (T[]) Array.newInstance(clazz, this.size);
    }

    public int hashFun(String key) {
        // всегда возвращает корректный индекс слота
        return key.hashCode() % size;
    }

    public boolean isKey(String key) {
        // возвращает true если ключ имеется,
        // иначе false
        if (key == null)
            return false;
        return Arrays.stream(getKeySearchIndexSequence(key, hashFun(key)))
                .filter(i -> slots[i] != null && slots[i].equals(key))
                .findFirst()
                .isPresent();
    }

    public void put(String key, T value) {
        // гарантированно записываем
        // значение value по ключу key
        Arrays.stream(getKeySearchIndexSequence(key, hashFun(key)))
                .filter(i -> slots[i] == null)
                .findFirst()
                .ifPresent(new PutConsumer(slots, values, key, value));
    }

    public T get(String key) {
        OptionalInt foundKeyOpt = Arrays.stream(getKeySearchIndexSequence(key, hashFun(key)))
                .filter(i -> slots[i] != null && slots[i].equals(key))
                .findFirst();
        return foundKeyOpt.isPresent() ? values[foundKeyOpt.getAsInt()] : null;
    }

    private int[] getKeySearchIndexSequence(String key, int index) {
        return IntStream.concat(IntStream.range(index, size), IntStream.range(0, index)).toArray();
    }

    class PutConsumer implements IntConsumer {
        private final String[] slots;
        private final T[] values;
        private final String key;
        private final T value;

        public PutConsumer(String[] slots, T[] values, String key, T value) {
            this.slots = slots;
            this.values = values;
            this.key = key;
            this.value = value;
        }

        @Override
        public void accept(int value) {
            this.slots[value] = this.key;
            this.values[value] = this.value;
        }
    }
}
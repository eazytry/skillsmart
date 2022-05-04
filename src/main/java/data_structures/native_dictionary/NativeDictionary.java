package data_structures.native_dictionary;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;
public class NativeDictionary<T> {
    public int size;
    public volatile String[] slots;
    public volatile T[] values;

    /* @ invariant size >= 0 @ */
    /* @ invariant slots != null @ */
    /* @ invariant values != null @ */

    public NativeDictionary(int sz, Class clazz) {
        size = sz;
        slots = new String[size];
        values = (T[]) Array.newInstance(clazz, this.size);
    }

    public int hashFun(String key) {
        // всегда возвращает корректный индекс слота
        return Math.abs(key.hashCode()) % size;
    }

    public boolean isKey(String key) {
        // возвращает true если ключ имеется,
        // иначе false
        if (key == null)
            return false;
        return Arrays.stream(getKeySearchIndexSequence(hashFun(key)))
                .filter(i -> slots[i] != null && slots[i].equals(key))
                .findFirst()
                .isPresent();
    }

    public void put(String key, T value) {
        // гарантированно записываем
        // значение value по ключу key
        Arrays.stream(getKeySearchIndexSequence(hashFun(key)))
                .filter(i -> key.equals(slots[i]) || slots[i] == null)
                .findFirst()
                .ifPresent(i -> {
                    slots[i] = key;
                    values[i] = value;
                });
    }

    public T get(String key) {
        OptionalInt foundKeyOpt = Arrays.stream(getKeySearchIndexSequence(hashFun(key)))
                .filter(i -> slots[i] != null && slots[i].equals(key))
                .findFirst();
        return foundKeyOpt.isPresent() ? values[foundKeyOpt.getAsInt()] : null;
    }

    private int[] getKeySearchIndexSequence(int index) {
        return IntStream.concat(IntStream.range(index, size), IntStream.range(0, index)).toArray();
    }
}
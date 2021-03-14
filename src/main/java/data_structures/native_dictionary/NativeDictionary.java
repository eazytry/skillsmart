package data_structures.native_dictionary;

import java.lang.reflect.Array;

class NativeDictionary<T> {
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
        return slots[hashFun(key)] != null;
    }

    public void put(String key, T value) {
        // гарантированно записываем
        // значение value по ключу key
        int index = hashFun(key);
        slots[index] = key;
        values[index] = value;
    }

    public T get(String key) {
        if (isKey(key))
            return values[hashFun(key)];
        return null;
    }
}
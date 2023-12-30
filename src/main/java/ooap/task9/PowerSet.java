package ooap.task9;

import ooap.task7.HashTable;

public interface PowerSet<T> extends HashTable<T, Void> {
    // Постусловия: возвращено пересечение двух сет-ов
    PowerSet<T> intersection(PowerSet<T> powerSet);
    // Постусловие: возвращен сет содержащий элементы из обоих сет-ов
    PowerSet<T> union(PowerSet<T> powerSet);
    // Постусловие: возвращен сет, содержащий разницу двух сетов
    PowerSet<T> difference(PowerSet<T> p);
    // Постусловие: true если элементы присутствуют, иначе false
    boolean containsAll(PowerSet<T> powerSet);
}

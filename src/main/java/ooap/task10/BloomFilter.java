package ooap.task10;

public interface BloomFilter<T> {
    // Команды

    // Постусловие: добавлен элемент в филтр
    void add(T t);
    // Запросы

    // Постусловие: возвращено true, если элемент был в фильтре, иначе false
    boolean isValue(T t);
}

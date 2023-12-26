package ooap.task7;

public interface HashTable<K, V> {
    // Команды

    // Постусловия: добавлен новый элемент в таблицу
    void put(K k, V v);

    // Запросы

    // Постусловия: возвращено значение по ключу
    V get(K k);

    // Постусловия: возвращен текущий размер таблицы
    int size();

    // Постусловия: удален элемент с ключем k
    void remove(K k);

    // Постусловия: true если такой ключ уже есть в таблице, иначе false
    boolean containsKey(K k);

    PutStatus getPutStatus();

    GetStatus getGetStatus();

    RemoveStatus getRemoveStatus();

    enum PutStatus {INITIAL, OVERRIDE, SUCCESS}

    enum GetStatus {INITIAL, NOT_EXIST, SUCCESS}

    enum RemoveStatus {INITIAL, NOT_EXIST, SUCCESS}
}

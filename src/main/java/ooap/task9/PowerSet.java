package ooap.task9;

public interface PowerSet<T> {
    // Команды

    // Постусловие: Добавлен элемент t
    void put(T t);

    // Предусловие: сет не пустой, элемент t присутствует
    // Постусловие: Удален элемент t
    void remove(T t);

    // Постусловие: Добавлен элементы powerSet
    void putAll(PowerSet<T> powerSet);

    // Запросы

    // Постусловие: true если элемент присутствует, иначе false
    boolean contains(T t);
    // Постусловие: true если элементы присутствуют, иначе false
    boolean containsAll(PowerSet<T> powerSet);
    // Постусловие: возвращен текущий размер сет-а
    int size();
    // Предусловия: аргумент не null
    // Постусловия: возвращено пересечение двух сет-ов
    PowerSet<T> intersection(PowerSet<T> powerSet);
    // Предусловие: аргумент не null
    // Постусловие: возвращен сет содержащий элементы из обоих сет-ов
    PowerSet<T> merge(PowerSet<T> powerSet);
    // Предусловие: аргумент не null
    // Постусловие: возвращен сет, содержащий разницу двух сетов
    PowerSet<T> difference(PowerSet<T> p);

    PutStatus getPutStatus();
    RemoveStatus getRemoveStatus();
    PutStatus getPutAllStatus();
    IntersectionStatus getIntersectionStatus();
    MergeStatus getMergeStatus();
    DifferenceStatus getDifferenceStatus();

    enum PutStatus { INITIAL, SUCCESS }
    enum RemoveStatus { INITIAL, SUCCESS, DID_NOT_EXIST }
    enum IntersectionStatus { INITIAL, SUCCESS, ARGUMENT_IS_NULL }
    enum MergeStatus { INITIAL, SUCCESS, ARGUMENT_IS_NULL }
    enum DifferenceStatus { INITIAL, SUCCESS, ARGUMENT_IS_NULL }
}

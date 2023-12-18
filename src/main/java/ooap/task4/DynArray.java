package ooap.task4;

public interface DynArray<T> {
    // Запросы

    // Предусловие: индекс лежит в допустимых пределах массива
    // Постусловие: возвращен эллемент под переданным индексом или его отсутствие
    T getItem(int index);

    // Постусловие: возвращен текущий размер листа
    int getSize();

    PutStatus getPutStatus();

    GetStatus getGetStatus();

    RemoveStatus getRemoveStatus();

    PutRightStatus getPutLeftStatus();

    PutLeftStatus getPutRightStatus();

    enum RemoveStatus {OUT_OF_BOUND, SUCCESS, LIST_EMPTY}

    enum PutStatus {OUT_OF_BOUND, SUCCESS, LIST_EMPTY}

    enum PutLeftStatus {OUT_OF_BOUND, SUCCESS, LIST_EMPTY}

    enum PutRightStatus {OUT_OF_BOUND, SUCCESS, LIST_EMPTY}

    enum GetStatus {OUT_OF_BOUND, SUCCESS}


    // Команды

    // Постусловие: добавлен переданный элемент
    void append(T item);

    // Предусловие: $index в пределах массива. Лист не должен быть пустой.
    // Постусловие: втсавлен $item на место элемента под индексом $index
    void put(T item, int index);

    // Предусловие: $index в пределах массива. Лист не должен быть пустой.
    // Постусловие: втсавлен $item на место элемента под индексом $index
    void putLeft(T item, int index);

    // Предусловие: $index в пределах массива. Лист не должен быть пустой.
    // Постусловие: втсавлен $item на место элемента под индексом $index
    void putRight(T item, int index);

    // Предусловие: $index в пределах массива. Лист не должен быть пустой.
    // Постусловие: удален элемент под индексом $index
    void remove(int index);

}

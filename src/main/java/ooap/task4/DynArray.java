package ooap.task4;
public interface DynArray<T> {
    // Запросы

    // Постусловие: возвращен эллемент под переданным индексом или его отсутствие
    T getItem(int index);

    // Постусловие: возвращен текущий размер листа
    int getSize();
    InsertStatus getLastInsertStatus();
    RemoveStatus getLastRemoveStatus();

    enum RemoveStatus { LESS_THAN_ZERO, MORE_THAN_LIST_SIZE, SUCCESS, LIST_EMPTY }
    enum InsertStatus { LESS_THAN_ZERO, MORE_THAN_LIST_SIZE, SUCCESS, LIST_EMPTY }


    // Команды

    // Постусловие: добавлен переданный элемент
    void append(T item);

    // Предусловие: $index должен быть не меньше нуля и не больше текущего размера листа. Лист не должен быть пустой.
    // Постусловие: втсавлен $item на место элемента под индексом $index
    void insert(T item, int index);

    // Предусловие: $index должен быть не меньше нуля и не больше текущего размера листа. Лист не должен быть пустой.
    // Постусловие: удален элемент под индексом $index
    void remove(int index);

}

package ooap.task5;

public interface Queue<T> {
    // Команды

    // Постусловия: добавлен элемент в голову очереди
    void add(T item);

    // Предусловия: очередь не пуста
    // Постусловия: возвращен элемент из головы очереди, элемент удален из очереди
    T poll();

    // Постусловия: очередь очищена
    void clear();


    // Запросы

    // Предусловия: очередь не пуста
    // Постусловия: возвращен элемент из головы очереди
    T peek();

    // Постусловия: возвращен текущий размер очереди
    int size();

    AddStatus getAddStatus();

    PollStatus getPollStatus();

    PeekStatus getPeekStatus();

    enum AddStatus { INITIAL, SUCCESS }
    enum PollStatus { SUCCESS, EMPTY }
    enum PeekStatus { SUCCESS, EMPTY }
}

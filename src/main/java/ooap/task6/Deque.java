package ooap.task6;

import ooap.task5.Queue;

public interface Deque<T> {
    // Команды

    // Постусловия: добавлен элемент в хвост очереди
    void pushTail(T item);

    // Постусловия: добавлен элемент в голову очереди
    void pushHead(T item);

    // Предусловия: очередь не пуста
    // Постусловия: возвращен элемент из головы очереди, элемент удален из головы очереди
    T pollTail();
    // Предусловия: очередь не пуста
    // Постусловия: возвращен элемент из головы очереди, элемент удален из головы очереди
    T pollHead();

    // Постусловия: очередь очищена
    void clear();


    // Запросы

    // Предусловия: очередь не пуста
    // Постусловия: возвращен элемент из головы очереди
    T peekHead();

    // Предусловия: очередь не пуста
    // Постусловия: возвращен элемент из хвоста очереди
    T peekTail();

    // Постусловия: возвращен текущий размер очереди
    int size();

    PushStatus getPushHeadStatus();
    PushStatus getPushTailStatus();
    PollStatus getPollHeadStatus();
    PollStatus getPollTailStatus();
    PeekStatus getPeekHeadStatus();
    PeekStatus getPeekTailStatus();

    enum PushStatus { INITIAL, SUCCESS }
    enum PollStatus { SUCCESS, EMPTY }
    enum PeekStatus { SUCCESS, EMPTY }
}

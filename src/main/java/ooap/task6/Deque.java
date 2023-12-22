package ooap.task6;

import ooap.task5.Queue;

public interface Deque<T> extends Queue<T>{
    // Команды

    // Постусловия: добавлен элемент в голову очереди
    void addHead(T item);

    // Предусловия: очередь не пуста
    // Постусловия: возвращен элемент из головы очереди, элемент удален из головы очереди
    T pollTail();

    // Запросы

    // Предусловия: очередь не пуста
    // Постусловия: возвращен элемент из хвоста очереди
    T peekTail();

    AddStatus getAddHeadStatus();
    PollStatus getPollTailStatus();
    PeekStatus getPeekTailStatus();
}

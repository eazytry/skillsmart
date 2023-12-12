package ooap.task2;

public interface LinkedList<T> {
    // Команды

    // Предусловия: список не пустой
    // Постусловия: указатель стоит на первом узле в списке
    void head();
    // Предусловия: список не пустой
    // Постусловия: указатель стоит на последнем узле в списке
    void tail();

    // Предусловия: список не пустой, текущий указатель находится не в хвосте
    // Постусловия: указатель сдвинут на один элемент вправо
    void right();

    // Предусловия: текущий указатель установлен
    // Постусловия: добавлен новый элемент справа от текущего указателя, указатель не сдвигается
    void putRight(T t);

    // Предусловия: текущий указатель установлен
    // Постусловия: добавлен новый элемент слева от текущего указателя, указатель не сдвигается
    void putLeft(T t);

    // Предусловия: текущий указатель установлен
    // Постусловия: элемент, на который указывал текущий указатель удален, указатель смещается вправо, если элемент есть
    // или влево, если элемент есть, если элемент был последний, то указатель становится NULL
    void remove();

    // Постусловия: список очищен, указатель никуда не указывает
    void clear();

    // Постусловия: добавлен новый элемент в конец списка
    void addTail(T t);

    // Предусловия: текущий указатель установлен
    // Постусловия: элемент, на который указывает текущий указатель заменен на элемент из аргументов
    void replace(T t);

    // Предусловия: текущий указатель установлен, текущий указатель находится не в хвосте, искомый элемент существует
    // Постусловия: указатель установлен на следующий, относительно текущего, элемент, значение которого равно аргументу
    void find(T t);


    // Запросы
    // Предусловие: список не пустой

    // Предусловия: указатель установлен
    // Постусловия: возвращен элемент, на который указывает текущее значение указателя
    T get();
    // Постусловия: возвращен текущий размер списка
    int size();
    boolean isHead();
    boolean isTail();
    boolean isValue();


    // Запросы статуса команд

    // Постусловия: возвращен статус последнего вызова метода replace(T t)
    ReplaceStatus getReplaceStatus();

    // Постусловия: возвращен статус последнего вызова метода find(T t)
    FindStatus getFindStatus();

    // Постусловия: возвращен статус последнего вызова метода remove()
    RemoveStatus getRemoveStatus();

    // Постусловия: возвращен статус последнего вызова метода head()
    HeadStatus getHeadStatus();

    // Постусловия: возвращен статус последнего вызова метода tail()
    TailStatus getTailStatus();

    // Постусловия: возвращен статус последнего вызова метода right()
    RightStatus getRightStatus();

    // Постусловия: возвращен статус последнего вызова метода putRight(T t)
    PutRightStatus getPutRightStatus();

    // Постусловия: возвращен статус последнего вызова метода putLeft(T t)
    PutLeftStatus getPutLeftStatus();

    enum ReplaceStatus { NOT_INVOKED, ITERATOR_NOT_SET, SUCCESS }
    enum FindStatus { NOT_INVOKED, ITERATOR_NOT_SET, SUCCESS, NOT_FOUND }
    enum RemoveStatus { NOT_INVOKED, MOVED_RIGHT, MOVED_LEFT, MOVED_TO_NOT_SET, ITERATOR_NOT_SET }
    enum HeadStatus { NOT_INVOKED, LIST_EMPTY }
    enum TailStatus { NOT_INVOKED, LIST_EMPTY }
    enum RightStatus { NOT_INVOKED, LIST_EMPTY, ITERATOR_ON_TAIL, ITERATOR_NOT_SET }
    enum PutRightStatus { NOT_INVOKED, LIST_EMPTY, ITERATOR_NOT_SET }
    enum PutLeftStatus { NOT_INVOKED, LIST_EMPTY, ITERATOR_NOT_SET }
}

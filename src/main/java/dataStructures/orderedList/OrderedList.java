package dataStructures.orderedList;

import java.util.ArrayList;


class Node<T> {
    public T value;
    public Node<T> next, prev;

    public Node(T _value) {
        value = _value;
        next = null;
        prev = null;
    }
}
public class OrderedList<T> {

    public Node<T> head, tail;
    private boolean _ascending;
    private int size;

    public OrderedList(boolean asc) {
        head = null;
        tail = null;
        size = 0;
        _ascending = asc;
    }

    public int compare(T v1, T v2) {
        if (v1 instanceof Comparable) {
            return ((Comparable<T>) v1).compareTo(v2);
        }
        throw new RuntimeException();
    }

    public void add(T value) {
        if (size < 1)
            addFirst(value);
        else if(size == 1)
            addSecond(value);
        else {
            switch (checkRange(value)) {
                case -1:
                    addHead(value);
                    break;
                case 1:
                    addTail(value);
                    break;
                case 0:
                    addToRange(value);
                    break;
            }
        }
        // автоматическая вставка value
        // в нужную позицию
    }

    private void addToRange(T value) {
        Node<T> newNode = new Node<>(value);
        insertAfter(findInsertPoint(value), newNode);
    }

    private Node<T> findInsertPoint(T value) {
        Node<T> currentNode = this.head;
        if (_ascending) {
            // до тех пор, пока вставляемое значение <= currentNode
            while (compare(value, currentNode.value) < 0) {
                currentNode = currentNode.next;
            }
        } else {
            // до тех пор, пока вставляемое значение >= currentNode
            while (compare(value, currentNode.value) > 0) {
                currentNode = currentNode.next;
            }
        }
        return currentNode;
    }

    private void insertAfter(Node<T> afterNode, Node<T> newNode) {
        Node<T> prevNode = afterNode.next;
        if (prevNode != null)
            prevNode.prev = newNode;
        else
            this.tail = newNode;
        afterNode.next = newNode;
        newNode.prev = afterNode;
        newNode.next = prevNode;
        size++;
    }

    public Node<T> find(T val) {
        Node<T> node = this.head;
        while (node != null && !node.value.equals(val)) {
            node = node.next; // здесь будет ваш код
        }
        return node;
    }

    public void delete(T val) {
        Node<T> node = find(val);
        if (node != null) {
            if (node.prev != null)
                node.prev.next = node.next;
            else
                this.head = node.next;
            if (node.next != null)
                node.next.prev = node.prev;
            else
                this.tail = node.prev;
            size--;
        }
    }

    public void clear(boolean asc) {
        _ascending = asc;
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public int count() {
        return size; // здесь будет ваш код подсчёта количества элементов в списке
    }

    private int checkRange(T val) {
        // вернет -1 если значение за пределами головы
        // вернет +1 если значение за пределами хвоста
        // вернет 0 если значение в пределах списка
        int compareHeadResult = compare(val, head.value);
        int compareTailResult = compare(val, tail.value);

        if (_ascending) {
            if (compareHeadResult == -1)
                return -1;
            else if (compareTailResult == 1)
                return  1;
            else
                return 0;
        } else {
            if (compareHeadResult == 1)
                return -1;
            else if (compareTailResult == -1)
                return 1;
            else
                return 0;
        }
    }

    private void addFirst(T val) {
        Node<T> node = new Node<>(val);
        this.head = node;
        this.tail = node;
        size++;
    }

    private void addSecond(T val) {
        int compareFirstResult = compare(val, this.head.value);
        boolean isAfterFirst = _ascending == (compareFirstResult >= 0);

        if (isAfterFirst) {
            addTail(val);
        } else {
            addHead(val);
        }
    }

    private void addHead(T val) {
        Node<T> newNode = new Node(val);
        newNode.next = this.head;
        this.head.prev = newNode;
        this.head = newNode;
        size++;
    }

    private void addTail(T val) {
        Node<T> newNode = new Node(val);
        newNode.prev = this.tail;
        this.tail.next = newNode;
        this.tail = newNode;
        size++;
    }

    public ArrayList<Node<T>> getAll() {
        // выдать все элементы упорядоченного
        // списка в виде стандартного списка
        ArrayList<Node<T>> r = new ArrayList<Node<T>>();
        Node<T> node = head;
        while (node != null) {
            r.add(node);
            node = node.next;
        }
        return r;
    }
}
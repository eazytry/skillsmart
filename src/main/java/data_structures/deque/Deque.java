package data_structures.deque;

public class Deque<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public Deque() {
        this.head = null;
        this.head = null;
        this.size = 0;
        // инициализация внутреннего хранилища
    }

    public void addFront(T item) {
        Node<T> node = new Node<>(item);
        if (size == 0)
            initDeque(node);
        else {
            node.nextNode = this.head;
            this.head.prevNode = node;
            this.head = node;
        }
        size++;
        // добавление в голову
    }

    public void addTail(T item) {
        Node<T> node = new Node<>(item);
        if (size == 0)
            initDeque(node);
        else {
            node.prevNode = this.tail;
            this.tail.nextNode = node;
            this.tail = node;
        }
        size++;
        // добавление в хвост
    }

    public T removeFront() {
        if (isEmpty())
            return null;
        Node<T> removingValue = this.head;
        if (removingValue.nextNode != null)
            removingValue.nextNode.prevNode = null;
        this.head = removingValue.nextNode;
        size--;
        return removingValue.value;
    }

    public T removeTail() {
        if (isEmpty())
            return null;
        Node<T> removingValue = this.tail;
        if (removingValue.prevNode != null)
            removingValue.prevNode.nextNode = null;
        this.tail = removingValue.prevNode;
        size--;
        return removingValue.value;
        // удаление из хвоста
    }

    public int size() {
        return size; // размер очереди
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void initDeque(Node<T> firstNode) {
        this.head = firstNode;
        this.tail = firstNode;
    }


    private static class Node<T> {
        private Node<T> prevNode;
        private Node<T> nextNode;
        private T value;

        Node(T value) {
            this.value = value;
        }
    }
}

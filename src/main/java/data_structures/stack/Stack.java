package data_structures.stack;

public class Stack<T> {
    public Node<T> head;
    public Node<T> tail;
    public int count;

    public Stack() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    public int size() {
        return count;
    }

    public T pop() {
        if (count == 0)
            return null;
        Node<T> valNode = this.head;
        if (count == 1) {
            this.head = null;
            this.tail = null;
        }
        T val = valNode.getT();
        this.head = valNode.nextNode;
        count--;
        return val;
    }

    public void push(T val) {
        Node<T> node = new Node<>(val);
        if (count == 0)
            this.tail = node;
        node.nextNode = this.head;
        this.head = node;
        count++;
    }

    public T peek() {
        return head == null ? null : head.getT();
    }

    public static class Node<T> {
        public Node<T> nextNode;
        public T t;

        public Node(T t) {
            this.nextNode = null;
            this.t = t;
        }

        public void setNextNode(Node<T> nextNode) {
            this.nextNode = nextNode;
        }

        public T getT() {
            return t;
        }
    }
}

package data_structures.queue;

import java.util.Stack;

public class Queue<T> {
    private final Stack<T> firstStack;
    private final Stack<T> secondStack;
    private int size;

    /* @ invariant firstStack != null @ */
    /* @ invariant secondStack != null @ */
    /* @ invariant size >= 0 @ */

    public Queue() {
        this.secondStack = new Stack<>();
        this.firstStack = new Stack<>();
        this.size = 0;
        // инициализация внутреннего хранилища очереди
    }

    public void enqueue(T item) {
        this.firstStack.push(item);
        size++;
        // вставка в хвост
    }

    public T dequeue() {
        if(size == 0)
            return null;
        if(secondStack.empty())
            fillSecondStack();
        size--;
        return secondStack.pop();
    }

    private void fillSecondStack() {
        while(!firstStack.empty()) {
            secondStack.push(firstStack.pop());
        }
    }

    public int size() {
        return size; // размер очереди
    }
}
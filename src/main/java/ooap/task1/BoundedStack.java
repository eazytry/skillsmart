package ooap.task1;

public class BoundedStack<T> {
    private static final int DEFAULT_SIZE = 32;

    private final int maxSize;
    private final T[] stackArray;
    private int top;
    private PeekStatus peekStatus = PeekStatus.INITIAL;
    private PopStatus popStatus = PopStatus.INITIAL;
    private PushStatus pushStatus = PushStatus.INITIAL;

    public BoundedStack() {
        this(DEFAULT_SIZE);
    }

    @SuppressWarnings("unchecked")
    public BoundedStack(int maxSize) {
        this.maxSize = maxSize;
        this.stackArray = (T[]) new Object[maxSize];
        this.top = -1;
    }

    public T pop() {
        if (top == -1) {
            this.popStatus = PopStatus.EMPTY;
            return null;
        }

        var item = stackArray[top];
        stackArray[top] = null;
        top--;
        popStatus = PopStatus.SUCCESS;

        return item;
    }

    public T peek() {
        if (top == -1) {
            peekStatus = PeekStatus.EMPTY;
            return null;
        }

        var item = stackArray[top];
        peekStatus = PeekStatus.SUCCESS;

        return item;
    }

    public void push(T t) {
        if (top == maxSize - 1) {
            pushStatus = PushStatus.FULL;
        } else {
            stackArray[++top] = t;
            pushStatus = PushStatus.SUCCESS;
        }
    }

    public int size() {
        return top + 1;
    }

    public void clear() {
        top = -1;
    }

    public PushStatus getPushStatus() {
        return pushStatus;
    }

    public PopStatus getPopStatus() {
        return popStatus;
    }

    public PeekStatus getPeekStatus() {
        return peekStatus;
    }

    public enum PopStatus {
        INITIAL,
        SUCCESS,
        EMPTY
    }

    public enum PeekStatus {
        INITIAL,
        SUCCESS,
        EMPTY
    }

    public enum PushStatus {
        INITIAL,
        SUCCESS,
        FULL
    }

}

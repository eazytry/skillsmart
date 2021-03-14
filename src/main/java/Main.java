import data_structures.stack.Stack;

public class Main {
    public static void main(String[] args) {
        Stack<String> stringStack = new Stack<>();
        stringStack.push(null);

        System.out.println(stringStack.peek());

    }
}

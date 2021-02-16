package dataStructures.stack;

import java.util.Stack;

public class ParenthesesParityCalculator {
    private Stack<Character> stack;

    public ParenthesesParityCalculator(String expression) {
        this.stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            stack.push(expression.charAt(i));
        }
    }

    public boolean calculate() {
        int counter = 0;
        while (!stack.empty()) {
            switch (stack.pop()) {
                case '(':
                    counter--;
                    break;
                case ')':
                    counter++;
                    break;
            }
            if (counter < 0)
                return false;
        }
        return counter == 0;
    }

    public static void main(String[] args) {
        ParenthesesParityCalculator calculator = new ParenthesesParityCalculator("()())(");
        System.out.println(calculator.calculate());
    }
}

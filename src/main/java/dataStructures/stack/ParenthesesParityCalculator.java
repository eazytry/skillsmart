package dataStructures.stack;

import java.util.Stack;

public class ParenthesesParityCalculator {
    private Stack<Character> stack;
    private String expression;

    public ParenthesesParityCalculator(String expression) {
        this.stack = new Stack<>();
        this.expression = expression;
    }

    public boolean calculate() {
        for (int i = 0; i < expression.length(); i++) {
            switch (expression.charAt(i)) {
                case '(':
                    stack.push(expression.charAt(i));
                    break;
                case ')':
                    if (stack.empty())
                        return false;
                    stack.pop();
                    break;
            }
        }
        return stack.size() == 0;
    }

    public static void main(String[] args) {
        ParenthesesParityCalculator calculator = new ParenthesesParityCalculator("(()())(");
        System.out.println(calculator.calculate());
    }
}

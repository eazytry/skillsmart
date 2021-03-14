package data_structures.stack;

import java.util.Arrays;

public class PostfixExpressionCalculator {
    private final static Character[] legalOperators = new Character[]{'+', '*', '='};

    private final String expression;
    private java.util.Stack<Character> operators;
    private java.util.Stack<Character> digits;

    public PostfixExpressionCalculator(String expression) {
        this.expression = expression;
        this.operators = new java.util.Stack<>();
        this.digits = new java.util.Stack<>();
    }

    public int calculate() {
        fillStacks();
        Integer result = null;
        while (!digits.empty()) {
            if (result == null) {
                result = Integer.parseInt(String.valueOf(digits.pop()));
                continue;
            }
            switch (operators.pop()) {
                case '+': {
                    result += Integer.parseInt(String.valueOf(digits.pop()));
                    break;
                }
                case '*': {
                    result *= Integer.parseInt(String.valueOf(digits.pop()));
                    break;
                }
                case '=': {
                    return result;
                }
            }
        }
        return result;
    }

    private void fillStacks() {
        for (int i = expression.length() - 1; i >= 0; i--) {
            char candidate = expression.charAt(i);
            if (Character.isDigit(candidate)) {
                digits.push(candidate);
            } else if (Arrays.asList(legalOperators).contains(candidate)) {
                operators.push(candidate);
            }
        }
    }
}

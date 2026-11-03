package week11.assignment;

import java.util.*;

public class QuizScoringFormula {
    static int precedence(char op) {
        return (op == '*' || op == '/') ? 2 : 1;
    }

    static void apply(Stack<Integer> values, Stack<Character> ops) {
        int b = values.pop();
        int a = values.pop();
        char op = ops.pop();

        switch (op) {
            case '+':
                values.push(a + b);
                break;
            case '-':
                values.push(a - b);
                break;
            case '*':
                values.push(a * b);
                break;
            case '/':
                values.push(a / b);
                break;
        }
    }

    static int evaluate(String expr) {
        Stack<Integer> values = new Stack<>();
        Stack<Character> ops = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (c == ' ')
                continue;

            if (Character.isDigit(c)) {
                int num = 0;

                while (i < expr.length() &&
                        Character.isDigit(expr.charAt(i))) {
                    num = num * 10 + expr.charAt(i++) - '0';
                }

                values.push(num);
                i--;
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                while (ops.peek() != '(')
                    apply(values, ops);
                ops.pop();
            } else {
                while (!ops.isEmpty() && ops.peek() != '(' &&
                        precedence(ops.peek()) >= precedence(c))
                    apply(values, ops);

                ops.push(c);
            }
        }

        while (!ops.isEmpty())
            apply(values, ops);

        return values.pop();
    }
}

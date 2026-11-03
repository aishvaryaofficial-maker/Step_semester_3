package week11.assignment;

import java.util.*;

public class HackathonBannerPrinter {
    static String expandBanner(String code) {
        Stack<Integer> counts = new Stack<>();
        Stack<StringBuilder> texts = new Stack<>();
        StringBuilder curr = new StringBuilder();
        int num = 0;

        for (char c : code.toCharArray()) {
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                counts.push(num);
                texts.push(curr);
                curr = new StringBuilder();
                num = 0;
            } else if (c == ']') {
                int k = counts.pop();
                StringBuilder temp = texts.pop();

                for (int i = 0; i < k; i++)
                    temp.append(curr);

                curr = temp;
            } else {
                curr.append(c);
            }
        }

        return curr.toString();
    }
}

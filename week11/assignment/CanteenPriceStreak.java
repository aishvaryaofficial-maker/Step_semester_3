package week11.assignment;

public class CanteenPriceStreak {
    static int[] priceStreak(int[] price) {
        int n = price.length;
        int[] ans = new int[n];
        int[] stack = new int[n];
        int top = -1;

        for (int i = 0; i < n; i++) {
            while (top >= 0 && price[stack[top]] <= price[i])
                top--;

            ans[i] = (top == -1) ? i + 1 : i - stack[top];
            stack[++top] = i;
        }

        return ans;
    }
}

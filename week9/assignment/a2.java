package week9.assignment;
public class a2 {

    public static int[] longestStreak(int[] costs, long budget) {

        int left = 0;
        long sum = 0;

        int maxLength = 0;
        int bestStart = -1;

        for (int right = 0; right < costs.length; right++) {

            sum += costs[right];

            // Shrink window while budget is exceeded
            while (sum > budget && left <= right) {
                sum -= costs[left];
                left++;
            }

            int length = right - left + 1;

            if (length > maxLength) {
                maxLength = length;
                bestStart = left;
            }
        }

        return new int[]{maxLength, bestStart};
    }
}
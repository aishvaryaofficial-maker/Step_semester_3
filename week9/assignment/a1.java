package week9.assignment;
import java.util.*;

public class a1 {

    public static List<Long> footfallReport(int[] visitors, int[][] queries) {

        int n = visitors.length;

        // Prefix sum
        long[] prefix = new long[n];
        prefix[0] = visitors[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + visitors[i];
        }

        List<Long> result = new ArrayList<>();

        for (int[] query : queries) {
            int start = query[0];
            int end = query[1];

            long sum;

            if (start == 0) {
                sum = prefix[end];
            } else {
                sum = prefix[end] - prefix[start - 1];
            }

            result.add(sum);
        }

        return result;
    }
}
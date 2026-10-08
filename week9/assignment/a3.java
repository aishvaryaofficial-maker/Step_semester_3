package week9.assignment;

import java.util.*;

public class a3{

    public static long countPeriods(int[] transactions, long k) {

        HashMap<Long, Integer> map = new HashMap<>();

        // Prefix sum 0 occurs once before starting
        map.put(0L, 1);

        long prefixSum = 0;
        long count = 0;

        for (int value : transactions) {

            prefixSum += value;

            // We need an earlier prefixSum = current - k
            long required = prefixSum - k;

            if (map.containsKey(required)) {
                count += map.get(required);
            }

            // Store current prefix sum
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }
}
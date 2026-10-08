package week9.assignment;

import java.util.*;

public class a5 {

    public static List<Integer> auditRoute(int[][] grid) {

        List<Integer> result = new ArrayList<>();

        int top = 0;
        int bottom = grid.length - 1;

        int left = 0;
        int right = grid[0].length - 1;

        while (top <= bottom && left <= right) {

            for (int j = left; j <= right; j++) {
                result.add(grid[top][j]);
            }
            top++;

            for (int i = top; i <= bottom; i++) {
                result.add(grid[i][right]);
            }
            right--;

            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    result.add(grid[bottom][j]);
                }
                bottom--;
            }

            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(grid[i][left]);
                }
                left++;
            }
        }

        return result;
    }
}
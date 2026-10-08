package week9.assignment;

public class a4{

    public static int countInBand(int[] scores, int low, int high) {

        int first = lowerBound(scores, low);
        int afterLast = upperBound(scores, high);

        return afterLast - first;
    }

   
    private static int lowerBound(int[] scores, int target) {

        int left = 0;
        int right = scores.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (scores[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    // First index where scores[index] > target
    private static int upperBound(int[] scores, int target) {

        int left = 0;
        int right = scores.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (scores[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
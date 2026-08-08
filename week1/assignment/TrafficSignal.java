import java.util.Scanner;

public class TrafficSignal {

    public static void findLongestStreak(String signalLog) {

        char longestColor = signalLog.charAt(0);
        int longestCount = 1;

        char currentColor = signalLog.charAt(0);
        int currentCount = 1;

        for (int i = 1; i < signalLog.length(); i++) {

            if (signalLog.charAt(i) == currentColor) {
                currentCount++;
            } else {
                currentColor = signalLog.charAt(i);
                currentCount = 1;
            }

            if (currentCount > longestCount) {
                longestCount = currentCount;
                longestColor = currentColor;
            }
        }

        System.out.println("Longest Streak: '" + longestColor +
                "' repeated " + longestCount + " times");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter signal log: ");
        String signalLog = sc.nextLine();

        findLongestStreak(signalLog);

        sc.close();
    }
}
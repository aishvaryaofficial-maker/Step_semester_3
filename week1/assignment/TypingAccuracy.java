import java.util.Scanner;

public class TypingAccuracy {

    public static void checkTypingAccuracy(String original, String typed) {

        int match = 0;
        int firstMismatch = -1;

        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                match++;
            } else if (firstMismatch == -1) {
                firstMismatch = i;
            }
        }

        double accuracy = (double) match / original.length() * 100;

        System.out.println("Matched: " + match + "/" + original.length());
        System.out.printf("Accuracy: %.2f%%\n", accuracy);

        if (firstMismatch == -1) {
            System.out.println("No Mismatches");
        } else {
            System.out.println("First Mismatch at Position: " + (firstMismatch + 1));
            System.out.println("Original Character: " + original.charAt(firstMismatch));
            System.out.println("Typed Character: " + typed.charAt(firstMismatch));
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter original text: ");
        String original = sc.nextLine();

        System.out.print("Enter typed text: ");
        String typed = sc.nextLine();

        if (original.length() != typed.length()) {
            System.out.println("Both strings must be of equal length.");
        } else {
            checkTypingAccuracy(original, typed);
        }

        sc.close();
    }
}
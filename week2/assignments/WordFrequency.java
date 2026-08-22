import java.util.*;

class WordFrequency {

    void printFilteredWordFrequency(String feedback) {

        feedback = feedback.toLowerCase();
        feedback = feedback.replace(".", "");
        feedback = feedback.replace(",", "");

        String[] words = feedback.split("\\s+");

        String[] stopWords = { "the", "was", "and", "a", "is", "of", "in" };

        HashMap<String, Integer> count = new HashMap<>();

        for (String word : words) {

            boolean stopWord = false;

            for (String stop : stopWords) {
                if (word.equals(stop)) {
                    stopWord = true;
                    break;
                }
            }

            if (!stopWord) {
                count.put(word, count.getOrDefault(word, 0) + 1);
            }
        }

        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(count.entrySet());

        list.sort((a, b) -> b.getValue() - a.getValue());

        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter feedback: ");
        String feedback = sc.nextLine();

        WordFrequency obj = new WordFrequency();
        obj.printFilteredWordFrequency(feedback);
    }
}
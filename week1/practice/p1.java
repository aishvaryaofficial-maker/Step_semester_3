import java.util.*;

class RockPaperScissors {
    static String playRound(String p, String c) {
        if (p.equals(c))
            return "Draw";

        if ((p.equals("Rock") && c.equals("Scissors")) ||
                (p.equals("Paper") && c.equals("Rock")) ||
                (p.equals("Scissors") && c.equals("Paper")))
            return "Player Wins";

        return "Computer Wins";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] moves = { "Rock", "Paper", "Scissors" };

        int win = 0, lose = 0, draw = 0;

        for (int i = 1; i <= 5; i++) {
            System.out.print("Enter Rock, Paper or Scissors: ");
            String player = sc.next();

            String computer = moves[(int) (Math.random() * 3)];
            String result = playRound(player, computer);

            System.out.println("Computer: " + computer);
            System.out.println(result);

            if (result.equals("Player Wins"))
                win++;
            else if (result.equals("Computer Wins"))
                lose++;
            else
                draw++;
        }

        System.out.println("\nWins: " + win);
        System.out.println("Losses: " + lose);
        System.out.println("Draws: " + draw);
        System.out.println("Win %: " + (win * 100.0 / 5));
    }
}
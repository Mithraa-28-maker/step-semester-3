import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {

    static String playRound(String playerMove, String computerMove) {

        if (playerMove.equals(computerMove)) {
            return "Draw";
        }

        if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
            (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
            (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
            return "Player Wins";
        }

        return "Computer Wins";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        String[] moves = {"Rock", "Paper", "Scissors"};

        String[] playerMoves = new String[5];
        String[] computerMoves = new String[5];
        String[] results = new String[5];

        int wins = 0, losses = 0, draws = 0;

        for (int i = 0; i < 5; i++) {

            System.out.print("Round " + (i + 1) + " - Enter Rock, Paper or Scissors: ");
            String playerMove = sc.next();

            String computerMove = moves[random.nextInt(3)];

            String result = playRound(playerMove, computerMove);

            playerMoves[i] = playerMove;
            computerMoves[i] = computerMove;
            results[i] = result;

            if (result.equals("Player Wins"))
                wins++;
            else if (result.equals("Computer Wins"))
                losses++;
            else
                draws++;
        }

        System.out.println("\nFinal Summary");
        System.out.println("-----------------------------------------------");
        System.out.printf("%-8s %-15s %-15s %-15s%n",
                "Round", "Player Move", "Computer Move", "Result");
        System.out.println("-----------------------------------------------");

        for (int i = 0; i < 5; i++) {
            System.out.printf("%-8d %-15s %-15s %-15s%n",
                    i + 1, playerMoves[i], computerMoves[i], results[i]);
        }

        double winPercentage = (wins / 5.0) * 100;

        System.out.println("-----------------------------------------------");
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("Draws: " + draws);
        System.out.printf("Win %% = %.1f%%%n", winPercentage);

        sc.close();
    }
}
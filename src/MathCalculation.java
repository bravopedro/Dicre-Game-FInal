public class MathCalculation {
    public static double calculateWinner(int numRolls, double score, int playerOneRoll, int playerTwoRoll) {
        double difference;
        double percentDifference = 0;

        //the calculation for how much a player will win
        if (playerOneRoll > playerTwoRoll) {
            difference = Math.abs(playerOneRoll - playerTwoRoll);
            percentDifference = (difference / playerOneRoll) * score;
        } else {
            difference = Math.abs(playerTwoRoll - playerOneRoll);
            percentDifference = (difference / playerTwoRoll) * score;
        }

        return percentDifference;    //returns the math calculation
    }

    //this method displays how much of Player 2's balance that Player 1 has won
    public static void printerP1Win(double winAmount, double totalScore, String player1Name, String player2Name) throws InterruptedException {
        Thread.sleep(800);
        System.out.printf("%s you have won $%.2f of %s's balance of $",player1Name,winAmount,player2Name);
        System.out.printf("%.2f", totalScore);
        System.out.println();
    }

    //this method displays how much of Player 1's balance that Player 2 has won
    public static void printerP2Win(double winAmount, double totalScore,String player1name, String player2name) throws InterruptedException {
        Thread.sleep(800);
        System.out.printf("%s you have won $%.2f of %s's balance of $",player2name,winAmount,player1name);
        System.out.printf("%.2f", totalScore);
        System.out.println();
    }

}

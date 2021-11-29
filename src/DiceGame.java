
import java.util.Scanner;
public class DiceGame {
    public static void main(String[] args) {
        Scanner scnr1 = new Scanner(System.in);
        gameDirections();
        String toRollOrNot;
        int player = 1;
        int numRolls;               //declaration of variables for the main method
        int bet;
        boolean bigEnough = false;
        int playerNumber = 1;
        double winNumberp1;
        double winNumberp2;
        double playerOneTotal = 0;
        double playerTwoTotal = 0;


        System.out.println("Reminder both players must have the same bet size with a minimum of 10 dollars");
        System.out.print("How much would you like to bet? ");
        bet = scnr1.nextInt();  // take user input of the bet size

        if (bet >= 10) {
            bigEnough = true;     // check to see if bet is large enough to play
            playerOneTotal = bet;
            playerTwoTotal = bet;

        } else {
            while (!bigEnough) {        // if bet isn't large enough prompt the user until your bet is large enough
                System.out.println("Your bet isn't big enough try again");
                System.out.println("Reminder you must have the same bet with a minimum of 10 dollars");
                bet = scnr1.nextInt();
                if (bet >= 10) {
                    bigEnough = true;
                }
            }
        }

        numRolls = howManyRolls();  //call method how many rolls to get user input of how many rolls per game

        System.out.print("In order to roll the dice each time, type the word 'roll' and press the enter key ");


        int roundNum = 1;

        while (numRolls > 0) {
            toRollOrNot = scnr1.next();
            int rollP1 = dice();    // get the actual input from the dice machine to print later.
            System.out.println();   // Player one's roll
            System.out.printf("ROUND %d has commenced!!!", roundNum);
            System.out.println();
            System.out.printf("Player %d you have rolled a %d\n", player, rollP1);

            if (player == 1) {
                player = 2;
            } else {
                player = 1;
            }

            int rollP2 = dice();            //player twos dice roll
            System.out.printf("Player %d you have rolled a %d\n", player, rollP2);


            winNumberp1 = calculateWinner(player, numRolls, bet, rollP1, rollP2);
            winNumberp2 = calculateWinner(player, numRolls, bet, rollP1, rollP2);       // calculate the winner of each round

            if (playerNumber == 1) {
                playerNumber = 2;
            } else {
                playerNumber = 1;
            }


            if (player == 1) {
                System.out.printf("Player %d you have won %.2f of player 2's initial bet of %d", player, winNumberp1, bet);
                System.out.println();
                playerOneTotal = playerOneTotal + winNumberp1;
                playerTwoTotal = playerTwoTotal - winNumberp1;
                player = 2;
            } else {                  //print out amount won between the two players
                System.out.printf("Player %d you have won %.2f of player 1's initial bet of %d", player, winNumberp1, bet);
                System.out.println();
                playerTwoTotal = playerTwoTotal + winNumberp2;
                playerOneTotal = playerOneTotal - winNumberp2;
                player = 1;
            }

            roundNum++;
            numRolls--;
        }

        System.out.println();
        System.out.println("THIS GAME IS NOW OVER");
        System.out.printf("Player 1, you have a total balance of: %.2f\n", playerOneTotal);
        System.out.printf("Player 2, you have a total balance of %.2f\n", playerTwoTotal);

        if (playerOneTotal > playerTwoTotal) {
            System.out.println("Congrats Player 1, you are the winner!!");          //print out winners and end the game
        } else if (playerTwoTotal > playerOneTotal) {
            System.out.println("Congrats Player 2, you are the winner!!");
        } else {
            System.out.println("You have both tied");
        }

    }


    public static void gameDirections() {
        System.out.println("Welcome to CSC-111 Gambling final!!!!!");
        System.out.println();
        System.out.println(" This is a dice game in which each player places a minimum bet of 10 dollars to play." +
                " Both players play against each other meaning that there is no house to cover the bets." +
                " Both players roll one dice. The player with the higher dice roll wins the percent difference between their roll and the" +
                " other player's rolls  in the player's initial buy-in. " +
                "In a nutshell the other person wins what the other loses. WARNING YOU CAN LOOSE MORE MONEY THAN YOU ACTUALLY PUT IN THE GAME!");
        System.out.println();
        System.out.println("Example: \n" +
                "\n" +
                "Both Players initially bet 200 dollars each and agree to roll 2 times.\n" +
                "Player 1 rolls a 6\n" +
                "Player 2 rolls a 3\n" +
                "\n" +
                "Round one is complete. The percent difference between Player 1’s roll and player 2’s roll is 50%\n" +
                "Player 1 wins 100 dollars \n" +
                "Player 2 losses 100 dollars \n" +
                "Player 1 balance 300 dollars player 2 balance 100\n" +
                "\n" +
                "Player 1 type roll to roll.\n" +
                "Player 1 you have rolled a 2\n" +
                "Player 2 type roll to roll \n" +
                "Player 2 you have rolled a 3\n" +
                "\n" +
                "Round 2 is complete The percent difference between Player 2’s roll and player 1’s roll is 33.33%\n" +
                "Player 1 loses.  $99.99\t\tPlayer 1’s Balance is 200.01\n" +
                "Player 2 wins \t  $99.99\t\tPlayer 2’s Balance is 199.99\n" +
                "\n");

    }

    public static int dice() {
        double theDice = Math.random() * (6 - 1) + 1;
        return (int) theDice;
    }


    public static int howManyRolls() {
        Scanner scnr3 = new Scanner(System.in);
        int numRolls;
        System.out.print("How many times will you be rolling this game? ");
        numRolls = scnr3.nextInt();
        return numRolls;
    }


    public static double calculateWinner(int player, int numRolls, int bet, int playerOneRoll, int playerTwoRoll) {
        double winningAmount = 0;
        double[] sum1 = new double[numRolls];                // create an array to store the sum value of a won
        double[] sum2 = new double[numRolls];
        int i;
        double difference;
        double percentDifference = 0;

        for (i = 0; i < sum1.length; i++) {
            sum1[i] = playerOneRoll;
            sum2[i] = playerTwoRoll;

            if (sum1[i] >= sum2[i]) {
                difference = sum1[i] - sum2[i];
                percentDifference = (difference / sum1[i]) * bet;
                break;

            } else {
                difference = sum2[i] - sum1[i];
                percentDifference = (difference / sum2[i]) * bet;
                break;

            }
        }

        return percentDifference;

    }
}

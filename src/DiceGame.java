/*
 *
 * This game was initially created after seeing my roommate win a lot of money playing poker on the weekends.
 * I never cared to learn how to play poker but wanted to gamble with him. So the weekend before my first
 * computer science examination I decided to make a gambling game to help me study. Although this
 * version of our game was archaic, it served its purpose. When Presented with the opportunity to "make our own"
 * final project I immediately thought about greatly improving this game and brought the idea up to my partner Akhil we decided
 * that it would be a good fit. This game has come a long way from its origins as it was originally just two random number generators
 * and an incorrect output of the percentage difference between the two rolls.
 * Akhil and I have poured hours into this project and believe that it is exactly what we had in mind when initially deciding to make the game.
 * We hope you enjoy the CSC-111 gambling final and hope you don't lose too much money!!!
 * Thank you for your time,
 * -Pedro Bravo '24
 * -Akhil Patel '24
 *
 * */

/*
 *
 * This game was initially created after seeing my roommate win a lot of money playing poker on the weekends.
 * I never cared to learn how to play poker but wanted to gamble with him. So the weekend before my first
 * computer science examination I decided to make a gambling game to help me study. Although this
 * version of our game was archaic, it served its purpose. When Presented with the opportunity to "make our own"
 * final project I immediately thought about greatly improving this game and brought the idea up to my partner Akhil we decided
 * that it would be a good fit. This game has come a long way from its origins as it was originally just two random number generators
 * and an incorrect output of the percentage difference between the two rolls.
 * Akhil and I have poured hours into this project and believe that it is exactly what we had in mind when initially deciding to make the game.
 * We hope you enjoy the CSC-111 gambling final and hope you don't lose too much money!!!
 * Thank you for your time,
 * -Pedro Bravo '24
 * -Akhil Patel '24
 *
 * */

import java.util.Scanner;
public class DiceGame {
    public static void main(String[] args) throws InterruptedException {
        getDirections();
        //an abstract data type from the class 'Dice' that will toss the die.
        Dice d1 = new Dice();

        //an abstract data type from the class 'MathCalculation' that will calculate the amount a user wins each turn
        MathCalculation calculator = new MathCalculation();

        Scanner scnr1 = new Scanner(System.in);
        String toRollOrNot;            //whether a user rolls
        int player = 1;                //default player
        int numRolls;//number of turns the user would like to play the game
        int diceSize;
        long bet;                      //the amount of money the user would like to bet
        boolean bigEnough = false;     //whether the amount the user decides to bet exceeds the minimum
        double winNumberP1 = 0;        //the amount won for a turn for Player 1
        double winNumberP2 = 0;        //the amount won for a turn for Player 2
        double playerOneTotal = 0;     //total amount won for Player 1
        double playerTwoTotal = 0;     //total amount won for Player 2
        String player1Name;
        String player2Name;

        //asks the users to insert their names
        System.out.println("Player 1 Type Your name to play");
        player1Name = scnr1.nextLine();
        System.out.println("Player 2 Type your name to play");
        player2Name = scnr1.nextLine();

        //asks the user how much they would like to bet
        System.out.println("Reminder: both players must have the same bet size with a minimum of 10 dollars");
        System.out.println();
        Thread.sleep(1000);
        System.out.print("How many sides would you like your dice to have? ");
        diceSize = scnr1.nextInt();
        System.out.print("How much would you like to bet? ");
        bet = scnr1.nextLong();  // take user input of the bet size

        if (bet >= 10) {
            bigEnough = true;     // check to see if bet is large enough to play

            //initializes the balance for both players as the starting bet amount
            playerOneTotal = bet;
            playerTwoTotal = bet;

        } else {

            // if bet isn't large enough, the program will; prompt the user until their bet is large enough
            while (!bigEnough) {
                System.out.println("Your bet isn't big enough try again");
                System.out.println("Reminder: you must have the same bet with a minimum of 10 dollars");
                bet = scnr1.nextLong();
                if (bet >= 10) {
                    bigEnough = true;
                }
            }
        }

        numRolls = howManyRolls();  //call method how many rolls to get user input of how many rolls per game
        double [] userTurnWins = new double[numRolls];  //declares an array to store how much is won for a given turn
        int [] turnWinner = new int[numRolls];    //declares an array to store which player won for a  given turn
        int i;

        System.out.print("In order to roll the dice each time, type the word 'roll' and press the enter key ");

        int roundNum = 1;   //what round will commence

        //this for loop constitutes each turn
        for (i = 0; i < numRolls; i++) {
            toRollOrNot = scnr1.next();
            System.out.println();
            System.out.printf("ROUND %d has commenced!!!", roundNum);
            System.out.println();
            d1.toss(diceSize);   //tosses the die
            int rollP1 = d1.getValue();     //gets the dice roll number
            Thread.sleep(700);
            if (player == 1) {
                System.out.printf("%s you have rolled a %d\n", player1Name, rollP1);
            }

            //switch players
            if (player == 1) {
                player = 2;
            } else {
                player = 1;
            }

            d1.toss(diceSize);    //tosses the die
            int rollP2 = d1.getValue();    //gets the dice roll number
            Thread.sleep(700);

            if (player == 2){
                System.out.printf("%s you have rolled a %d\n", player2Name, rollP2);
            }


            //switch players
            if (player == 1) {
                player = 2;
            } else {
                player = 1;
            }

            // calculate the winner of each round
            if (rollP1 > rollP2) {                //if Player 1's roll is larger
                winNumberP1 = calculator.calculateWinner(numRolls, playerTwoTotal, rollP1, rollP2); //amount won for a given round
                calculator.printerP1Win(winNumberP1, playerTwoTotal,player1Name,player2Name);  //prints which player won and balances for a given round
                userTurnWins[i] = winNumberP1;

                turnWinner[i] = 1;

                //updates totals after a turn and increases the round number
                playerOneTotal += winNumberP1;
                playerTwoTotal -= winNumberP1;
                roundNum++;

            } else if (rollP2 > rollP1) {         //if Player 1's roll is larger
                winNumberP2 = calculator.calculateWinner(numRolls, playerOneTotal, rollP1, rollP2); //amount won for a given round
                calculator.printerP2Win(winNumberP2, playerOneTotal,player1Name,player2Name);  //prints which player won and balances for a given round
                userTurnWins[i] = winNumberP2;
                turnWinner[i] = 2;

                //updates totals after a turn and increases the round number
                playerTwoTotal += winNumberP2;
                playerOneTotal -= winNumberP2;
                roundNum++;

            } else {                             //if there is a tie
                System.out.print("This round was a tie");
                System.out.println();
                roundNum++;
                userTurnWins[i] = 0;
                turnWinner[i] = 0;
            }
        }

        //the code that is printed after the game has finished, outputs the winners and final balances
        System.out.println();
        Thread.sleep(800);
        System.out.println("THIS GAME IS NOW OVER");
        System.out.printf("%s, you have a total balance of: $%.2f\n",player1Name,playerOneTotal);
        System.out.printf("%s, you have a total balance of: $%.2f\n",player2Name, playerTwoTotal);

        if (playerOneTotal > playerTwoTotal) {
            Thread.sleep(500);
            System.out.printf("Congrats  %s, you are the winner!!",player1Name);
            System.out.println();
        }
        else if (playerTwoTotal > playerOneTotal) {
            Thread.sleep(500);
            System.out.printf("Congrats %s, you are the winner!!",player2Name);
            System.out.println();
        }
        else {
            Thread.sleep(500);
            System.out.println("You have both tied");
            System.out.println();
        }

        //outputs a chart that displays the player who won and their winnings for each round
        Thread.sleep(800);
        System.out.println("    TURN BY TURN CHART:        ");
        System.out.println("Round     Player    Winnings");

        int turnNumber = 1;
        for (i = 0; i < numRolls; i++) {
            System.out.print(turnNumber + "           " + turnWinner [i] + "        $");
            System.out.printf("%.2f", userTurnWins [i]);
            System.out.println();
            turnNumber++;

        }

    }

    //this method determines how many rounds a user would like to play the game
    public static int howManyRolls() {
        Scanner scnr3 = new Scanner(System.in);
        int numRolls;
        System.out.print("How many times will you be rolling this game? ");
        numRolls = scnr3.nextInt();
        return numRolls;
    }
    public static void getDirections(){
        System.out.println("Welcome to CSC-111 Gambling final!!!!!");
        System.out.println();
        System.out.println(" This is a  2 player dice game in which each player places a minimum bet of 10 dollars to play and a maximum bet of 9,223,372,036,854,775,807." +
                " Both players play against each other meaning that there is no house to cover the bets." +
                " Both players roll one dice. The player with the higher dice roll wins the percent difference between their roll and the" +
                " other player's rolls in the player's initial buy-in. " +
                "In a nutshell the other person wins what the other loses.");
        System.out.println();
        System.out.println("Example: \n" +
                "\n" +
                "Both Players initially bet $200 each and agree to roll 2 times.\n" +
                "Player 1 rolls a 6\n" +
                "Player 2 rolls a 3\n" +
                "\n" +
                "Round one is complete. The percent difference between Player 1’s roll and player 2’s roll is 50%\n" +
                "Player 1 wins $100 \n" +
                "Player 2 loses $100 \n" +
                "Player 1's balance is $300 and Player 2's balance is $100\n" +
                "\n" +
                "Type 'roll' to roll.\n" +
                "Player 1 you have rolled a 2\n" +
                "Player 2 you have rolled a 3\n" +
                "\n" +
                "Round 2 is complete. The percent difference between Player 2’s roll and player 1’s roll is 33.33%\n" +
                "Player 1 loses    $99.99\t\tPlayer 1’s Balance is $200.01\n" +
                "Player 2 wins \t  $99.99\t\tPlayer 2’s Balance is $199.99\n" +
                "\n");
    }

}
//This class simulates the roll of the die

public class Dice {
    private int diceSize;  //number of sides on the die
    private int roll;      //what number is rolled

    //constructor method
    public Dice() {
        this.diceSize = 6;
        this.roll = 1;
    }

    //setter method
    public void setDiceSides (int diceSize) {
        this.diceSize = diceSize;
    }

    //getter method
    public int getValue() {
        return roll;
    }

    //actual dice roll
    public void toss(int diceSize) {
        double theDice = Math.random() * (diceSize - 1) + 1;
        this.roll = (int) theDice;
    }

}

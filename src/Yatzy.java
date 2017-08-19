import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Yatzy {

    Scanner scan = new Scanner(System.in);
    Random rnd = new Random();

    private Score playerOne = new Score("Maja");
    private Score playerTwo = new Score("Eva");
    private Score playerThree = new Score("Kavve");
    private Score playerFour = new Score("Saga");

    //TODO: Låt spelare ange antal spelare
    private int amountOfPlayers = 2;

    //TODO: Räkna ut poängen rätt
    private String updateScore(int playerNumber, int[] dices){

        boolean correctCommand = false;
        String message = "";
        int numberChosen;
        int score = 0;
        String command = "";

        System.out.println("How do you want to save your score?");

        while(!correctCommand){
            command = scan.nextLine();

            switch (command) {
            /* Upper section */
                case "ones":
                    score = upperSectionScore(dices, 1);
                    correctCommand = true;
                    break;
                case "twos":
                    score = upperSectionScore(dices, 2);
                    correctCommand = true;
                    break;
                case "threes":
                    score = upperSectionScore(dices, 3);
                    correctCommand = true;
                    break;
                case "fours":
                    score = upperSectionScore(dices, 4);
                    correctCommand = true;
                    break;
                case "fives":
                    score = upperSectionScore(dices, 5);
                    correctCommand = true;
                    break;
                case "sixes":
                    score = upperSectionScore(dices, 6);
                    correctCommand = true;
                    break;

            /* Lower section */
                case "pair":
                    System.out.println("Please specify which number you got pair");
                    numberChosen = scan.nextInt();
                    scan.nextLine();
                    score = pairTest(dices, numberChosen);
                    correctCommand = true;
                    break;
                case "twopairs":
                    score = twoPairTest(dices);
                    correctCommand = true;
                    break;
                case "triplets":
                    System.out.println("Please specify which number you got triplets");
                    numberChosen = scan.nextInt();
                    scan.nextLine();
                    score = tripletTest(dices, numberChosen);
                    correctCommand = true;
                    break;
                case "tetrad":
                    System.out.println("Please specify which number you got pair");
                    numberChosen = scan.nextInt();
                    scan.nextLine();
                    score = tetradTest(dices, numberChosen);
                    correctCommand = true;
                    break;
                case "fullhouse":
                    score = fullHouseTest(dices);
                    correctCommand = true;
                    break;
                case "smallstraight":
                    score = smallStraightTest(dices);
                    correctCommand = true;
                    break;
                case "largestraight":
                    score = largaStraightTest(dices);
                    correctCommand = true;
                    break;
                case "change":
                    score = change(dices);
                    correctCommand = true;
                    break;
                case "yatzy":
                    score = yatzyTest(dices);
                    correctCommand = true;
                    break;
                default:
                    System.out.println("There is no such command, try again!");
                    break;
            }
        }

        switch (playerNumber){
            case 1:
                message = playerOne.updateScore(command, score);
                break;
            case 2:
                message = playerTwo.updateScore(command, score);
                break;
            case 3:
                message = playerThree.updateScore(command, score);
                break;
            case 4:
                message = playerFour.updateScore(command, score);
                break;
        }

        return message;
    }

    int upperSectionScore(int[] dices, int number){
        int counter = 0;

        for(int i = 0; i < dices.length; i++) {
            if (dices[i] == number) {
                counter++;
            }
        }
        return counter*number;
    }

    int pairTest(int[] dices, int pairNumber){

        for(int i = 1; i < dices.length; i++){
            if(dices[i] == pairNumber && dices[i] == dices[i-1]){
                return dices[i]*2;
            }
        }
        return 0;
    }

    int twoPairTest(int[] dices){
        int firstPairValue = 0;
        int secondPairValue = 0;
        boolean foundPair = false;
        boolean twoPair = false;

        for(int i = 1; i < dices.length; i++){
            if(dices[i] == dices[i-1]){
                if(!foundPair){
                    firstPairValue = dices[i]*2;
                    foundPair = true;
                }else if(!(firstPairValue == dices[i]*2)){
                    secondPairValue = dices[i]*2;
                    twoPair = true;
                }
            }
        }

        if(twoPair){
            return firstPairValue + secondPairValue;
        }
        return 0;
    }

    int tripletTest(int[] dices, int tripletNumber){

        for(int i = 2; i < dices.length; i++){
            if(dices[i] == tripletNumber && dices[i] == dices[i-1] && dices[i] == dices[i-2]){
                return dices[1]*3;
            }
        }
        return 0;
    }

    int tetradTest(int[] dices, int tetradNumber){

        for(int i = 3; i < dices.length; i++){
            if(dices[i] == tetradNumber && dices[i] == dices[i-1] && dices[i] == dices[i-2] && dices[i] ==dices[i-3]){
                return dices[1]*4;
            }
        }
        return 0;
    }

    int fullHouseTest(int[] dices){

        int tripletScore = tripletTest(dices, dices[0]);
        int pairScore = pairTest(dices, dices[4]);

        if(tripletScore == 0){
            tripletScore = tripletTest(dices, dices[4]);
            pairScore = pairTest(dices, dices[4]);
        }
        if(tripletScore != 0 && pairScore != 0 && tripletScore/3 != pairScore/2){
            return tripletScore + pairScore;
        }
        return 0;
    }

    boolean straightTest(int[] dices, int startNumber){

        for(int i = 0; i < dices.length; i++){
            if(!(dices[i] == startNumber)){
                return false;
            }
            startNumber++;
        }
        return true;
    }

    int smallStraightTest(int[] dices){

        if(straightTest(dices, 1)){
            return 15;
        }else{
            return 0;
        }
    }

    int largaStraightTest(int[] dices){

        if(straightTest(dices, 2)){
            return 20;
        }
        return 0;
    }

    int change(int[] dices){
        int score = 0;
        for(int i = 0; i < dices.length; i++){
            score += dices[i];
        }
        return score;
    }

    int yatzyTest(int[] dices){

        for(int i = 0; i < dices.length; i++){
            if(!(dices[0] == dices[i])){
                return 0;
            }
        }

        return 50;
    }

    private void oneRound(int playerNumber){

        int diceOne = rnd.nextInt(6) + 1;
        int diceTwo = rnd.nextInt(6) + 1;
        int diceThree = rnd.nextInt(6) + 1;
        int diceFour = rnd.nextInt(6) + 1;
        int diceFive = rnd.nextInt(6) + 1;

        for (int i = 0; i < 2; i++) {

            System.out.println("Your dices result:"
                    + "\nDice 1: " + diceOne
                    + "\nDice 2: " + diceTwo
                    + "\nDice 3: " + diceThree
                    + "\nDice 4: " + diceFour
                    + "\nDice 5: " + diceFive
                    + "\n \nPress the dice number you want to save\n");

            String dices = scan.nextLine();

            //TODO: Går det att få bort if-lösning? Loop möjligtvis?
            if(!dices.contains("1")){
                diceOne = rnd.nextInt(6) + 1;
            }
            if(!dices.contains("2")){
                diceTwo = rnd.nextInt(6) + 1;
            }
            if(!dices.contains("3")){
                diceThree = rnd.nextInt(6) + 1;
            }
            if(!dices.contains("4")){
                diceFour = rnd.nextInt(6) + 1;
            }
            if(!dices.contains("5")){
                diceFive = rnd.nextInt(6) + 1;
            }
        }

        System.out.println("\nYour result: "
                + "\nDice 1: " + diceOne
                + "\nDice 2: " + diceTwo
                + "\nDice 3: " + diceThree
                + "\nDice 4: " + diceFour
                + "\nDice 5: " + diceFive);

        int [] dices = new int[5];
        dices[0] = diceOne;
        dices[1] = diceTwo;
        dices[2] = diceThree;
        dices[3] = diceFour;
        dices[4] = diceFive;

        Arrays.sort(dices);

        System.out.println(updateScore(playerNumber, dices));
    }

    private void play(){

//        System.out.println("How many will play today? (Min 2, max 4)");
//        amountOfPlayers = scan.nextInt();
        int player = 1;
        for(int i = 1; i < amountOfPlayers + 1; i++){

            System.out.println("\nPlayer " + i + "\n");
            System.out.println("Press enter to roll the dices");
            scan.nextLine();

            oneRound(player);

            player ++;
        }

        System.out.println("\nPlayer one: " + playerOne.totalScore + "\n" + "Player two: " + playerTwo.totalScore);

    }

    public static void main(String args[]) {

        Yatzy yatzy = new Yatzy();
        yatzy.play();

    }
}

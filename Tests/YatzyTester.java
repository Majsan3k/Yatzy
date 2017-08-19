import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class YatzyTester {

    Yatzy yatzy = new Yatzy();

    int [] dicesPair = new int[5];
    int [] dicesTwoPair = new int[5];
    int [] dicesTriplets = new int[5];
    int [] dicesTetrad = new int[5];
    int [] dicesSmallStraight = new int[5];
    int [] dicesLargeStraight = new int[5];
    int [] dicesFullHouse = new int[5];
    int [] dicesYatzy = new int[5];

    @Before
    public void setUp() throws Exception {
        dicesPair[0] = 2;
        dicesPair[1] = 1;
        dicesPair[2] = 4;
        dicesPair[3] = 4;
        dicesPair[4] = 5;

        dicesTwoPair = dicesPair.clone();
        dicesTwoPair[1] = 2;

        dicesTriplets = dicesTwoPair.clone();
        dicesTriplets[2] = 2;

        dicesTetrad = dicesTriplets.clone();
        dicesTetrad[3] = 2;

        for (int i = 0; i < 5; i++){
           dicesSmallStraight[i] = i + 1;
        }

        int placeInStraight = 2;
        for (int i = 0; i < 5; i++){
            dicesLargeStraight[i] = placeInStraight;
            placeInStraight++;
        }

        dicesFullHouse = dicesTriplets.clone();
        dicesFullHouse[3] = 5;

        dicesYatzy = dicesTetrad.clone();
        dicesYatzy[4] = 2;

        System.out.println(writeArray(dicesPair));
        System.out.println(writeArray(dicesTwoPair));
        System.out.println(writeArray(dicesTriplets));
        System.out.println(writeArray(dicesTetrad));
        System.out.println(writeArray(dicesSmallStraight));
        System.out.println(writeArray(dicesLargeStraight));
        System.out.println(writeArray(dicesFullHouse));
        System.out.println(writeArray(dicesYatzy));
    }

    public String writeArray(int[] dices){

        String dicesString;

        return dicesString = "" + dices[0] + " " + dices[1] + " " + dices[2] + " " + dices[3] + " " + dices[4];

    }

    @Test
    public void testUpperSectionScore(){
        assertEquals(6, yatzy.upperSectionScore(dicesTriplets,2));
        assertEquals(8, yatzy.upperSectionScore(dicesTwoPair, 4));
    }

    @Test
    public void testPair() {
        assertEquals(8, yatzy.pairTest(dicesPair, 4));
    }

    @Test
    public void testTwoPair(){
        assertEquals(12, yatzy.twoPairTest(dicesTwoPair));
    }

    @Test
    public void testTriplets() {
        assertEquals(6, yatzy.tripletTest(dicesTriplets, 2));
    }

    @Test
    public void testTetrad() {
        assertEquals(8, yatzy.tetradTest(dicesTetrad, 2));
    }

    @Test
    public void testSmallStraight() {
        assertEquals(15, yatzy.smallStraightTest(dicesSmallStraight));
    }

    @Test
    public void testLargeStraight() {
        assertEquals(20, yatzy.largaStraightTest(dicesLargeStraight));
    }

    @Test
    public void testFullHouse() {
        assertEquals(16, yatzy.fullHouseTest(dicesFullHouse));
    }

    @Test
    public void testChange(){
        assertEquals(16, yatzy.change(dicesPair));
        assertEquals(17, yatzy.change(dicesTwoPair));
        assertEquals(15, yatzy.change(dicesTriplets));
        assertEquals(13, yatzy.change(dicesTetrad));
        assertEquals(15, yatzy.change(dicesSmallStraight));
        assertEquals(20, yatzy.change(dicesLargeStraight));
        assertEquals(16, yatzy.change(dicesFullHouse));
        assertEquals(10, yatzy.change(dicesYatzy));
    }

    @Test
    public void testYatzy() {
        assertEquals(50, yatzy.yatzyTest(dicesYatzy));
    }






}


public class Score {

    public String name;
    public int totalScore;
    public int scoreUpperSection;

    public int[] scoreBoard = new int[16];


    public Score(String name){
        this.name = name;
    }

    public String updateScore(String command, int score){

        switch (command.toLowerCase()) {

            /* Upper section */
            case "ones":
                return setUpperSectionScore(1, score);
            case "twos":
                return setUpperSectionScore(2, score);
            case "threes":
                return setUpperSectionScore(3, score);
            case "fours":
                return setUpperSectionScore(4, score);
            case "fives":
                return setUpperSectionScore(5, score);
            case "sixes":
                return setUpperSectionScore(6, score);

            /* Lower section */
            case "pair":
                return setLowerSectionScore(7, score);
            case "twopairs":
                return setLowerSectionScore(8, score);
            case "triplets":
                return setLowerSectionScore(9, score);
            case "tetrad":
                return setLowerSectionScore(10, score);
            case "fullhouse":
                return setLowerSectionScore(11, score);
            case "smallstraight":
                return setLowerSectionScore(12, score);
            case "largestraight":
                return setLowerSectionScore(13, score);
            case "change":
                return setLowerSectionScore(14, score);
            case "yatzy":
                return setLowerSectionScore(15, score);
        }

        return "Command doesn't exist";
    }

    public String setUpperSectionScore(int number, int score) {

        if(!(scoreBoard[number-1] == 0)){
            return "You already used that one";
        }else{
            scoreBoard[number-1] = score;
            scoreUpperSection += score;
            totalScore += score;
            return "Your score " + score + " is saved";
        }
    }

    public String setLowerSectionScore(int command, int score){

        if(!(scoreBoard[command-1] == 0)){
            return "You already used that one";
        }else{
            scoreBoard[command-1] = score;
            totalScore += score;
            return "Your score " + score + " is saved";
        }
    }
}

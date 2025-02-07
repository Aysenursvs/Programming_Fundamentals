package TreasureHunt;
import java.util.ArrayList;

/**
 * The Scoreboard class represents a collection of Score objects.
 * It provides methods to manipulate and retrieve scores, including
 * adding, removing, and finding the maximum score.
 * 
 * @author 300201044 Ekin Ece Bayrak
 * @author 300201051 Ayşenur Sivaslıgil
 * @author 300201052 Mustafa Erkoca
 * @author 310201060 Büşra Şeyma Küyner
 */

public class Scoreboard {

    private ArrayList<Score> scores;

    // default constructor
    public Scoreboard(){
        this.scores = new ArrayList<>();
    }
    // prametrized constructor
    public Scoreboard(ArrayList<Score> scores){
        this.scores = scores;
    }
    // copy constructor
    public Scoreboard(Scoreboard scoreboard){
        if(scoreboard == null){
            System.out.println("Fatal Error: Scoreboard is null");
            System.exit(0);
        }
        this.scores = new ArrayList<>(scoreboard.scores);
    }

    // setters and getters
    public void setScores(ArrayList<Score> scores){
        this.scores = scores;
    }


    public ArrayList<Score> getScores(){
        return new ArrayList<Score>(scores);
    }

    public void addScore(Score scoreItem){
        scores.add(scoreItem);
    }

    public void removeScore(Score scoreItem){
        scores.remove(scoreItem);
    }

    // find the maximum score of the scoreboard arraylist
    public Score maxScore(){
        if(scores.size() == 0){
            return null;
        }
        Score maxScore = scores.get(0);
        for(Score score : scores){
            if(score.getUpdatedPoint() > maxScore.getUpdatedPoint()){
                maxScore = score;
            }
        }
        return maxScore;
    }

   
    // return the string representation of the Scoreboard
    public String toString(){
        String result = "";
        for(Score score : scores){
            result += score.toString() + "\n";
        }
        return result;
    }

    // equals method for Scoreboard
    public boolean equals(Scoreboard scoreboard){
        if(scoreboard == null){
            return false;
        }
        if(scores.size() != scoreboard.scores.size()){
            return false;
        }
        for(int i = 0; i < scores.size(); i++){
            if(!scores.get(i).equals(scoreboard.scores.get(i))){
                return false;
            }
        }
        return true;
    }

    // equals method for Scoreboard 
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }else{
            Scoreboard scoreboard = (Scoreboard)obj;
            return scores.equals(scoreboard.scores);
        }
    }

    // convert the Scoreboard to an array
    public Score[] toArray(){
        Score[] scoreArray = new Score[scores.size()];
        for(int i = 0; i < scores.size(); i++){
            scoreArray[i] = scores.get(i);
        }
        return scoreArray;
    }

}

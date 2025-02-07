package TreasureHunt;
/**
 * The Score class represents a score in a treasure hunt game.
 * It contains information about the departure and arrival positions on the map,
 * as well as the updated points scored.
 * 
 * @author 300201044 Ekin Ece Bayrak
 * @author 300201051 Ayşenur Sivaslıgil
 * @author 300201052 Mustafa Erkoca
 * @author 310201060 Büşra Şeyma Küyner
 */

public class Score {
    private MapPosition departed;
    private MapPosition arrived;
    private int updatedPoint;

    // default constructor
    public Score(){
        departed = new MapPosition();
        arrived = new MapPosition();
        updatedPoint = 0;
    }
    // prametrized constructor
    public Score(MapPosition departed, MapPosition arrived, int updatedPoint){
        this.departed = new MapPosition(departed);
        this.arrived = new MapPosition(arrived);
        this.updatedPoint = updatedPoint;
    }
    // copy constructor
    public Score(Score score){
        if(score == null){
            System.out.println("Fatal Error: Score is null");
            System.exit(0);
        }
        this.departed = new MapPosition(score.departed);
        this.arrived = new MapPosition(score.arrived);
        this.updatedPoint = score.updatedPoint;
    }

    //setters and getters
    public void setDeparted(MapPosition departed){
        this.departed = new MapPosition(departed);
    }

    public MapPosition getDeparted(){
        return new MapPosition(departed);
    }

    public void setArrived(MapPosition arrived){
        this.arrived = new MapPosition(arrived);
    }

    public MapPosition getArrived(){
        return new MapPosition(arrived);
    }

    public void setUpdatedPoint(int updatedPoint){
        this.updatedPoint = updatedPoint;
    }

    public int getUpdatedPoint(){
        return updatedPoint;
    }

    //return the string representation of the score
    public String toString(){
        return "Departed: " + departed.toString() + "          Arrived: " + arrived.toString() + "          Updated Point: " + updatedPoint;
    }

    //check if two scores are equal
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        Score score = (Score)obj;
        return departed.equals(score.departed) && arrived.equals(score.arrived) && updatedPoint == score.updatedPoint;
    }

    //equals method for score
    public boolean equals(Score score){
        if(score == null){
            return false;
        }else{
            return departed.equals(score.departed) && arrived.equals(score.arrived) && updatedPoint == score.updatedPoint;
        }
    }

    
}

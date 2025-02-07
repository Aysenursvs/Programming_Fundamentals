package TreasureHunt;
import java.util.ArrayList;

/**
 * The Player class represents a player in the Treasure Hunt game.
 * It extends the MapItem class and includes additional attributes
 * such as points and lives.
 * 
 * @author 300201044 Ekin Ece Bayrak
 * @author 300201051 Ayşenur Sivaslıgil
 * @author 300201052 Mustafa Erkoca
 * @author 310201060 Büşra Şeyma Küyner
 */

public class Player extends MapItem{
    private int point;
    private int live;

    // default constructor
    public Player(){
        super();
        setSymbol("P");;
        point = 100;
        live = 2;
    }
    // prametrized constructor
    public Player(MapPosition position, String symbol, int value, int lives){
        super(position, symbol, value, lives);
        
    }

    // copy constructor
    public Player(Player player){
        super(player);
        if(player == null){
            System.out.println("Fatal Error: Player is null");
            System.exit(0);
        }
        this.point = player.point;
        this.live = player.live;
    }

    //setters and getters
    public void setPoint(int point){
        this.point = point;
    }

    public int getPoint(){
        return point;
    }

    public void setLive(int live){
        this.live = live;
    }

    public int getLive(){
        return live;
    }
    
    // this method is used to decide the next position of the player randomly
    public void nextRandomStep(Player player, Map map, ArrayList<MapItem> specialItems, Scoreboard scores){
        
        MapItem mapItem = new MapItem();                                                //Use this map item to add the current position of the player to the map after the player has moved
        MapPosition departed = player.getPosition();                                    //Take the current position of the player
        
        map.addMapItemWithCoordinates(mapItem, departed.getX(), departed.getY());       //Player departed from the current position so add a map item to the current position 
        MapPosition arrived = player.decideRandomlyPosition();                          //Player decided the next position randomly, arrived is the next position of the player
        
        MapPosition mapPosition = map.getCoordinates(arrived.getX(), arrived.getY());   //Get the map position of the arrived position of the player
        MapItem item = map.getItem(mapPosition);                                        // haritada bulunan itemı al bu item ı playerın canı ve puanında değişlik için kullanıcaz
        if(item.getPosition().getIsOccupied() == true){                                 //If the arrived position is occupied, then point and live of the player will be arranged according to the item
            int originalPoint = player.point;                                           //Take the original point of the player

            player.arrangedPoint(item.getCombinedValue());                              //Arrange the point of the player according to the item
            player.arrangedLives(item.getCombinedLives());                              //Arrange the live of the player according to the item

            Score score = null;
            if(player.getPoint() != originalPoint){                                     //If the point of the player is changed, then add the score to the scoreboard
                score = new Score(departed, arrived, player.getPoint());
                scores.addScore(score);
            }

            specialItems.remove(item);                                                  // Player used the item so remove the item from the special items
        }
        

        map.addMapItemWithCoordinates(player, arrived.getX(), arrived.getY());          //Add the player to the arrived position
    }

    //this method is used to arrange the point of the player
    private void arrangedPoint(int points) {
        this.point += points;
    }

    //this method is used to arrange the lives of the player
    private void arrangedLives(int lives) {
        this.live += lives;
    }

    //equals method
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        else if(obj.getClass() != this.getClass()){
            return false;
        }
        else{
            Player player = (Player) obj;
            return player.point == this.point && player.live == this.live;
        }
    }

    public boolean equals(Player player){
        if(player == null){
            return false;
        }else{
            return player.point == this.point && player.live == this.live;
        }
        
    }

    //toString method
    public String toString(){
        return super.toString() + "Point: " + point + " Live: " + live;
    }
}

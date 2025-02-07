package TreasureHunt;
import java.util.Random;
/**
 * The MapItem class represents an item on a treasure hunt map.
 * Each MapItem has a position, symbol, value, and lives.
 * 
 * It provides methods to get and set these properties, as well as
 * methods to compare MapItems and generate a random position.
 * 
 * @author 300201044 Ekin Ece Bayrak
 * @author 300201051 Ayşenur Sivaslıgil
 * @author 300201052 Mustafa Erkoca
 * @author 310201060 Büşra Şeyma Küyner
 */
public class MapItem {
    private MapPosition position;
    private String symbol;
    private int value;
    private int lives;

    // default constructor
    public MapItem(){
        position = new MapPosition();
        symbol = "__";
        value = 0;
        lives = 0;
    }
    // prametrized constructor
    public MapItem(MapPosition position, String symbol, int value, int lives){
        this.position = position;
        this.symbol = symbol;
        this.value = value;
        this.lives = lives;
    }
    // copy constructor
    public MapItem(MapItem item){
        if(item == null){
            System.out.println("Fatal Error: MapItem is null");
            System.exit(0);

        }
        this.position = item.position;
        this.symbol = item.symbol;
        this.value = item.value;
        this.lives = item.lives;
    }

    // calculate the combined value(point) of the items
    public int getCombinedValue() {
        return value;
    }

    // calculate the combined lives of the items
    public int getCombinedLives() {
        return lives;
    }

    //setter and getter methods
    public MapPosition getPosition(){
        return new MapPosition(position);
    }

    public void setPosition(MapPosition position){
        this.position = position;
    }

    public String getSymbol(){
        return symbol;
    }

    public void setSymbol(String symbol){
        this.symbol = symbol;
    }

    public int getLives(){
        return lives;
    }

    public void setLives(int lives){
        this.lives = lives;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    // generate a random position for the item and return this map position
    public MapPosition decideRandomlyPosition(){
        Random random = new Random();
        int x = random.nextInt(20);
        int y = random.nextInt(20);
        position = new MapPosition(x, y, false);
        return new MapPosition(position);
    }
    
    // return a string representation of the item
    public String toString(){
        return "Position: " + position + " Symbol: " + symbol + " Value: " + value;
    }
    
    // compare two items and return true if they are equal
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        else if(obj.getClass() != this.getClass()){
            return false;
        }
        else{
            MapItem item = (MapItem) obj;
            return position.equals(item.position) && symbol.equals(item.symbol) && value == item.value;
        }
    }

    public boolean equals(MapItem item){
        if(item == null){
            return false;
        }else{
            return position.equals(item.position) && symbol.equals(item.symbol) && (value == item.value) && (lives == item.lives);
        }
    }
    


    

   


}

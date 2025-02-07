/**
 * The Breaker class represents a type of MapItem that can hold and manage a collection of other Breaker items such as
 * frog and mushroom.
 * It extends the MapItem class and provides additional functionality for managing a list of breakers.
 * 
 * This class includes methods for initializing, adding, removing, and retrieving breakers, as well as
 * calculating the combined value and lives of all contained breakers.
 * 
 * @author 300201044 Ekin Ece Bayrak
 * @author 300201051 Ayşenur Sivaslıgil
 * @author 300201052 Mustafa Erkoca
 * @author 310201060 Büşra Şeyma Küyner
 */
package TreasureHunt;
import java.util.ArrayList;


public class Breaker extends MapItem {
    private ArrayList<Breaker> breakers = new ArrayList<Breaker>();
    // default constructor
    public Breaker(){
        super();
        setSymbol("X");
        setValue(0);
        setLives(0);
    }
    // prametrized constructor
    public Breaker(MapPosition position, String symbol, int value, int lives){
        super(position, symbol, value, lives);
    }
    // copy constructor
    public Breaker(Breaker breaker){
        super(breaker);
        if(breaker == null){
            System.out.println("Fatal Error: Breaker is null");
            System.exit(0);
        }
    }
    // initialize the breakers with Mushroom and Frog
    public void initializeBreakers() {
        // Coin, Diamond ve Treasure nesnelerini ekle
        breakers.add(new Mushroom());
        breakers.add(new Frog());

    }
    // setters and getters
    public void setBreakers(ArrayList<Breaker> breakers){
        this.breakers = breakers;
    }

    public ArrayList<MapItem> getBreakers(){
        return new ArrayList<>(breakers);
    }

    public void addBreaker(Breaker breakerItem){
        breakers.add(breakerItem);
    }

    public void removeBreaker(Breaker breakerItem){
        breakers.remove(breakerItem);
    }

    @Override
    // calculate the combined value(point) of the breakers
    public int getCombinedValue() {
        int totalPoints = 0;
        for (Breaker item : breakers) {
            totalPoints += item.getCombinedValue();
        }
        return totalPoints;
    }

    @Override
    // calculate the combined lives of the breakers
    public int getCombinedLives() {
        int totalLives = 0;
        for (Breaker item : breakers) {
            totalLives += item.getCombinedLives();
        }
        return totalLives;
    }

    // equals method
    public boolean equals(Breaker breaker){
        if(breaker == null){
            return false;
        }
        return super.equals(breaker);
    }

    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        else if(obj.getClass() != this.getClass()){
            return false;
        }
        else{
            Breaker breaker = (Breaker) obj;
            return super.equals(breaker);
        }
    }
    // toString method
    public String toString(){
        return super.toString() + "Breakers: " + breakers;
    }


    

}

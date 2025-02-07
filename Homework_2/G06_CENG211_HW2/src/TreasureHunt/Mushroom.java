package TreasureHunt;

/**
 * The Mushroom class represents a type of Breaker in the Treasure Hunt game.
 * It can have a negative value and does not affect the player's lives.
 * 
 * @author 300201044 Ekin Ece Bayrak
 * @author 300201051 Ayşenur Sivaslıgil
 * @author 300201052 Mustafa Erkoca
 * @author 310201060 Büşra Şeyma Küyner
 */

public class Mushroom extends Breaker{
    // default constructor
    public Mushroom(){
        super();
        setSymbol("M");
        setValue(-20);
        setLives(0);
    }
    // prametrized constructor
    public Mushroom(MapPosition position, String symbol, int value, int lives){
        super(position, symbol, value, lives);
    }
    // copy constructor
    public Mushroom(Mushroom mushroom){
        super(mushroom);
        if(mushroom == null){
            System.out.println("Fatal Error: Mushroom is null");
            System.exit(0);
        }
    }

    @Override
    // calculate the combined value(point) of the mushrooms
    public int getCombinedValue() { 
        return getValue(); 
    }

    @Override
    // calculate the combined lives of the mushrooms
    public int getCombinedLives() { 
        return getLives(); 
    }

    // equals method
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(this.getClass() != obj.getClass()){
            return false;
        }else{
            Mushroom other = (Mushroom) obj;
            return super.equals(other);
        }
        
    }

    public boolean equals(Mushroom other){
        if(other == null){
            return false;
        }else{
            return super.equals(other);
        }
        
    }
    //toString method
    public String toString(){
        return "Mushroom: " + super.toString();
    }

}

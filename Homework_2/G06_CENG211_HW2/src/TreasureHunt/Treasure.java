package TreasureHunt;
/**
 * The Treasure class represents a treasure item on the map.
 * It extends the Booster class and includes additional properties and methods specific to a treasure.
 * 
 * @author 300201044 Ekin Ece Bayrak
 * @author 300201051 Ayşenur Sivaslıgil
 * @author 300201052 Mustafa Erkoca
 * @author 310201060 Büşra Şeyma Küyner
 */
public class Treasure extends Booster {
    // default constructor
    public Treasure(){
        super();
        setSymbol("T");
        setValue(0);
        setLives(1);
    }
    // prametrized constructor
    public Treasure(MapPosition position, String symbol, int value, int lives){
        super(position, symbol, value, lives);
    }
    // copy constructor
    public Treasure(Treasure treasure){
        super(treasure);
        if(treasure == null){
            System.out.println("Fatal Error: Treasure is null");
            System.exit(0);
        }
    }
    @Override
    // calculate the combined value(point) of the treasures
    public int getCombinedValue() { 
        return getValue(); 
    }

    @Override
    // calculate the combined lives of the treasures
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
            Treasure other = (Treasure) obj;
            return super.equals(other);
        }
        
    }

    public boolean equals(Treasure other){
        if(other == null){
            return false;
        }else{
        return super.equals(other);
        }
    }

    //toString method
    public String toString(){
        return "Treasure: " + super.toString();
    }


}

package TreasureHunt;

/**
 * The Diamond class represents a diamond item on the map in the Treasure Hunt game.
 * It extends the Booster class and provides specific properties and behaviors for a diamond.
 * 
 * @author 300201044 Ekin Ece Bayrak
 * @author 300201051 Ayşenur Sivaslıgil
 * @author 300201052 Mustafa Erkoca
 * @author 310201060 Büşra Şeyma Küyner
 */
public class Diamond extends Booster {
    // default constructor
    public Diamond(){
        super();
        setSymbol("D");
        setValue(10);
        setLives(0);
    }
    // prametrized constructor
    public Diamond(MapPosition position, String symbol, int value, int lives){
        super(position, symbol, value, lives);
    }
    // copy constructor
    public Diamond(Diamond diamond){
        super(diamond);
        if(diamond == null){
            System.out.println("Fatal Error: Diamond is null");
            System.exit(0);
        }
    }

    @Override
    // calculate the combined value(point) of the diamonds
    public int getCombinedValue() { 
        return getValue();
    }

    @Override
    // calculate the combined lives of the diamonds
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
            Diamond other = (Diamond) obj;
            return super.equals(other);
        }
        
    }

    public boolean equals(Diamond other){
        if(other == null){
            return false;
        }else{
            return super.equals(other);
        }
    }
    //toString method
    public String toString(){
        return "Diamond: " + super.toString();
    }

}

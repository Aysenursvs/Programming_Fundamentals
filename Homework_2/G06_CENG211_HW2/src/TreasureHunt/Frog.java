package TreasureHunt;
/**
 * The Frog class represents a frog in the treasure hunt game.
 * It extends the Breaker class and provides specific implementations
 * for the frog's behavior and properties.
 * 
 * @author 300201044 Ekin Ece Bayrak
 * @author 300201051 Ayşenur Sivaslıgil
 * @author 300201052 Mustafa Erkoca
 * @author 310201060 Büşra Şeyma Küyner
 */
public class Frog extends Breaker{
    // default constructor
    public Frog(){
        super();
        setSymbol("F");
        setValue(0);
        setLives(-1);

    }
    // prametrized constructor
    public Frog(MapPosition position, String symbol, int value, int lives){
        super(position, symbol, value, lives);
    }

    // copy constructor
    public Frog(Frog frog){
        super(frog);
        if(frog == null){
            System.out.println("Fatal Error: Frog is null");
            System.exit(0);
        }
    }

    @Override
    // calculate the combined value(point) of the frogs
    public int getCombinedValue() { 
        return getValue(); 
    }

    @Override
    // calculate the combined lives of the frogs
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
            Frog other = (Frog) obj;
            return super.equals(other);
        }
        
    }

    public boolean equals(Frog other){
        if(other == null){
            return false;
        }else{
            return super.equals(other);
        }
    }
    //toString method
    public String toString(){
        return "Frog: " + super.toString();
    }

}

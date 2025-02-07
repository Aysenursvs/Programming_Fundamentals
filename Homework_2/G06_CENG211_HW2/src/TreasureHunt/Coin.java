package TreasureHunt;
/**
 * The Coin class represents a coin item in the treasure hunt game.
 * It extends the Booster class and provides specific implementations
 * for coins, including their symbol, value, and lives.
 * 
 * @author 300201044 Ekin Ece Bayrak
 * @author 300201051 Ayşenur Sivaslıgil
 * @author 300201052 Mustafa Erkoca
 * @author 310201060 Büşra Şeyma Küyner
 */
public class Coin extends Booster {
    // default constructor
    public Coin(){
        super();
        setSymbol("C");
        setValue(5);
        setLives(0);
    }
    // prametrized constructor
    public Coin(MapPosition position, String symbol, int value, int lives){

        super(position, symbol, value, lives);
    }
    // copy constructor
    public Coin(Coin coin){
        super(coin);
        if(coin == null){
            System.out.println("Fatal Error: Coin is null");
            System.exit(0);
        }
    }
    @Override
    // calculate the combined value(point) of the coins
    public int getCombinedValue() {
         return getValue(); 
    }

    @Override
    // calculate the combined lives of the coins
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
            Coin other = (Coin) obj;
            return super.equals(other);
        }
        
    }
    
    public boolean equals(Coin other){
        if(other == null){
            return false;
        }else{
            return super.equals(other);
        }
        
    }
    //toString method

    public String toString(){
        return "Coin" + super.toString();
    }
}

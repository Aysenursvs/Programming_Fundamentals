package TreasureHunt;
import java.util.ArrayList;

/**
 * The Booster class represents a special type of MapItem that can hold other Booster items
 * such as Coin, Diamond, and Treasure. It provides methods to initialize, add, remove,
 * and retrieve these items, as well as to calculate their combined values and lives.
 * 
 * This class extends the MapItem class and inherits its properties and methods.
 * 
 * @author 300201044 Ekin Ece Bayrak
 * @author 300201051 Ayşenur Sivaslıgil
 * @author 300201052 Mustafa Erkoca
 * @author 310201060 Büşra Şeyma Küyner
 */
public class Booster extends MapItem{
    private ArrayList<Booster> boosters = new ArrayList<Booster>();
    // default constructor
    public Booster(){
        super();
        setSymbol("O");
        setValue(0);
        setLives(0);
    }
    // prametrized constructor
    public Booster(MapPosition position, String symbol, int value, int lives){

        super(position, symbol, value, lives);
    }

    // copy constructor
    public Booster(Booster booster){
        super(booster);
        if(booster == null){
            System.out.println("Fatal Error: Booster is null");
            System.exit(0);
        }
    }
    // initialize the boosters with Coin, Diamond, and Treasure
    public void initializeBoosters() {
        // Coin, Diamond ve Treasure nesnelerini ekle
        boosters.add(new Coin());
        boosters.add(new Diamond());
        boosters.add(new Treasure());
    }
    // setters and getters
    public void setBoosters(ArrayList<Booster> boosters){
        this.boosters = boosters;
    }

    public ArrayList<MapItem> getBoosters(){
        return new ArrayList<>(boosters);
    }

    public void addBooster(Booster boosterItem){
        boosters.add(boosterItem);
    }

    public void removeBooster(Booster boosterItem){
        boosters.remove(boosterItem);
    }

    @Override
    // calculate the combined value(point) of the boosters
    public int getCombinedValue() {
        int totalPoints = 0;
        for (Booster item : boosters) {
            totalPoints += item.getCombinedValue();
        }
        return totalPoints;
    }

    @Override
    // calculate the combined lives of the boosters
    public int getCombinedLives() {
        int totalLives = 0;
        for (Booster item : boosters) {
            totalLives += item.getCombinedLives();
        }
        return totalLives;
    }

    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        else if(obj.getClass() != this.getClass()){
            return false;
        }
        else{
            Booster booster = (Booster) obj;
            return (super.equals(booster) && boosters.equals(booster.boosters));
        }
    }

    public boolean equals(Booster booster){
        if(booster == null){
            return false;
        }
        return (super.equals(booster) && boosters.equals(booster.boosters));
    }

    public String toString(){
        return (super.toString() + "Boosters: " + boosters);
    }

    



}

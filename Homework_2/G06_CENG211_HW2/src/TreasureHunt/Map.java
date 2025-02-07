package TreasureHunt;
import java.util.ArrayList;

/**
 * The Map class represents a 20x20 grid map for a treasure hunt game.
 * It contains methods to initialize the map with default items, add special items,
 * and retrieve or modify items on the map.
 * 
 * @author 300201044 Ekin Ece Bayrak
 * @author 300201051 Ayşenur Sivaslıgil
 * @author 300201052 Mustafa Erkoca
 * @author 310201060 Büşra Şeyma Küyner
 */

public class Map {
    private ArrayList<ArrayList<MapItem>> map;

    // default constructor
    // initialize the map with 20x20 grid and default items
    public Map(){
        map = new ArrayList<>();
        for(int i = 0; i < 20; i++){
            ArrayList<MapItem> row = new ArrayList<>();
            for(int j = 0; j < 20; j++){
                row.add(new MapItem(new MapPosition(i, j, false), "__", 0, 0));
            }
            map.add(row);
        }
        addMapItems();
        
    }

    // prametrized constructor
    public Map(ArrayList<ArrayList<MapItem>> map){
        this.map = map;
    }

    // copy constructor
    public Map(Map map){
        if(map == null){
            System.out.println("Fatal Error: Map is null");
            System.exit(0);
        }
        this.map = new ArrayList<>();
        for(ArrayList<MapItem> row : map.map){
            ArrayList<MapItem> newRow = new ArrayList<>();
            for(MapItem item : row){
                newRow.add(new MapItem(item));
            }
            this.map.add(newRow);
        }
    }

    // get and set methods
    public void setMap(ArrayList<ArrayList<MapItem>> map){
        this.map = map;
    }

    public ArrayList<ArrayList<MapItem>> getMap(){
        ArrayList<ArrayList<MapItem>> newMap = new ArrayList<>();
        for(ArrayList<MapItem> row : map){
            ArrayList<MapItem> newRow = new ArrayList<>();
            for(MapItem item : row){
                newRow.add(new MapItem(item));
            }
            newMap.add(newRow);
        }
        return newMap;
    }

    // print the map
    public void printMap(){
        for(ArrayList<MapItem> row : map){
            for(MapItem item : row){
                System.out.print(String.format("%-5s", item.getSymbol()));
            }
            System.out.println();
        }
    }

    // initialize special items to add to the map
    private ArrayList<MapItem> initiliazeSpecialItems() {
        ArrayList<MapItem> specialItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) { 
            Coin coin = new Coin();
            specialItems.add(coin);
        }
        for (int i = 0; i < 5; i++) {
            Diamond diamond = new Diamond();
            specialItems.add(diamond);
        }
        for (int i = 0; i < 5; i++) {
            Mushroom mushroom = new Mushroom();
            specialItems.add(mushroom);
        }
        for (int i = 0; i < 2; i++) {
            Frog frog = new Frog();
            specialItems.add(frog);
        }
        for (int i = 0; i < 2; i++) {
            Treasure treasure = new Treasure();
            specialItems.add(treasure);
        }
        Breaker breaker = new Breaker();
        breaker.initializeBreakers();
        specialItems.add(breaker);
        Booster booster = new Booster();
        booster.initializeBoosters();
        specialItems.add(booster);
        return specialItems;
    }

    // get all special items on the map
    public ArrayList<MapItem> getSpecialItems(){ 
        ArrayList<MapItem> specialItems = new ArrayList<>();
        for(ArrayList<MapItem> row : map){
            for(MapItem item : row){
                if(item.getClass() != MapItem.class){ 
                    specialItems.add(item);
                }
            }
        }
        
        return specialItems;
    }

    // add special items to the map by using addMapItem method and specialItems arraylist
    private void addMapItems() {
        for (MapItem specialItem : initiliazeSpecialItems()) {
            addMapItem(specialItem);
        }
    }
    
    // add a MapItem to the map by checking if the position is occupied
    public void addMapItem(MapItem item) {
        MapPosition location = item.decideRandomlyPosition();
        item.setPosition(location); 
        if (map.get(location.getX()).get(location.getY()).getPosition().getIsOccupied() == false) {
            map.get(location.getX()).set(location.getY(), item);
            location.setIsOccupied(true);
        } else {
            addMapItem(item); 
        }
    }


    // add a MapItem to the map with given coordinates
    public void addMapItemWithCoordinates(MapItem item, int x, int y){
        map.get(x).set(y, item);
    }

    // get the coordinates of the given position
    public MapPosition getCoordinates(int x, int y){
        return map.get(x).get(y).getPosition();

    }

    // get the item at the given position
    public MapItem getItem(MapPosition position){
        return map.get(position.getX()).get(position.getY());
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
            Map map = (Map) obj;
            return map.map.equals(this.map);
        }
    }

    public boolean equals(Map map){
        if(map == null){
            return false;
        }
        return map.map.equals(this.map);
    }
    //toString method
    public String toString(){
        return "Map: " + map;
    }
}


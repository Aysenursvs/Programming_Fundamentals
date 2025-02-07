package TreasureHunt;
/**
 * The MapPosition class represents a position on a map with x and y coordinates and an occupancy status.
 * 
 * @author 300201044 Ekin Ece Bayrak
 * @author 300201051 Ayşenur Sivaslıgil
 * @author 300201052 Mustafa Erkoca
 * @author 310201060 Büşra Şeyma Küyner
 */

public class MapPosition {
    private int x;
    private int y;
    private boolean isOccupied;

    //default constructor
    public MapPosition(){  
        x = 0;
        y = 0;
        isOccupied = false;
    }

    //parameterized constructor
    public MapPosition(int x, int y, boolean isOccupied) {
        this.x = x;
        this.y = y;
        this.isOccupied = isOccupied;
    }


    //copy constructor
    public MapPosition(MapPosition mapPosition) {
        if(mapPosition == null) {
            System.out.println("Fatal Error: null mapPosition");
            System.exit(0);
        }
        this.x = mapPosition.x;
        this.y = mapPosition.y;
        this.isOccupied = mapPosition.isOccupied;
    }

    //setter and getter methods
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    //return the string representation of the map position
    public String toString() {
        return "X: " + (getX() < 10 ? "0" + getX() : getX()) + " Y: " + (getY() < 10 ? "0" + getY() : getY());
    }

    //equals method for map position
    public boolean equals(MapPosition mapPosition) {
        return this.x == mapPosition.x && this.y == mapPosition.y && this.isOccupied == mapPosition.isOccupied;
    }

    //equals method for object
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        MapPosition mapPosition = (MapPosition)obj;
        return this.x == mapPosition.x && this.y == mapPosition.y && this.isOccupied == mapPosition.isOccupied;
    }
}

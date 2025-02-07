package GardenPuzzleDomain.Classes;

import GardenPuzzleDomain.Enums.Position;
import GardenPuzzleDomain.Interfaces.ISquare;

/**
 * The GardenSquare class represents a square in a garden puzzle.
 * It contains information about the square's position, the object occupying the square, and whether the square is occupied.
 */
public class GardenSquare {

    private ISquare squareObject;
    private Position position;
    private boolean isOccupied;

    // Default constructor
    public GardenSquare() {
        this.squareObject = null;
        this.position = Position.DEFAULT;
        this.isOccupied = false;
    }

    // Parameterized constructor
    public GardenSquare(ISquare squareObject, Position position, boolean isOccupied) {
        this.squareObject = squareObject;
        this.position = position;
        this.isOccupied = false;
    }

    // Copy constructor
    public GardenSquare(GardenSquare gardenSquare) {
        if(gardenSquare == null){
            System.out.println("Fatal Error: gardenSquare is null");
            System.exit(0);
        }
        this.squareObject = gardenSquare.squareObject;
        this.position = gardenSquare.position;
        this.isOccupied = gardenSquare.isOccupied;
    }

    // Getters and Setters

    public ISquare getSquareObject() {
        return squareObject;
    }

    public void setSquareObject(ISquare squareObject) {
        if(squareObject == null){
            System.out.println("Fatal Error: squareObject is null");
            System.exit(0);
        }
        this.squareObject = squareObject;
        this.isOccupied = true;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    } 
    
    public boolean getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    
    //toString method
    @Override
    public String toString() {
        String result = "GardenSquare: " +
                "Square Object: ";
        if(squareObject == null){
            result += "Null";
        } else {
            result += squareObject.toString();
        }
        result += ", Position: " + position;
        return result;
                
    }

    //equals method that takes an object as parameter
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        else if(getClass() != obj.getClass()){
            return false;
        }
        GardenSquare gardenSquare = (GardenSquare) obj;
        return squareObject.equals(gardenSquare.squareObject) && position.equals(gardenSquare.position) && isOccupied == gardenSquare.isOccupied;
    }

    //equals method that takes a GardenSquare as parameter
    public boolean equals(GardenSquare gardenSquare){
        if(gardenSquare == null){
            return false;
        }
        return squareObject.equals(gardenSquare.squareObject) && position.equals(gardenSquare.position) && isOccupied == gardenSquare.isOccupied;
    }

    //clone method
    public GardenSquare clone(){
        return new GardenSquare(this);
    }

    public boolean addAGardenSquareObject(ISquare squareObject){
        if(this.isOccupied){
            return false;
        }
        setSquareObject(squareObject);
        ((GardenObject)squareObject).setPosition(this.position);
        if(this.squareObject.getClass() != PollenCloud.class){
            
            this.isOccupied = true;
            return true;
        }
        return false;
    }



}

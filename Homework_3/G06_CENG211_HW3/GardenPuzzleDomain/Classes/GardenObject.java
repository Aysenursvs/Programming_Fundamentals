package GardenPuzzleDomain.Classes;

import GardenPuzzleDomain.Enums.Position;
import GardenPuzzleDomain.Interfaces.ISquare;

/**
 * The GardenObject class is an abstract class that represents an object in a garden.
 * It implements the ISquare interface and provides basic properties and methods 
 * for garden objects, such as id, position, and common methods like equals, toString, 
 * and clone.
 */
public abstract class GardenObject implements ISquare{
    private String id;
    private Position position;

    //default constructor
    public GardenObject() {
        this.id = "default";
        this.position = Position.DEFAULT; // or any valid enum constant
    }

    //parameterized constructor
    public GardenObject(String id, Position position) {
        this.id = id;
        this.position = position;
    }

    //copy constructor
    public GardenObject(GardenObject gardenObject) {
        if(gardenObject == null){
            System.out.println("Fatal Error: gardenObject is null");
            System.exit(0);
        }
        this.id = gardenObject.id;
        this.position = gardenObject.position;
    }

    //getter and setter methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    //toString method
    @Override
    public String toString() {
        return "GardenObject: " +
                "Id: " + id +
                ", Position: " + position;
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
        else{
            GardenObject gardenObject = (GardenObject) obj;
            return (id.equals(gardenObject.id) && position.equals(gardenObject.position));
        }
    }

    //equals method that takes a GardenObject as parameter
    public boolean equals(GardenObject gardenObject){
        if(gardenObject == null){
            return false;
        }else{
            return (id.equals(gardenObject.id) && position.equals(gardenObject.position));
        }
    }

    //abstract clone method
    public abstract GardenObject clone();

    
    /**
     * Prints the details of the garden object.
     * This method should be implemented by subclasses to provide specific details.
     *
     * We made this method abstract because we want to force the subclasses to implement it.
     * This way, we can ensure that each subclass provides a specific implementation of the print method.
     * 
     * @return A string representation of the garden object.
     */
    public abstract String print();

   
}

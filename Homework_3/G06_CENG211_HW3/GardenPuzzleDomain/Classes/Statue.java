package GardenPuzzleDomain.Classes;

import GardenPuzzleDomain.Enums.Position;

/**
 * The Statue class represents a statue object in a garden.
 * It extends the GardenObject class and provides additional functionality specific to statues.
 * 
 * This class includes constructors for creating statues with default values, specific values, 
 * and by copying another statue. It also overrides the toString, equals, and clone methods 
 * from the Object class, and provides a custom print method.
 */
public class Statue extends GardenObject{
    

    //default constructor
    public Statue() {
        super();
    }

    //parameterized constructor
    public Statue(String id, Position position) {
        super(id, position);
    }

    //copy constructor
    public Statue(Statue statue) {
        super(statue);
    }

    //toString method
    @Override
    public String toString() {
        return "Statue: " +
                "Id: " + getId() +
                ", Position:" + getPosition();
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
            Statue statue = (Statue) obj;
            return (getId().equals(statue.getId()) && getPosition().equals(statue.getPosition()));
        }
    }

    //equals method that takes a Statue as parameter
    public boolean equals(Statue statue){
        return (getId().equals(statue.getId()) && getPosition().equals(statue.getPosition()));
    }

    //clone method
    @Override
    public Statue clone(){
        return new Statue(this);
    }
    
    //print method
    @Override
    public String print(){
        return "Statue: " + getId() + " at " + getPosition();
    }

}

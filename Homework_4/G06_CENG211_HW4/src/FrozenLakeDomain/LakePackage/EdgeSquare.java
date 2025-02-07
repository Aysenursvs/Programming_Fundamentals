package FrozenLakeDomain.LakePackage;

import java.util.ArrayList;
import java.util.List;

import FrozenLakeDomain.HazardsPackage.Hazard;
import FrozenLakeDomain.InterfacesPackage.IEffectable;
import FrozenLakeDomain.InterfacesPackage.ISquare;

/**
 * The EdgeSquare class represents a square on the edge of a frozen lake.
 * It implements the ISquare interface and contains a list of IEffectable objects.
 * The class provides constructors, getter and setter methods, and overrides the equals, toString, and clone methods.
 * It also includes methods to check for hazards and add effectable objects.
 */
public class EdgeSquare implements ISquare {
    private List<IEffectable> edgeObjects;
    private int row;
    private int column;

    // Default constructor
    public EdgeSquare() {
        this.edgeObjects = new ArrayList<IEffectable>();
        this.row = 0;
        this.column = 0;
    }

    // Parameterized constructor
    public EdgeSquare(List<IEffectable> edgeObjects, int row, int column) {
        this.edgeObjects = edgeObjects;
        this.row = row;
        this.column = column;
    }

    // Copy constructor
    public EdgeSquare(EdgeSquare other) {
        if (other == null) {
            System.out.println("Fatal error: null EdgeSquare object passed to copy constructor");
            System.exit(1);
        }
        this.edgeObjects = new ArrayList<>(other.edgeObjects);
        this.row = other.row;
        this.column = other.column;
    }

    // Getter and setter methods
    public List<IEffectable> getEdgeObjects() {
        return edgeObjects;
    }

    public void setEdgeObjects(List<IEffectable> edgeObjects) {
        this.edgeObjects = edgeObjects;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
            String content = edgeObjects.get(0).toString();
            int padding = (8 - content.length()) / 2;
            return "|" + String.format("%" + padding + "s", "") + content + String.format("%" + (8 - content.length() - padding) + "s", "");
        
    }

    //equals method that takes an Object as a parameter
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EdgeSquare other = (EdgeSquare) obj;
        return this.edgeObjects.equals(other.edgeObjects) && this.row == other.row && this.column == other.column;
    }

    //equals method that takes an EdgeSquare as a parameter
    public boolean equals(EdgeSquare other) {
        if (other == null) {
            return false;
        }
        return this.edgeObjects.equals(other.edgeObjects) && this.row == other.row && this.column == other.column;
    }

    //clone method
    public EdgeSquare clone() {
        return new EdgeSquare(this);
    }

    //containsAny method
    public boolean containsAny() {
        return !edgeObjects.isEmpty();
    }

    /**
     * Checks if the current edge contains any hazardous objects.
     * Specifically, it looks for objects of type "CliffEdge" within the edgeObjects collection.
     *
     * @return true if a "CliffEdge" object is found, false otherwise.
     */
    public boolean containsAnyHazard() {
        for (IEffectable edgeObject : edgeObjects) {
            if (edgeObject.getClass().getSimpleName().equals("CliffEdge")) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an effectable object to the list of edge objects.
     *
     * @param effectable the effectable object to be added
     */
    @Override
    public void addEffectable(IEffectable effectable) {
        edgeObjects.add(effectable);
    }

    /**
     * Retrieves a hazard object from the edgeObjects list based on the provided hazard name.
     *
     * @param hazardName the name of the hazard to retrieve
     * @return the hazard object that matches the provided name, or null if no matching hazard is found
     */
    @Override
    public IEffectable getHazard(String hazardName) {
        for (IEffectable edgeObject : edgeObjects) {
            if (edgeObject instanceof Hazard && edgeObject.getClass().getSimpleName().equals(hazardName)) {
                return edgeObject;
            }
        }
        return null;
    }

    /**
     * Retrieves a list of objects that can be affected by this edge square.
     *
     * @return a list of IEffectable objects that are present on this edge square.
     */
    @Override
    public List<IEffectable> getEffectables() {        
        return edgeObjects;
    }
}

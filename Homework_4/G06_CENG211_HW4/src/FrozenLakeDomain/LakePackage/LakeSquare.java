package FrozenLakeDomain.LakePackage;

import java.util.ArrayList;
import java.util.List;

import FrozenLakeDomain.HazardsPackage.Hazard;
import FrozenLakeDomain.InterfacesPackage.IEffectable;
import FrozenLakeDomain.InterfacesPackage.ISquare;

/**
 * The LakeSquare class represents a square in the Frozen Lake domain.
 * It implements the ISquare interface and contains a list of IEffectable objects.
 * Each LakeSquare has a row and column position.
 * 
 */
public class LakeSquare implements ISquare{
    private List<IEffectable> squareObjectList;
    private int row;
    private int column;

    // Default constructor
    public LakeSquare() {
        this.squareObjectList = new ArrayList<IEffectable>();
        this.row = 0;
        this.column = 0;
    }

    // Parameterized constructor
    public LakeSquare(List<IEffectable> squareObjectList, int row, int column) {
        this.squareObjectList = squareObjectList;
        this.row = row;
        this.column = column;
    }

    // Copy constructor
    public LakeSquare(LakeSquare other) {
        this.squareObjectList = new ArrayList<IEffectable>();
        for (IEffectable effectable : other.squareObjectList) {
            this.squareObjectList.add(effectable);
        }
        this.row = other.row;
        this.column = other.column;
    }

    // Getter and setter methods
    public List<IEffectable> getSquareObjectList() {
        List<IEffectable> squareObjectListCopy = new ArrayList<IEffectable>();
        for (IEffectable effectable : squareObjectList) {
            squareObjectListCopy.add(effectable);
        }
        return squareObjectListCopy;
    }

    public void setSquareObjectList(List<IEffectable> squareObjectList) {
        this.squareObjectList = squareObjectList;
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

    //toString method
    @Override
    public String toString() {
        int cellWidth = 8; // Define the width of each cell for consistent formatting

        if (squareObjectList.size() == 0) {
            // Empty cell
            return "|" + String.format("%-" + cellWidth + "s", "");
        } else if (squareObjectList.size() == 1) {
            // Single object, centered in the cell
            String content = squareObjectList.get(0).toString();
            int padding = (cellWidth - content.length()) / 2;
            return "|" + String.format("%" + padding + "s", "") + content + String.format("%" + (cellWidth - content.length() - padding) + "s", "");
        } else if (squareObjectList.size() == 2) {
            // Two objects, joined with a dash and aligned
            String content = squareObjectList.get(0).toString() + "-" + squareObjectList.get(1).toString();
            int padding = (cellWidth - content.length()) / 2;
            return "|" + String.format("%" + padding + "s", "") + content + String.format("%" + (cellWidth - content.length() - padding) + "s", "");
        } else {
            // More than two objects (optional case, truncation or custom formatting)
            String content = squareObjectList.get(0).toString() + "-" + squareObjectList.get(1).toString();
            content += "..."; // Indicate there are more objects
            if (content.length() > cellWidth) {
                content = content.substring(0, cellWidth); // Truncate to fit cell width
            }
            return "|" + String.format("%-" + cellWidth + "s", content);
        }
    }

    // equals method that compares two LakeSquare objects
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LakeSquare that = (LakeSquare) obj;
        if (this.row != that.row || this.column != that.column) {
            return false;
        }
        if (this.squareObjectList.size() != that.squareObjectList.size()) {
            return false;
        }
        for (int i = 0; i < this.squareObjectList.size(); i++) {
            if (!this.squareObjectList.get(i).equals(that.squareObjectList.get(i))) {
                return false;
            }
        }
        return true;
    }

    // equals method that compares two LakeSquare objects
    public boolean equals(LakeSquare other) {
        if (other == null) {
            return false;
        }
        if (this.row != other.row || this.column != other.column) {
            return false;
        }
        if (this.squareObjectList.size() != other.squareObjectList.size()) {
            return false;
        }
        for (int i = 0; i < this.squareObjectList.size(); i++) {
            if (!this.squareObjectList.get(i).equals(other.squareObjectList.get(i))) {
                return false;
            }
        }
        return true;
    }

    // clone method
    public LakeSquare clone() {
        return new LakeSquare(this);
    }

    // addEffectable method
    public void addEffectable(IEffectable effectable) {
        this.squareObjectList.add(effectable);
    }

    // removeEffectable method
    public void removeEffectable(IEffectable effectable) {
        this.squareObjectList.remove(effectable);
    }

    /**
     * Checks if the current LakeSquare contains any hazard.
     *
     * @return true if the LakeSquare contains at least one object that is an instance of Hazard, false otherwise.
     */
    public boolean containsAnyHazard(){  
        for (IEffectable effectable : squareObjectList) {
            if(effectable instanceof Hazard){
                return true;
            }
        }
        return false;
    } 

    /**
     * Checks if the square contains any objects.
     *
     * @return true if the square contains one or more objects, false otherwise.
     */
    public boolean containsAny(){  
        return squareObjectList.size() > 0;
    } 
    
    /**
     * Retrieves a hazard from the squareObjectList based on the provided hazard name.
     *
     * @param hazardName the name of the hazard to be retrieved
     * @return an instance of IEffectable that matches the hazard name and is an instance of Hazard,
     *         or null if no matching hazard is found
     */
    public IEffectable getHazard(String hazardName){
        for (IEffectable effectable : squareObjectList) {
            if(effectable instanceof Hazard && effectable.getClass().getSimpleName().equals(hazardName)){
                return effectable;
            }
        }
        return null;
    }

    // We will make changes in this List.
    /**
     * Retrieves the list of effectable objects present in the lake square.
     * Note: The returned list is not a copy, but a reference to the original list.
     *
     * @return a list of objects that implement the IEffectable interface.
     */
    public synchronized List<IEffectable> getEffectables(){ //We do not return copy on purpose
        return squareObjectList;
    }
}

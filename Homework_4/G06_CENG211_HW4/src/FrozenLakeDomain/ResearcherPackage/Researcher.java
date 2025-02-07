package FrozenLakeDomain.ResearcherPackage;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

import FrozenLakeDomain.ExceptionsPackage.IncompatibleResearchEquipmentLocationException;
import FrozenLakeDomain.ExceptionsPackage.UnavailableDirectionException;
import FrozenLakeDomain.ExceptionsPackage.UnavailableEquipmentException;
import FrozenLakeDomain.ExperimentsPackage.Experiment;
import FrozenLakeDomain.InterfacesPackage.IEffectable;
import FrozenLakeDomain.InterfacesPackage.ISquare;
import FrozenLakeDomain.LakePackage.Direction;
import FrozenLakeDomain.LakePackage.FrozenLake;
import FrozenLakeDomain.LakePackage.LakeSquare;

/**
 * The Researcher class represents a researcher in the FrozenLakeDomain.
 * It implements the IEffectable interface and provides methods to manage
 * the researcher's state, equipment, and interactions within the lake and its objects.
 */
public class Researcher implements IEffectable{
    private int id;
    private int currentRow, currentColumn;
    private boolean isTired;
    private EquipmentBag<Equipment> equipmentBag;

    // Default constructor
    public Researcher() {
        this.id = 0;
        this.currentRow = 0;
        this.currentColumn = 0;
        this.isTired = false;
        this.equipmentBag = new EquipmentBag<>();
    }

    public Researcher(int id) {
        this.id = id;
        this.currentRow = 1;
        this.currentColumn = 6;
        this.isTired = false;
        this.equipmentBag = new EquipmentBag<>();
    }

    // Parameterized constructor
    public Researcher(int id, int currentRow, int currentColumn, boolean isTired, EquipmentBag<Equipment> equipmentBag) {
        this.id = id;
        this.currentRow = currentRow;
        this.currentColumn = currentColumn;
        this.isTired = isTired;
        this.equipmentBag = equipmentBag;
    }

    // Copy constructor
    public Researcher(Researcher other) {
        this.id = other.id;
        this.currentRow = other.currentRow;
        this.currentColumn = other.currentColumn;
        this.isTired = other.isTired;
        this.equipmentBag = new EquipmentBag<>(other.equipmentBag);
    }

    // Getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public void setCurrentRow(int currentRow) {
        this.currentRow = currentRow;
    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    public void setCurrentColumn(int currentColumn) {
        this.currentColumn = currentColumn;
    }

    public boolean isTired() {
        return isTired;
    }

    public void setTired(boolean isTired) {
        this.isTired = isTired;
    }

    public EquipmentBag<Equipment> getEquipmentBag() {
        return equipmentBag;
    }

    public void setEquipmentBag(EquipmentBag<Equipment> equipmentBag) {
        this.equipmentBag = equipmentBag;
    }


    // equals method that takes an Object as a parameter
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Researcher that = (Researcher) obj;
        return id == that.id &&
                currentRow == that.currentRow &&
                currentColumn == that.currentColumn &&
                isTired == that.isTired &&
                equipmentBag.equals(that.equipmentBag);
    }

    // equals method that takes a Researcher as a parameter
    public boolean equals(Researcher other) {
        if(other == null) return false;
        return id == other.id &&
                currentRow == other.currentRow &&
                currentColumn == other.currentColumn &&
                isTired == other.isTired &&
                equipmentBag.equals(other.equipmentBag);
    }

    // toString method
    @Override
    public String toString() {
        return "R" + id;
    }

    // clone method
    @Override
    public Researcher clone() {
        return new Researcher(this);
    }

    // Method to add equipment to the equipment bag
    public void addEquipment(Equipment equipment) {
        equipmentBag.add(equipment);
    }

    // Method to remove equipment from the equipment bag
    public void removeEquipment(Equipment equipment) {
        equipmentBag.remove(equipment);
    }

    /**
     * Slides the researcher in the given direction on the frozen lake.
     * 
     * @param direction The direction in which the researcher should slide.
     * @param lake The frozen lake on which the researcher is sliding.
     * @throws UnavailableDirectionException If the direction is not valid.
     */
    public void slide(Direction direction, FrozenLake lake) throws UnavailableDirectionException  {
        
        lake.getSquare(currentRow, currentColumn).getEffectables().remove(this);

        if (isTired) {
            System.out.println("Researcher " + id + " is tired and cannot slide.");
            return;
        }

        int dRow = direction.getDRow();
        int dCol = direction.getDCol();

        // Check if the direction is valid
        if (!lake.isValidDirection(currentRow, currentColumn, dRow, dCol)) {
            throw new UnavailableDirectionException("*** The input direction is unavailable. Please enter an available direction:");
        }
        boolean isSlide = true;
        while (isSlide) {
            int newRow = currentRow + dRow;
            int newCol = currentColumn + dCol;

            // Get the object at the new position which represents the square which is the researcher intends to move
            
            ISquare square = lake.getSquare(newRow, newCol);
            if (square.getEffectables().size() > 0) {
                    isSlide = false;
                    
                
                   List<IEffectable> listOfTheSquare = new ArrayList<>(square.getEffectables());
                    for (IEffectable effectable : listOfTheSquare) {
                        if (effectable.effect(this, square) == 0) {
                            newRow = currentRow;
                            newCol = currentColumn;
                            break;
                        } else {
                            newRow = currentRow + dRow;
                            newCol = currentColumn + dCol;
                        }
                    }
 
                }   
                setCurrentColumn(newCol);
                setCurrentRow(newRow);
                    // Check if the researcher reached the entrance
                if (lake.isEntrance(currentRow, currentColumn)) {
                        System.out.println("Researcher " + id + " returned to the entrance.");
                        isTired = true;
                        break;
                }   
            }

        //add the researcher to the square which he/she ended up 
        lake.getSquare(currentRow, currentColumn).addEffectable(this);
    }
    
    /**
     * Performs an experiment by placing the required equipment on the specified lake square.
     *
     * @param experiment The experiment to be performed.
     * @param lake The frozen lake where the experiment is conducted.
     * @param square The specific lake square where the equipment is to be placed.
     * @return The experiment after attempting to place the equipment.
     * @throws UnavailableEquipmentException If the required equipment is not available in the equipment bag.
     */
    public Experiment performExperiment(Experiment experiment, FrozenLake lake, LakeSquare square) throws UnavailableEquipmentException{
        Equipment requiredEquipment = equipmentBag.getRequiredEquipment(experiment);
        if(requiredEquipment == null){
            throw new UnavailableEquipmentException("Required equipment is not available.");
        }
        try{
            experiment.validatePlacement(lake, square); 
            equipmentBag.remove(requiredEquipment);
            lake.getSquare(currentRow, currentColumn).getEffectables().add(requiredEquipment);
            System.out.println("--- The selected research equipment has been placed in the current location.");
            return experiment;
        }catch(IncompatibleResearchEquipmentLocationException e){
            System.out.println(e.getMessage());
            System.out.println("*** The selected research equipment is incompatible with the current location.");
        }
        return null;
        
    }

    /**
     * Applies the effect of the researcher on the given square.
     *
     * @param researcher the researcher on which the effect is applied
     * @param square the square on which the effect is applied
     * @return an integer representing the result of the effect
     * Return 0 because other researchers will not move this square. 
     */
    @Override
    public int effect(Researcher researcher, ISquare square) {
        return 0;
    }

    /**
     * This method simulates the injury of a researcher.
     * It prints a message indicating that the researcher is injured and the game has failed.
     * It also prints an error message in red color and terminates the program.
     */
    public void injure() {
        System.out.println("The researcher injured. Game failed :(");
        System.err.println("\u001B[31m-----------> UNSUCCESSFUL\u001B[0m");
        System.exit(0);
    }
}

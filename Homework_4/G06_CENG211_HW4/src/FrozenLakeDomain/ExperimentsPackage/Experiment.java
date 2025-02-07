package FrozenLakeDomain.ExperimentsPackage;

import FrozenLakeDomain.ExceptionsPackage.IncompatibleResearchEquipmentLocationException;
import FrozenLakeDomain.LakePackage.FrozenLake;
import FrozenLakeDomain.LakePackage.LakeSquare;
import FrozenLakeDomain.ResearcherPackage.Equipment;

/**
 * The Experiment class represents an abstract base class for experiments
 * that utilize research equipment. It provides constructors, getter and setter
 * methods, and abstract methods for validating placement and displaying results.
 * 
 */
public abstract class Experiment {
    private ResearchEquipment researchEquipment;
    
    // Default constructor
    public Experiment() {
        researchEquipment = null;
    }

    // Parameterized constructor
    public Experiment(ResearchEquipment researchEquipment) {
        this.researchEquipment = researchEquipment;
    }

    //Copy constructor
    public Experiment(Experiment other) {
        if(other == null){
            System.out.println("Fatal error: null Experiment object passed to copy constructor");
            System.exit(1);
        }
        this.researchEquipment = other.researchEquipment; 
    }

    // Getter and setter methods
    public ResearchEquipment getResearchEquipment() {
        return researchEquipment;
    }

    public void setResearchEquipment(ResearchEquipment researchEquipment) {
        this.researchEquipment = researchEquipment;
    }

    // equals method that takes an object as parameter
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Experiment that = (Experiment) obj;
        return researchEquipment.equals(that.researchEquipment);
    }

    // equals method that takes an Experiment as parameter
    public boolean equals(Experiment other) {
        if (other == null) {
            return false;
        }
        return researchEquipment.equals(other.researchEquipment);
    }

    // toString method
    @Override
    public String toString() {
        return "Experiment{" +
                "researchEquipment=" + researchEquipment +
                '}';
    }

    public Equipment getEquipment() {
        return researchEquipment;
    }
    
    /**
     * Validates the placement of research equipment on a given square in the frozen lake.
     *
     * @param frozenLake the frozen lake where the equipment is to be placed
     * @param square the specific square on the frozen lake where the equipment is to be placed
     * @throws IncompatibleResearchEquipmentLocationException if the placement of the equipment is not compatible with the given square
     */
    public abstract void validatePlacement(FrozenLake frozenLake, LakeSquare square) throws IncompatibleResearchEquipmentLocationException;
    /**
     * Displays the results of the experiment.
     * 
     * @return A string representation of the experiment results.
     */
    public abstract String displayResults();
    
}

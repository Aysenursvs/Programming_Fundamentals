package FrozenLakeDomain.HazardsPackage;

import FrozenLakeDomain.InterfacesPackage.IEffectable;
import FrozenLakeDomain.InterfacesPackage.ISquare;
import FrozenLakeDomain.ResearcherPackage.Equipment;
import FrozenLakeDomain.ResearcherPackage.Researcher;

public abstract class Hazard implements IEffectable{
    private HazardEquipment requiredHazardEquipment;

    // Default constructor
    public Hazard() {
        this.requiredHazardEquipment = null;
    }

    // Parameterized constructor
    public Hazard(HazardEquipment requirHazardEquipment) {
        this.requiredHazardEquipment = requirHazardEquipment;
    }

    // Copy constructor
    public Hazard(Hazard other) {
        if (other == null) {
            System.out.println("Fatal error: null Hazard object passed to copy constructor");
            System.exit(1);
        }
        this.requiredHazardEquipment = other.requiredHazardEquipment;
    }

    // Getter and setter methods
    public HazardEquipment getRequiredHazardEquipment() {
        return requiredHazardEquipment;
    }

    public void setRequiredHazardEquipment(HazardEquipment requiredHazardEquipment) {
        this.requiredHazardEquipment = requiredHazardEquipment;
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
        Hazard that = (Hazard) obj;
        return requiredHazardEquipment.equals(that.requiredHazardEquipment);
    }

    // equals method that takes a Hazard as parameter
    public boolean equals(Hazard other) {
        if (other == null) {
            return false;
        }
        return requiredHazardEquipment.equals(other.requiredHazardEquipment);
    }

    // toString method
    @Override
    public String toString() {
        return "Hazard{" +
                "requiredHazardEquipment=" + requiredHazardEquipment +
                '}';
    }

    public Equipment getEquipment() {
        return requiredHazardEquipment;
    }
    
    public abstract int effect(Researcher researcher, ISquare square);

}

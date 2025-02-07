package FrozenLakeDomain.HazardsPackage;

import FrozenLakeDomain.InterfacesPackage.ISquare;
import FrozenLakeDomain.ResearcherPackage.Researcher;

/**
 * The CliffEdge class represents a specific type of hazard in the Frozen Lake Domain.
 * It extends the Hazard class and requires ClimbingEquipment to be safely navigated.
 * 
 */
public class CliffEdge extends Hazard {

    // Default constructor
    public CliffEdge() {
        super();
        setRequiredHazardEquipment(new ClimbingEquipment());
    }

    // Parameterized constructor
    public CliffEdge(ClimbingEquipment requiredHazardEquipment) {
        super();
        setRequiredHazardEquipment(requiredHazardEquipment);
    }

    // Copy constructor
    public CliffEdge(CliffEdge other) {
        super(other);  
    }

    //Getters and Setters
    public ClimbingEquipment getRequiredHazardEquipment() {
        return (ClimbingEquipment) super.getRequiredHazardEquipment();
    }

    public void setRequiredHazardEquipment(ClimbingEquipment requiredHazardEquipment) {
        super.setRequiredHazardEquipment(requiredHazardEquipment);
    }

    //clone method
    public CliffEdge clone() {
        return new CliffEdge(this);
    }

    public String toString() {
        return "CE";
    }

    /**
     * Applies the effect of the cliff edge on the researcher.
     * If the researcher has the required hazard equipment, it is used to cover the cliff,
     * and the equipment is removed from the researcher's bag and added to the square's effectables.
     * If the researcher does not have the required equipment, the researcher is injured.
     * 
     * @param researcher The researcher encountering the cliff edge.
     * @param square The square where the cliff edge is located.
     * @return 0 because cannot move since the next square is cliff
     */
    public int effect(Researcher researcher, ISquare square) {
        if (researcher.getEquipmentBag().contains(getRequiredHazardEquipment())) {
            square.getEffectables().add(getRequiredHazardEquipment());
            researcher.getEquipmentBag().remove(getRequiredHazardEquipment());
            square.getEffectables().remove(this);
        } else {
            researcher.injure();
        }
        System.out.println("!!! Researcher " + researcher.getId() +  " comes to a cliff edge. However, Researcher " + researcher.getId() + " is carrying a Climbing Equipment. \r\n" + //
                        "Researcher " + researcher.getId() + " covers the cliff with the climbing equipment and climbed. ");
        return 0;
    }
}

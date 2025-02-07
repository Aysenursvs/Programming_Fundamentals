package FrozenLakeDomain.HazardsPackage;

import FrozenLakeDomain.InterfacesPackage.ISquare;
import FrozenLakeDomain.ResearcherPackage.Researcher;

public class HoleInIce extends Hazard{

    // Default constructor
    public HoleInIce() {
        super();
        setRequiredHazardEquipment(new LargeWoodenBoard());
    }
    
    // Parameterized constructor
    public HoleInIce(LargeWoodenBoard equipment) {
        super(equipment);
    }

    // Copy constructor
    public HoleInIce(HoleInIce other) {
        super(other);
    }

    //getter and setter
    public LargeWoodenBoard getRequiredHazardEquipment() {
        return (LargeWoodenBoard) super.getRequiredHazardEquipment();
    }

    public void setRequiredHazardEquipment(LargeWoodenBoard requiredHazardEquipment) {
        super.setRequiredHazardEquipment(requiredHazardEquipment);
    }

    //clone method
    public HoleInIce clone() {
        return new HoleInIce(this);
    }

    /**
     * Applies the effect of the hazard on the researcher when they encounter a hole in the ice.
     * If the researcher has the required hazard equipment, they use it to cover the hole and cross safely.
     * Otherwise, the researcher gets injured.
     *
     * @param researcher The researcher encountering the hazard.
     * @param square The square where the hazard is located.
     * @return returns 1 if the researcher successfully handle the hazard and now can stay on the hazard , if can't will get injure
     */
    @Override
    public int effect(Researcher researcher, ISquare square) {
        if (researcher.getEquipmentBag().contains(getRequiredHazardEquipment())) {
            square.getEffectables().add(getRequiredHazardEquipment());
            researcher.getEquipmentBag().remove(getRequiredHazardEquipment());
            square.getEffectables().remove(this);
        } else {
            researcher.injure();
        }
        System.out.println("!!! Researcher " + researcher.getId() +  " comes to a hole in ice. However, Researcher " + researcher.getId() + " is carrying a Large Wooden Board. \r\n" + //
                        "Researcher " + researcher.getId() + " covers the hole with the large wooden board and crossed. ");
        return 1;
    }

    public String toString(){
        return "HI";
    }
}

package FrozenLakeDomain.HazardsPackage;

import FrozenLakeDomain.InterfacesPackage.ISquare;
import FrozenLakeDomain.ResearcherPackage.Researcher;

public class IceSpike extends Hazard{

    // Default constructor
    public IceSpike() {
        super();
        setRequiredHazardEquipment(new ProtectiveHelmet());
    }

    //Parameterized constructor
    public IceSpike(ProtectiveHelmet requiredHazardEquipment) {
        super(requiredHazardEquipment);
    }

    // Copy constructor
    public IceSpike(IceSpike other) {
        super(other);
    }

    //getter and setter
    public ProtectiveHelmet getRequiredHazardEquipment() {
        return (ProtectiveHelmet) super.getRequiredHazardEquipment();
    }

    public void setRequiredHazardEquipment(ProtectiveHelmet requiredHazardEquipment) {
        super.setRequiredHazardEquipment(requiredHazardEquipment);
    }

    //clone method
    public IceSpike clone() {
        return new IceSpike(this);
    }
    
    /**
     * Applies the effect of the IceSpike hazard on the given researcher and square.
     * If the researcher has the required hazard equipment, it is removed from their equipment bag
     * and the IceSpike is removed from the square. Otherwise, the researcher is injured.
     * 
     * @param researcher the researcher encountering the IceSpike
     * @param square the square containing the IceSpike
     * @return when the effect is successfully done, the method returns 1 because the researcher will move to the next square.
     */
    @Override
    public int effect(Researcher researcher, ISquare square) {
        if (researcher.getEquipmentBag().contains(getRequiredHazardEquipment())) {
            researcher.getEquipmentBag().remove(getRequiredHazardEquipment());
            square.getEffectables().remove(this);
            
        } else {
            researcher.injure();
        }
        System.out.println("!!! Researcher " + researcher.getId() +  " comes to an ice spike. However, Researcher " + researcher.getId() + " is carrying a Protective Helmet. \r\n" + //
                        "Researcher " + researcher.getId() + " covers the ice spike with the protective helmet and crossed. ");
        return 1;
    }

    public String toString(){
        return "IS";
    }
}

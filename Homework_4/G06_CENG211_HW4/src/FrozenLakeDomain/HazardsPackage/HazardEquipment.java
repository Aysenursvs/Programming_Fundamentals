package FrozenLakeDomain.HazardsPackage;

import FrozenLakeDomain.InterfacesPackage.ISquare;
import FrozenLakeDomain.ResearcherPackage.Equipment;
import FrozenLakeDomain.ResearcherPackage.Researcher;

public abstract class HazardEquipment extends Equipment {

    // Default constructor
    public HazardEquipment() {
        super();
    }

    // Copy constructor
    public HazardEquipment(HazardEquipment other) {
        super(other);
    }

    public abstract int effect(Researcher researcher, ISquare square);
    public abstract String displayEquipment();
}

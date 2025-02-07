package FrozenLakeDomain.HazardsPackage;

import FrozenLakeDomain.InterfacesPackage.ISquare;
import FrozenLakeDomain.ResearcherPackage.Researcher;

public class IceBlock extends Hazard{

    // Default constructor
    public IceBlock() {
        super();
    }

    // Parameterized constructor
    public IceBlock(HazardEquipment requirHazardEquipment) {
        super(requirHazardEquipment);
    }
    
    // Copy constructor
    public IceBlock(IceBlock other) {
        super(other);
    }

    //clone method
    public IceBlock clone(){
        return new IceBlock(this);
    }

    /**
     * Applies the effect of the IceBlock on the given researcher and square.
     * Returns 0 means that do not move to next square and stay where you at.
     */
    @Override
    public int effect(Researcher researcher, ISquare square) {
        return 0;
    }

    public String toString(){
        return "IB";
    }
}

package FrozenLakeDomain.HazardsPackage;

import FrozenLakeDomain.InterfacesPackage.ISquare;
import FrozenLakeDomain.ResearcherPackage.Researcher;

public class ClimbingEquipment extends HazardEquipment {
    
     // Default constructor
    public ClimbingEquipment() {
        super();
        setSymbol("cl");
    }

    // Parameterized constructor
    public ClimbingEquipment(String symbol) {
        super();
        setSymbol(symbol);
    }

    // Copy constructor
    public ClimbingEquipment(ClimbingEquipment other) {
        super(other);
    }

    /**
     * Applies the effect of the climbing equipment on the given researcher and square.
     * return 0 because cannot go to next square since it is cliff
     */
    @Override
    public int effect(Researcher researcher, ISquare square) {
        return 0;
    }

    public String toString(){
        return getSymbol();
    }

    public String displayEquipment(){
        return getSymbol() + " Climbing Equipment";
    }
}

package FrozenLakeDomain.HazardsPackage;

import FrozenLakeDomain.InterfacesPackage.ISquare;
import FrozenLakeDomain.ResearcherPackage.Researcher;

public class ProtectiveHelmet extends HazardEquipment {

    // Default constructor
    public ProtectiveHelmet() {
        super();
        setSymbol("ph");
    }

    // Parameterized constructor
    public ProtectiveHelmet(String symbol) {
        super();
        setSymbol(symbol);
    }

    // Copy constructor
    public ProtectiveHelmet(ProtectiveHelmet other) {
        super(other);
    }

    /**
     * Applies the effect of the protective helmet on the given researcher and square.
     * return 1 means researcher can stay at the square with protective helmet if there is
     */
    @Override
    public int effect(Researcher researcher, ISquare square) {
        return 1;
    }

    public String toString(){
        return getSymbol();
    }

    public String displayEquipment(){
        return getSymbol() + " Protective Helmet";
    }
}

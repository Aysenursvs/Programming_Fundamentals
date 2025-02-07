package FrozenLakeDomain.HazardsPackage;

import FrozenLakeDomain.InterfacesPackage.ISquare;
import FrozenLakeDomain.ResearcherPackage.Researcher;

public class LargeWoodenBoard extends HazardEquipment {
    
    // Default constructor
    public LargeWoodenBoard() {
        super();
        setSymbol("wb");
    }

    // Parameterized constructor
    public LargeWoodenBoard(String symbol) {
        super();
        setSymbol(symbol);
    }

    // Copy constructor
    public LargeWoodenBoard(LargeWoodenBoard other) {
        super(other);
    }

    /**
     * Applies the effect of the Large Wooden Board on the given researcher and square.
     * return 1 means that move to next square , in this case: Move to the square which has large wooden board
     */
    @Override
    public int effect(Researcher researcher, ISquare square) {
        return 1;
    }

    public String toString(){
        return getSymbol();
    }

    public String displayEquipment(){
        return getSymbol() + " Large Wooden Board";
    }
    
}

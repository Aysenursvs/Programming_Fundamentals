package FrozenLakeDomain.ResearcherPackage;

import FrozenLakeDomain.InterfacesPackage.IEffectable;
import FrozenLakeDomain.InterfacesPackage.ISquare;

/**
 * The Equipment class represents an abstract piece of equipment with a symbol.
 * It implements the IEffectable interface and provides methods to get and set the symbol,
 * as well as to compare equipment objects.
 * 
 */
public abstract class Equipment implements IEffectable{
    private String symbol;

    public Equipment() {
        this.symbol = "";
    }

    public Equipment(String symbol) {
        this.symbol = symbol;
    }

    public Equipment(Equipment other) {
        if (other == null) {
            System.out.println("Fatal Error: Null object");
            System.exit(0);
        }
        this.symbol = other.symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    //equals method that takes an object as a parameter
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Equipment equipment = (Equipment) obj;
        return symbol.equals(equipment.symbol);
    }

    //equals method that takes an Equipment as a parameter
    public boolean equals(Equipment other) {
        if(other == null) return false;
        return symbol.equals(other.symbol);
    }

    public abstract int effect(Researcher researcher, ISquare square);
    public abstract String displayEquipment();

}

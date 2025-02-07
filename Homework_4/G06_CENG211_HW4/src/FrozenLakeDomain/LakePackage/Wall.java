package FrozenLakeDomain.LakePackage;

import FrozenLakeDomain.InterfacesPackage.IEffectable;
import FrozenLakeDomain.InterfacesPackage.ISquare;
import FrozenLakeDomain.ResearcherPackage.Researcher;

/**
 * The Wall class represents an immovable object in the Frozen Lake Domain.
 * It implements the IEffectable interface, indicating that it can have an effect
 * on a Researcher object when interacted with.
 * 
 * The Wall is represented by the character "W" when converted to a string.
 */
public class Wall implements IEffectable{

    public String toString(){
        return "W"; 
    }

    /**
     * Applies the effect of the wall on the given researcher and square.
     * when tried to move to next square but if there is a wall the method returns 0 so that researcher stays where he/she at
     */
    @Override
    public int effect(Researcher researcher, ISquare square) {
        return 0;
    }
}

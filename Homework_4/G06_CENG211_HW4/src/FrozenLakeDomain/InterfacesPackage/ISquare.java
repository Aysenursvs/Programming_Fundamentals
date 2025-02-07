package FrozenLakeDomain.InterfacesPackage;

import java.util.List;

public interface ISquare {
    
    /**
     * Checks if the square contains any objects or entities.
     *
     * @return true if the square contains any objects or entities, false otherwise.
     */
    public boolean containsAny();
    
    /**
     * Checks if the square contains any hazards.
     *
     * @return true if the square contains any hazards, false otherwise.
     */
    public boolean containsAnyHazard();
    
    /**
     * Retrieves the hazard associated with the given hazard name.
     *
     * @param hazardName the name of the hazard to retrieve
     * @return an IEffectable object representing the hazard, or null if no such hazard exists
     */
    public IEffectable getHazard(String hazardName);
    
    /**
     * Adds an effectable object to the square.
     *
     * @param effectable the effectable object to be added
     */
    public void addEffectable(IEffectable effectable);
    
    /**
     * Creates and returns a copy of this ISquare object.
     */
    public ISquare clone();
    
    /**
     * Retrieves a list of objects that implement the IEffectable interface.
     * 
     * @return a List of IEffectable objects.
     */
    public List<IEffectable> getEffectables();
}

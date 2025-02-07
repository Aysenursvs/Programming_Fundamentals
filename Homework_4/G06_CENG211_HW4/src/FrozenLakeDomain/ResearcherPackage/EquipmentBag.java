package FrozenLakeDomain.ResearcherPackage;

import java.util.ArrayList;
import java.util.List;

import FrozenLakeDomain.ExperimentsPackage.Experiment;
import FrozenLakeDomain.ExperimentsPackage.ResearchEquipment;
import FrozenLakeDomain.HazardsPackage.Hazard;
import FrozenLakeDomain.HazardsPackage.HazardEquipment;

/**
 * The EquipmentBag class represents a collection of equipment items.
 * It provides methods to add, remove, and retrieve equipment items, as well as
 * methods to check for the presence of specific equipment items.
 *
 * @param <T> the type of equipment that this bag can hold, which must extend the Equipment class
 */
public class EquipmentBag<T extends Equipment> {

    List<T> equipmentBag;

    // Default constructor
    public EquipmentBag() {
        equipmentBag = new ArrayList<T>();
    }

    // Parameterized constructor
    public EquipmentBag(List<T> equipmentBag) {
        this.equipmentBag = equipmentBag;
    }

    // Copy constructor
    public EquipmentBag(EquipmentBag<T> other) {
        if (other == null) {
            System.out.println("Fatal error: null EquipmentBag object passed to copy constructor");
            System.exit(1);
        }
        this.equipmentBag = new ArrayList<T>();
        for (T equipment : other.equipmentBag) {
            this.equipmentBag.add(equipment);
        }
    }

    // Getter and setter methods
    public List<T> getEquipmentBagList() {
        List<T> equipmentBagCopy = new ArrayList<T>();
        for (T equipment : equipmentBag) {
            equipmentBagCopy.add(equipment);
        }
        return equipmentBagCopy;
    }

    public void setEquipmentBag(List<T> equipmentBag) {
        this.equipmentBag = equipmentBag;
    }

    // equals method that takes an object as parameter
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        EquipmentBag<T> that = (EquipmentBag<T>) obj;
        return equipmentBag.equals(that.equipmentBag);
    }

    // equals method that takes an EquipmentBag as parameter
    public boolean equals(EquipmentBag<T> other) {
        if (other == null) {
            return false;
        }
        return equipmentBag.equals(other.equipmentBag);
    }

    // Method to return a comma-separated string of equipment
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < equipmentBag.size(); i++) {
            sb.append(equipmentBag.get(i).toString());
            if (i < equipmentBag.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    //clone method
    @Override
    public EquipmentBag<T> clone() {
        return new EquipmentBag<T>(this);
    }


    //add and remove methods
    public boolean add(T equipment) {
        return equipmentBag.add(equipment);
    }

    public void remove(T equipment) {
        if (equipmentBag.contains(equipment)) {
            equipmentBag.remove(equipment);
        }
    }

    /**
     * Retrieves the required equipment from the equipment bag for a given event.
     * The event can be any hazard or experiment, but not both.
     * so we overloaded the method one for Hazard one for Experiment
     *
     * @param <E>   the type of the event, which extends Hazard
     * @param event the event for which the required equipment is needed
     * @return the required equipment if found in the equipment bag, otherwise null
     */
    public <E extends Hazard> T getRequiredEquipment(E event) { 
        for (T equipment : equipmentBag) {
            if (event.getEquipment().equals(equipment)) {
                return equipment;
            }
        }
        return null;
    }

    /**
     * Retrieves the required equipment for a given experiment.
     *
     * @param <E>   the type of the experiment, which extends the Experiment class
     * @param event the experiment for which the required equipment is needed
     * @return the required equipment if found in the equipment bag, otherwise null
     */
    public <E extends Experiment> T getRequiredEquipment(E event) { 
        for (T equipment : equipmentBag) {
            if (event.getEquipment().equals(equipment)) {
                return equipment;
            }
        }
        return null;
    }

    /**
     * Checks if the equipment bag contains an item with the specified key.
     *
     * @param key the symbol of the equipment to search for
     * @return true if the equipment bag contains an item with the specified key, false otherwise
     */
    public boolean contains(String key) {
        for (T equipment : equipmentBag) {
            if (equipment.getSymbol().equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the specified equipment is present in the equipment bag.
     *
     * @param equipment the equipment to be checked for presence in the bag
     * @return true if the equipment is present in the bag, false otherwise
     */
    public boolean contains(T equipment) {
        return equipmentBag.contains(equipment);
    }

    /**
     * Checks if the equipment bag contains any research equipment.
     *
     * @return true if the equipment bag contains at least one item that is an instance of ResearchEquipment, false otherwise.
     */
    public boolean containsResearcherEquipment() {
        for (T equipment : equipmentBag) {
            if (equipment instanceof ResearchEquipment) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves an equipment from the equipment bag that matches the given key.
     *
     * @param key the symbol of the equipment to be retrieved
     * @return the equipment that matches the given key, or null if no such equipment is found
     */
    public Equipment getEquipmentWithString(String key) {
        for (T equipment : equipmentBag) {
            if (equipment.getSymbol().equals(key)) {
                return equipment;
            }
        }
        return null;
    }

    public int size(){
        return equipmentBag.size();
    }

    /**
     * Checks if the type of the first equipment in the equipment bag matches the type of equipment
     * stored in the provided equipment storage based on the given key.
     * We have provided this method to check if the researcher has added another object of the same type as the object he/she added before.
     *
     * @param key the key to check in the equipment storage, case-insensitive
     * @param equipmentStorage the equipment storage to check against
     * @return true if the type of the first equipment in the bag
     *         matches the type of equipment stored in the equipment storage with the given key;
     *         false otherwise
     */
    public boolean checkType(String key, EquipmentStorage equipmentStorage){
        key = key.toLowerCase();
        if(equipmentBag.size() > 0){
            if(equipmentBag.get(0) instanceof HazardEquipment && equipmentStorage.getHazardEquipmentStorage().containsKey(key)) return true;
            else if (equipmentBag.get(0) instanceof ResearchEquipment && equipmentStorage.getResearchEquipmentStorage().containsKey(key)) return true;
            else return false; 
        }
        else return true;
    }  
}

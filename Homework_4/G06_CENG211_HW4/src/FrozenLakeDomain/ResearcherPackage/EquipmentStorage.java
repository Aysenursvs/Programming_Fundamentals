package FrozenLakeDomain.ResearcherPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import FrozenLakeDomain.ExceptionsPackage.UnavailableEquipmentException;
import FrozenLakeDomain.ExperimentsPackage.Camera;
import FrozenLakeDomain.ExperimentsPackage.ChiselingEquipment;
import FrozenLakeDomain.ExperimentsPackage.TemperatureDetector;
import FrozenLakeDomain.ExperimentsPackage.WindSpeedDetector;
import FrozenLakeDomain.HazardsPackage.ClimbingEquipment;
import FrozenLakeDomain.HazardsPackage.LargeWoodenBoard;
import FrozenLakeDomain.HazardsPackage.ProtectiveHelmet;

/**
 * The EquipmentStorage class manages the storage and retrieval of research and hazard equipment.
 * It maintains two separate inventories for research and hazard equipment, and provides methods
 * to initialize, create, insert, take, and display equipment from these inventories.
 *  
 * Note: The class uses synchronized methods to ensure thread safety for equipment retrieval and insertion.
 */
public class EquipmentStorage {
    private Map<String, List<Equipment>> researchEquipmentInventory;
    private Map<String, List<Equipment>> hazardEquipmentInventory;

    /**
     * Initializes the equipment storage by creating and populating the research and hazard
     * equipment inventories. The research equipment inventory is populated with keys "td", "ws",
     * "cm", and "ch", each associated with an empty list of Equipment objects. The hazard equipment
     * inventory is populated with keys "cl", "wb", and "ph", each associated with an empty list of
     * Equipment objects.
     */
    private void initializeEquipmentStorage() {
        researchEquipmentInventory = new HashMap<>();
        hazardEquipmentInventory = new HashMap<>();
        researchEquipmentInventory.put("td", new ArrayList<Equipment>());
        researchEquipmentInventory.put("ws", new ArrayList<Equipment>());
        researchEquipmentInventory.put("cm", new ArrayList<Equipment>());
        researchEquipmentInventory.put("ch", new ArrayList<Equipment>());
        hazardEquipmentInventory.put("cl", new ArrayList<Equipment>());
        hazardEquipmentInventory.put("wb", new ArrayList<Equipment>());
        hazardEquipmentInventory.put("ph", new ArrayList<Equipment>());
    }

    /**
     * Populates the equipment storage with various types of research and hazard equipment.
     * 
     * The method adds:
     * - 2 Temperature Detectors to the research equipment inventory under the key "td".
     * - 2 Wind Speed Detectors to the research equipment inventory under the key "ws".
     * - 2 Cameras to the research equipment inventory under the key "cm".
     * - 2 Chiseling Equipment to the research equipment inventory under the key "ch".
     * - 2 Climbing Equipment to the hazard equipment inventory under the key "cl".
     * - 2 Large Wooden Boards to the hazard equipment inventory under the key "wb".
     * - 2 Protective Helmets to the hazard equipment inventory under the key "ph".
     */
    private void createEquipmentStorage() {
        for (int i = 0; i < 14; i++) {
            if (i < 2) {
                researchEquipmentInventory.get("td").add(new TemperatureDetector());
            } 
            else if (i < 4) {
                researchEquipmentInventory.get("ws").add(new WindSpeedDetector());
            } 
            else if (i < 6) {
                researchEquipmentInventory.get("cm").add(new Camera());
            } 
            else if (i < 8) {
                researchEquipmentInventory.get("ch").add(new ChiselingEquipment());
            } 
            else if (i < 10) {
                hazardEquipmentInventory.get("cl").add(new ClimbingEquipment());
            } 
            else if (i < 12) {
                hazardEquipmentInventory.get("wb").add(new LargeWoodenBoard());
            } 
            else {
                hazardEquipmentInventory.get("ph").add(new ProtectiveHelmet());
            }
        }
    }

    //Default constructor
    public EquipmentStorage(){
        initializeEquipmentStorage();
        createEquipmentStorage();
    }
    //Parameterized constructor
    public EquipmentStorage(Map<String, List<Equipment>> researchEquipmentInventory, Map<String, List<Equipment>> hazardEquipmentInventory){
        this.researchEquipmentInventory = new HashMap<>(researchEquipmentInventory);
        this.hazardEquipmentInventory = new HashMap<>(hazardEquipmentInventory);
    }
    //Copy constructor
    public EquipmentStorage(EquipmentStorage other){
        if(other == null){
            System.out.println("Fatal error: null EquipmentStorage object passed to copy constructor");
            System.exit(1);
        }
        this.researchEquipmentInventory = new HashMap<>(other.researchEquipmentInventory);
        this.hazardEquipmentInventory = new HashMap<>(other.hazardEquipmentInventory);
    }
    //Getter and setter methods
    public Map<String, List<Equipment>> getResearchEquipmentStorage(){
        return new HashMap<>(researchEquipmentInventory);
    }

    public void setResearchEquipmentStorage(Map<String, List<Equipment>> equipmentStorage){
        this.researchEquipmentInventory.clear();
        this.researchEquipmentInventory.putAll(equipmentStorage);
    }

    public Map<String, List<Equipment>> getHazardEquipmentStorage(){
        return new HashMap<>(hazardEquipmentInventory);
    }

    public void setHazardEquipmentStorage(Map<String, List<Equipment>> equipmentStorage){
        this.hazardEquipmentInventory.clear();
        this.hazardEquipmentInventory.putAll(equipmentStorage);
    }

    //equals method that takes an object as parameter
    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        EquipmentStorage equipmentInventory = (EquipmentStorage) obj;
        return researchEquipmentInventory.equals(equipmentInventory.researchEquipmentInventory) && hazardEquipmentInventory.equals(equipmentInventory.hazardEquipmentInventory);
    }

    //equals method that takes an EquipmentStorage object as parameter
    public boolean equals(EquipmentStorage equipmentInventory){
        if(this == equipmentInventory){
            return true;
        }
        if(equipmentInventory == null){
            return false;
        }
        return researchEquipmentInventory.equals(equipmentInventory.researchEquipmentInventory) && hazardEquipmentInventory.equals(equipmentInventory.hazardEquipmentInventory);
    }

    //toString method
    @Override
    public String toString(){
        return "EquipmentStorage: " +
                "researchEquipmentInventory:" + researchEquipmentInventory +
                ", hazardEquipmentInventory:" + hazardEquipmentInventory;
    }

    //clone method
    @Override
    public EquipmentStorage clone(){
        return new EquipmentStorage(this);
    }

    /**
     * Takes an equipment from the storage based on the provided key.
     * The method is synchronized to ensure thread safety.
     *
     * @param key the key representing the equipment to be taken.
     * @return the equipment corresponding to the provided key.
     * @throws UnavailableEquipmentException if the equipment is not available or the key is invalid.
     */
    public synchronized Equipment takeEquipment(String key) throws UnavailableEquipmentException {
        if (!researchEquipmentInventory.containsKey(key.toLowerCase()) && !hazardEquipmentInventory.containsKey(key.toLowerCase())) {
            throw new UnavailableEquipmentException("Invalid equipment: " + key);
        }
    
        key = key.toLowerCase();
        Equipment removedEquipment = null;
    
        if (researchEquipmentInventory.containsKey(key)) {
            if (researchEquipmentInventory.get(key).size() > 0) {
                removedEquipment = researchEquipmentInventory.get(key).remove(0);
            } else {
                throw new UnavailableEquipmentException("There are no more " + key + " left in the Equipment Storage.");
            }
        } else if (hazardEquipmentInventory.containsKey(key)) {
            if (hazardEquipmentInventory.get(key).size() > 0) {
                removedEquipment = hazardEquipmentInventory.get(key).remove(0);
            } else {
                throw new UnavailableEquipmentException("There are no more " + key + " left in the Equipment Storage.");
            }
        }
    
        if (removedEquipment == null) {
            throw new UnavailableEquipmentException("Equipment with key '" + key + "' could not be retrieved.");
        }
    
        return removedEquipment;
    }
    
    /**
     * Inserts the specified equipment into the appropriate inventory based on the provided key.
     * The method is synchronized to ensure thread safety.
     *
     * @param key The key representing the type of equipment. ,
     * @param equipment The equipment object to be inserted into the inventory.
     * @throws IllegalArgumentException if the key is not recognized as a valid equipment type.
     */
    public synchronized void insertEquipment(String key, Equipment equipment) {
        if (key.toLowerCase().equals("td") || key.toLowerCase().equals("ws") || key.toLowerCase().equals("cm") || key.toLowerCase().equals("ch")) {
            researchEquipmentInventory.get(key.toLowerCase()).add(equipment);
        } 
        else if (key.toLowerCase().equals("cl") || key.toLowerCase().equals("wb") || key.toLowerCase().equals("ph")) {
            hazardEquipmentInventory.get(key.toLowerCase()).add(equipment);
        }
        else {
            System.out.println("Invalid equipment key: " + key);
        }
    }

    /**
     * Inserts all equipment from the provided list into the storage.
     *
     * @param equipmentList the list of equipment to be inserted
     */
    public void insertAllEquipment(List<Equipment> equipmentList) {
        for (Equipment equipment : equipmentList) {
            insertEquipment(equipment.getSymbol(), equipment);
        }
    }

    /**
     * Displays the inventory of research and hazard equipment.
     * This method prints out the shorter notations of the equipment
     * from both research and hazard equipment inventories.
     * It iterates through each inventory and prints the notation
     * of the first equipment in each list if the list is not empty.
     * This method is synchronized to ensure thread safety.
     */
    public synchronized void displayInventory() {
        System.out.println("Here are the shorter notations of the equipments:");
        for (Map.Entry<String, List<Equipment>> researchEntry : researchEquipmentInventory.entrySet()) {
            if (researchEntry.getValue().size() > 0) {
                System.out.println(researchEntry.getValue().get(0).displayEquipment());
            }
        }

        for (Map.Entry<String, List<Equipment>> hazardEntry : hazardEquipmentInventory.entrySet()) {
            if (hazardEntry.getValue().size() > 0) {
                System.out.println(hazardEntry.getValue().get(0).displayEquipment());
            }
        }
    }

    /**
     * Checks if the specified equipment is present in either the research or hazard equipment inventories.
     *
     * @param key the name of the equipment to check for
     * @return true if the equipment is found in either inventory and has a size greater than 0, false otherwise
     */
    public boolean contains(String key) {
        if (researchEquipmentInventory.containsKey(key.toLowerCase())) {
            return researchEquipmentInventory.get(key.toLowerCase()).size() > 0;
        } else if (hazardEquipmentInventory.containsKey(key.toLowerCase())) {
            return hazardEquipmentInventory.get(key.toLowerCase()).size() > 0;
        } else {
            return false;
        }
    }
}

package GardenPuzzleDomain.Classes;

import java.util.ArrayList;
import java.util.List;

import GardenPuzzleDomain.Interfaces.ISearchable;

/**
 * The StorageShed class represents a storage shed that holds a list of ISearchable objects.
 * It provides methods to add, remove, search, and manage the list of objects.
 */
public class StorageShed {
    private List<ISearchable> storegeShedList;

    //default constructor
    public StorageShed(){
        storegeShedList = new ArrayList<ISearchable>();
    }

    //parameterized constructor
    public StorageShed(List<ISearchable> storegeShedList){
        this.storegeShedList = storegeShedList;
    }

    //copy constructor
    public StorageShed(StorageShed storageShed){
        if(storageShed == null){
            System.out.println("Fatal Error: StorageShed object is null");
            System.exit(0);
        }
        this.storegeShedList = new ArrayList<>(storageShed.storegeShedList);
    }

    //getter and setter methods

    public List<ISearchable> getStoregeShedList() {
        return new ArrayList<>(storegeShedList);
    }

    public void setStoregeShedList(List<ISearchable> storegeShedList) {
        this.storegeShedList = storegeShedList;
    }

    //toString method
    @Override
    public String toString() {
        return "Storage Shed: " +
                "Storege Shed List: " + storegeShedList.toString();
    }

    //equals method that takes an object as parameter
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StorageShed storageShed = (StorageShed) obj;
        return storegeShedList.equals(storageShed.storegeShedList);
    }

    //equals method that takes a StorageShed object as parameter
    public boolean equals(StorageShed storageShed){
        if (storageShed == null) {
            return false;
        }
        return storegeShedList.equals(storageShed.storegeShedList);
    }

    //add method
    public void add(ISearchable gardenObject){
        storegeShedList.add(gardenObject);
    }

    //remove method
    public void remove(ISearchable gardenObject){
        storegeShedList.remove(gardenObject);
    }

    //search method
    /**
     * Searches for elements in the storage shed list that match the specified field and value.
     * If a matching element is found, it is added to the filtered garden object list.
     *
     * @param field The field to search for in the elements.
     * @param value The value to match against the specified field.
     * @param filtredGardenObjectList The list to which matching elements will be added.
     */
    public void search(String field, Object value, List<ISearchable> filtredGardenObjectList) {
        for (ISearchable listElement : storegeShedList) {
            if (listElement instanceof ISearchable searchable) {
                if (searchable.search(field, value)) {
                    filtredGardenObjectList.add(listElement);
                }
            }
            
        }
    }



}

/**
 * The GardenerApp class represents a gardening application that manages a garden and a storage shed.
 * It allows the user to select garden objects, place them in the garden, and manage the garden's state.
 */
package GardenPuzzleDomain.Classes;

import FileAccess.FileIO;
import GardenPuzzleDomain.Enums.ColorType;
import GardenPuzzleDomain.Enums.LightType;
import GardenPuzzleDomain.Enums.PlantType;
import GardenPuzzleDomain.Enums.PollenType;
import GardenPuzzleDomain.Enums.Position;
import GardenPuzzleDomain.Interfaces.ISearchable;
import GardenPuzzleDomain.Interfaces.ISquare;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;



public class GardenerApp {
    private Garden garden;
    private StorageShed storageShed;
    private List<ISearchable> selectedGardenObjectsList;
    private Random random = new Random();
    

    // Default constructor
    public GardenerApp() {
        this.garden = new Garden();
        this.storageShed = new StorageShed();
        this.selectedGardenObjectsList = new ArrayList<>();
    }

    //Parameterized constructor
    public GardenerApp(Garden garden, StorageShed storageShed, List<ISearchable> selectedGardenObjectsList, List<ISearchable> filtredGardenObjectList) {
        this.garden = garden;
        this.storageShed = storageShed;
        this.selectedGardenObjectsList = selectedGardenObjectsList;
    }

    //Copy constructor
    public GardenerApp(GardenerApp gardenerApp) {
        if (gardenerApp == null) {
            System.out.println("Fatal Error: gardenerApp is null");
            System.exit(0);
        }
        this.garden = new Garden(gardenerApp.garden);
        this.storageShed = new StorageShed(gardenerApp.storageShed);
        this.selectedGardenObjectsList = new ArrayList<>(gardenerApp.selectedGardenObjectsList);
    }

    //Getters and Setters
    public Garden getGarden() {
        return new Garden(garden);
    }

    public void setGarden(Garden garden) {
        this.garden = garden;
    }

    public StorageShed getStorageShed() {
        return new StorageShed(storageShed);  //storage shed yazılmalı 
    }

    public void setStorageShed(StorageShed storageShed) {
        this.storageShed = storageShed;
    }

    public List<ISearchable> getSelectedGardenObjectsList() {
        return new ArrayList<>(selectedGardenObjectsList);
    }

    public void setSelectedGardenObjectsList(List<ISearchable> selectedGardenObjectsList) {
        this.selectedGardenObjectsList = selectedGardenObjectsList;
    }


    //toString method
    @Override
    public String toString() {
        return "GardenerApp: " +
                "Garden: " + garden +
                ", Storage Shed: " + storageShed +
                ", Selected Garden Objects List: " + selectedGardenObjectsList;
    }

    //equals method that takes an Object
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        GardenerApp gardenerApp = (GardenerApp) obj;
        return garden.equals(gardenerApp.garden) && storageShed.equals(gardenerApp.storageShed) && selectedGardenObjectsList.equals(gardenerApp.selectedGardenObjectsList);
    }

    //equals method that takes a GardenerApp
    public boolean equals(GardenerApp gardenerApp) {
        if (this == gardenerApp) return true;
        if (gardenerApp == null) return false;
        return garden.equals(gardenerApp.garden) && storageShed.equals(gardenerApp.storageShed) && selectedGardenObjectsList.equals(gardenerApp.selectedGardenObjectsList);
    }

    //clone method
    public GardenerApp clone() {
        return new GardenerApp(this);
    }

    //main game method
    public void gameManage() {
        //initialize garden and goal, then add statues and goal randomly
        GardenSquare goal = generateGoal();
        addStatuesAndGoalrandomly(goal, garden);

        //initialize storage shed and add garden objects to the storage shed by selecting according to the gardener's choice
        FileIO file = new FileIO(); 
        List<ISearchable> gardenObjectsList = file.readFile("storage_contents.csv");
        storageShed.setStoregeShedList(gardenObjectsList);
        Scanner scanner = new Scanner(System.in);
        selectObject(goal, scanner);

        //Gardener starts to move and carry objects to the garden
        for(ISearchable listElement: selectedGardenObjectsList) {
            garden.addGardenObject(listElement);
        }

        //print the initial garden
        System.out.println("Initial Garden Map:");
        garden.printGarden(goal,1);

        //place the selected objects to the garden and hold the placed objects in a list
        List<ISearchable> placedObjectsList = placeObjectsToGarden(garden.getGardenStorage(), scanner, goal.getPosition());
        
        //wait for the plants to bloom and the lights to light up
        wait(placedObjectsList);

        //print the final garden
        System.out.println("Final Garden Map:");
        garden.printGarden(goal,2);

        //print the garden objects  placed in the garden
        printGardenObjects(placedObjectsList);

       //Decide if the gardener is successful or not
        successOrNot(goal);
    }
    
    /**
     * Randomly adds statues and a goal to the garden.
     * 
     * This method first adds 7 statues to random positions in the garden, ensuring that no two statues
     * occupy the same position. It then sets the position of the goal to a random position that is not
     * already occupied by a statue.
     * 
     * @param goal The GardenSquare object representing the goal.
     * @param garden The Garden object where statues and the goal will be placed.
     */

    private void addStatuesAndGoalrandomly(GardenSquare goal, Garden garden){
        Position[] positionArray = Position.values();
        while(goal.getPosition() == Position.DEFAULT){
            
            Position goalPosition = positionArray[random.nextInt(positionArray.length - 1 )];  //-1 for default position
            if(garden.getGardenSquareByPosition(goalPosition).getSquareObject() == null){

                goal.setPosition(goalPosition);
                garden.getGardenSquareByPosition(goalPosition).setSquareObject(new PollenCloud());
                garden.getGardenSquareByPosition(goalPosition).setIsOccupied(true);  //set true to prevent adding another object to the same position
                }
        }
        for(int i = 0; i < 7; i++){
            Position position = positionArray[random.nextInt(positionArray.length - 1 )];  //-1 for default position
            Statue statue = new Statue("S"+ (i + 1), position);
            GardenSquare gardenSquare = garden.getGardenSquareByPosition(position);
            
            if(!gardenSquare.addAGardenSquareObject(statue)){
                i--; 
                
            }
        }
    }


    /**
     * Generates a goal GardenSquare with a PollenCloud object.
     * The PollenCloud is populated with a random number of unique PollenTypes and ColorTypes.
     * The number of PollenTypes is between 1 and 3 (inclusive).
     * The number of ColorTypes is between 0 and 3 (inclusive).
     * The PollenTypes and ColorTypes are sorted before returning the goal GardenSquare.
     *
     * @return a GardenSquare object with a PollenCloud containing random PollenTypes and ColorTypes.
     */
    private GardenSquare generateGoal(){
        GardenSquare goal = new GardenSquare();
        goal.setSquareObject(new PollenCloud()); //set a new PollenCloud object to the goal
        PollenCloud goalPollenCloud = (PollenCloud) goal.getSquareObject();
        PollenType[] pollenTypes = PollenType.values();
        int numberOfPollen = random.nextInt(3) + 1;
        while(numberOfPollen > 0){
            int typeOfPollen = random.nextInt(3) + 1;
            PollenType pollenType = pollenTypes[typeOfPollen];
            if (!goalPollenCloud.getPollenList().contains(pollenType)) {
                goalPollenCloud.addPollenType(pollenType);
                numberOfPollen--;
            }
        }
        ColorType[] colorTypes = ColorType.values();
        int numberOfColors = random.nextInt(4) ;
        while(numberOfColors > 0){
            int typeOfColor = random.nextInt(3) + 1;
            ColorType colorType = colorTypes[typeOfColor];
            if(!goalPollenCloud.getColorList().contains(colorType)){
                goalPollenCloud.addColorType(colorType);
                numberOfColors--;
            }
        }
        Collections.sort(goalPollenCloud.getPollenList());
        Collections.sort(goalPollenCloud.getColorList());
        return goal;
    }

    /**
     * This method allows the user to select garden objects from the storage shed and add them to the selectedGardenObjectsList.
     * The user can choose between Plant and Light objects, and can take up to 7 objects in total.
     * The method provides a guided interaction with the user to filter, select, and confirm the objects to be taken.
     * 
     * @param goal The target GardenSquare that needs specific pollens and colors.
     * @param scanner The Scanner object used to read user input.
     */
    private void selectObject(GardenSquare goal, Scanner scanner){
        System.out.println("Welcome to Garden Puzzle App. Your goal Square needs " +  ((PollenCloud)goal.getSquareObject()).getPollenList().toString() + " pollens infused with " + ((PollenCloud)goal.getSquareObject()).getColorList().toString() + " color(s)");
        
        boolean isFinished = true;
        while(isFinished == true && selectedGardenObjectsList.size() < 7){
            List<ISearchable> filtredGardenObjectList = new ArrayList<>();
            System.out.println("==> Please search for Garden Objects from the Storage Shed. You can take up to " + (7 - selectedGardenObjectsList.size()) + " object(s).");
            System.out.println("Please select the type of object ([1] Plant, [2] Light): ");
            String selectedType = "";
            int type = getValidInputNumber(scanner, 1, 2);
            switch (type) {
                case 1:
                    selectPlant(scanner, filtredGardenObjectList);
                    selectedType = "Plant Object";
                    break;
                case 2:
                    selectLight(scanner, filtredGardenObjectList);
                    selectedType = "Light Source";
                    break;
                default:
                    System.out.println("Invalid object type.");
                    break;
            }
            if (filtredGardenObjectList.isEmpty()) {
                System.out.println("No " + selectedType + " found. Please try again.");
                continue;
            }
            printFiltredGardenObjectList(filtredGardenObjectList, selectedType);
            System.out.println("--> Please enter the id of the " + selectedType + " you would like to take:");
            String id = getValidStringInput(scanner, "id", filtredGardenObjectList);  
            ISearchable selectedObject = getObjectById(id, filtredGardenObjectList);
            if (selectedObject != null) {
                selectedGardenObjectsList.add(selectedObject);
                storageShed.remove(selectedObject);
            } else {
                System.out.println("The " + selectedType + " with id " + id + " does not exist in the filtered list. Please try again.");
                
            }
            System.out.println("You have taken " + selectedGardenObjectsList.size() + " Garden Object(s). Would you like to select another one? ([1] Yes, [2] No):");
            int answer = getValidInputNumber(scanner, 1, 2);
            if (answer == 2) {
                isFinished = false;
            }
            if (answer == 1 && selectedGardenObjectsList.size() == 7) {
                System.out.println("You have reached the maximum number of objects you can take. You can not select another object.");
                isFinished = false;
            }
        }
        System.out.println("==> The gardener carries selected objects to the Garden.");
        System.out.println("*".repeat(66));
    }

    /**
     * Prompts the user to select a search filter for plants and retrieves the corresponding value.
     * The method then searches the storage shed for plants matching the selected criteria.
     *
     * @param scanner the Scanner object used to read user input
     * @param filtredGardenObjectList the list to store the filtered garden objects
     */
    private void selectPlant(Scanner scanner, List<ISearchable> filtredGardenObjectList){ 
        String field = "";
        Object value = null;
        System.out.println("Please select search filter for Plants ([1] Plant type, [2] Plant name, [3] Plant id, [4] Area of spread): ");
        int criteria = getValidInputNumber(scanner,1, 4); 
        switch (criteria) {
            case 1 -> {
                field = "type";
                System.out.println("Please enter a plant type ([1] Flower, [2] Tree, [3] Bush): ");
                int plantTypeInput = getValidInputNumber(scanner,1, 3); 
                value = PlantType.choosePlant(plantTypeInput);
            }
            case 2 -> {
                field = "name";
                System.out.println("Please enter a plant name: ");
                value = getValidStringInput(scanner, "name", getStorageShed().getStoregeShedList());
            }
            case 3 -> {
                field = "id";
                System.out.println("Please enter the id of the plant object you would like to take: "); 
                value = getValidStringInput(scanner, "id", getStorageShed().getStoregeShedList());
            }
            case 4 -> {
                field = "areaofpollenspread";
                System.out.println("Please enter a pollen area of reach value between 1 and 5: ");
                value = getValidInputNumber(scanner, 1, 5); 
            }
            
        }
        storageShed.search(field, value, filtredGardenObjectList);
    }

    /**
     * Prompts the user to select a search filter for light sources and performs the search based on the selected criteria.
     * 
     * @param scanner The Scanner object used to read user input.
     * @param filtredGardenObjectList The list of ISearchable objects to store the filtered garden objects.
     * 
     * The user can choose from the following search filters:
     * - Light type: Prompts the user to select a light type (Small Lamp, Large Lamp, Spotlight).
     * - Light id: Prompts the user to enter the id of the light source.
     * - Color: Prompts the user to select a light color (Red, Green, Blue).
     * - Area of reach: Prompts the user to enter a light area of reach value between 1 and 5.
     * 
     * The method then calls the storageShed.search method with the selected field and value to filter the garden objects.
     */
    private void selectLight(Scanner scanner, List<ISearchable> filtredGardenObjectList){  
        String field = "";
        Object value = null;
        System.out.println("Please select search filter for Light Sources ([1] Light type, [2] Light id, [3] Color, [4] Area of reach): ");
        int criteria = getValidInputNumber(scanner,1, 4); 
        switch (criteria) {
            case 1 -> {
                field = "type";
                System.out.println("Please enter a light type ([1] Small Lamp, [2] Large Lamp, [3] Spotlight): ");
                int lightTypeInput = getValidInputNumber(scanner,1, 3); 
                value = LightType.chooseLight(lightTypeInput);
            }
            case 2 -> {
                field = "id";
                System.out.println("Please enter the id of the light source you would like to take: ");
                value = getValidStringInput(scanner, "id", getStorageShed().getStoregeShedList());
            }
            case 3 -> {
                field = "color";
                System.out.println("Please enter a light color ([1] Red, [2] Green, [3] Blue): ");
                int colorTypeInput = getValidInputNumber(scanner,1, 3); 
                value = ColorType.chooseColor(colorTypeInput);
            }
            case 4 -> {
                field = "areaoflightreach";
                System.out.println("Please enter a light area of reach value between 1 and 5: ");
                value = getValidInputNumber(scanner,1,5); 
            }
        }
        storageShed.search(field, value, filtredGardenObjectList);
        
        
    }

    /**
     * Places objects from the garden storage into the garden based on user input.
     * 
     * @param gardenStorage A list of ISearchable objects representing the garden storage.
     * @param scanner A Scanner object for reading user input.
     * @param goalPosition The position in the garden that should be set as unoccupied at the end.
     * @return An ArrayList of ISearchable objects that have been placed in the garden.
     */
    private ArrayList<ISearchable> placeObjectsToGarden(List<ISearchable> gardenStorage, Scanner scanner, Position goalPosition) {  
        ArrayList<ISearchable> placedObjects = new ArrayList<>();
        boolean isFinished = true;
        while (!gardenStorage.isEmpty() && isFinished) {
            System.out.println("--> Your chosen Garden Objects: ");
            for (ISearchable gardenObject : gardenStorage) {
                System.out.println(gardenObject.toString());
            }

            System.out.println("Enter the id corresponding to the Garden Object you would like to place: ");
            String id = getValidStringInput(scanner, "id", gardenStorage).toLowerCase();
            boolean isPlaced = false;
            while(!isPlaced){
                System.out.println("Enter the location you would like to place the selected Garden Object: ");
                String location = getValidStringInput(scanner, "location",gardenStorage ).toUpperCase();
                Position position = Position.valueOf(location);
                GardenSquare gardenSquare = garden.getGardenSquareByPosition(position);
                if(!gardenSquare.getIsOccupied()){
                    gardenSquare.addAGardenSquareObject((ISquare)getObjectById(id, gardenStorage));
                    
                    placedObjects.add(getObjectById(id, gardenStorage));  
                    gardenStorage.remove(getObjectById(id, gardenStorage));
                    isPlaced = true; 
                }    
                else {
                    System.out.println("Position " + position + " is already occupied or invalid. Please try again.");
                }
                
            }
            
            if(!gardenStorage.isEmpty()){
                System.out.println("Would you like to place another Garden Object? ([1] Yes, [2] No): ");
                int answer = getValidInputNumber(scanner, 1, 2);
                if (answer == 2) {
                isFinished = false;
            }
            }
            
            
            
            if (gardenStorage.isEmpty()) {
                System.out.println("You have run out of objects to place.");
                isFinished = false;
            }
        }
        garden.getGardenSquareByPosition(goalPosition).setIsOccupied(false); //set false to add pollen cloud to the goal position
        return placedObjects;
    }

    /**
     * Prompts the user to enter a valid number within a specified range.
     * Continuously reads input from the provided Scanner until a valid number is entered.
     *
     * @param scanner the Scanner object to read user input
     * @param firstNumber the lower bound of the valid number range (inclusive)
     * @param lastNumber the upper bound of the valid number range (inclusive)
     * @return a valid number entered by the user within the specified range
     */
    private int getValidInputNumber(Scanner scanner, int firstNumber, int lastNumber) { 
        while (true) {
            try {
                int number = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (number >= firstNumber && number <= lastNumber) {
                    return number; // whiledan çıkıyor.
                } else {
                    System.out.println("Invalid input. Please enter a number between " + firstNumber + " and " + lastNumber + ": ");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number: ");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    /**
     * Prompts the user to enter a valid string input for a specified field and validates it against a list of searchable objects.
     * 
     * @param scanner the Scanner object used to read user input
     * @param field the field name to validate (e.g., "name", "id", "location")
     * @param currentList the list of ISearchable objects to validate the input against
     * @return the valid input string entered by the user
     * @throws IllegalArgumentException if the input is invalid or does not match any object in the list
     */
    private String getValidStringInput(Scanner scanner, String field, List<ISearchable> currentList) {  
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    throw new IllegalArgumentException("Invalid input. Please enter a valid " + field + ": ");
                }
                for (ISearchable searchable : currentList) {
                    GardenObject obj = (GardenObject) searchable;
                    if (field.equalsIgnoreCase("name")) {

                        if (obj instanceof GardenPlant && ((GardenPlant)obj).getName().equalsIgnoreCase(input)) {
                            return input;
                        }
                        else if (obj instanceof LightSource) {
                            throw new IllegalArgumentException("Invalid input. No matching " + field + " found. Please enter a valid " + field + ": ");
                        }
                    } 
                    else if (field.equalsIgnoreCase("id")) {
                        if (obj instanceof GardenPlant && obj.getId().equalsIgnoreCase(input)) {
                            return input;
                        }
                        else if (obj instanceof LightSource && obj.getId().equalsIgnoreCase(input)) {
                            return input;
                        }
                    }
                }
                if (field.equalsIgnoreCase("location")) {
                    Position.valueOf(input.toUpperCase());
                    return input;
                }
                throw new IllegalArgumentException("Invalid input. No matching " + field + " found. Please enter a valid " + field + ": ");
            } 
            catch (IllegalArgumentException e) {
                
                if(field.equalsIgnoreCase("location")){
                    System.out.println("Invalid position! Please enter a valid position:");
                }
                else{
                    System.out.println(e.getMessage());
                }
                
            }
        }
    }

    /**
     * Prints the list of garden objects that match the given criteria.
     *
     * @param filteredGardenObjectList the list of garden objects that match the criteria
     * @param selectedType the type of garden objects being printed
     */
    private void printFiltredGardenObjectList(List<ISearchable> filteredGardenObjectList, String selectedType) {
        System.out.println("The " + selectedType + " that fit the given criteria:");
        for (ISearchable searchable : filteredGardenObjectList) {
            System.out.println(searchable.toString());
        }
    }

    /**
     * Retrieves an object from the list of searchable garden objects by its ID.
     *
     * @param id The ID of the garden object to search for.
     * @param filtredGardenObjectList The list of searchable garden objects to search within.
     * @return The garden object with the specified ID, or null if no such object is found.
     */
    private ISearchable getObjectById(String id, List<ISearchable> filtredGardenObjectList) {
        for (ISearchable searchable : filtredGardenObjectList) {
            GardenObject obj = (GardenObject) searchable;
            if (obj.getId().equalsIgnoreCase(id)) {
                return searchable;
            }
        }
        return null;
    }

    /**
     * Determines if the garden square at the specified goal position matches the goal's square object.
     * Sorts the pollen and color lists of the PollenCloud object in the garden square at the goal position.
     * Prints "SUCCESSFUL" if the garden square's object matches the goal's square object, otherwise prints "UNSUCCESSFUL".
     *
     * @param goal the GardenSquare object representing the goal position and square object to compare against
     */
    private void successOrNot(GardenSquare goal){
        Collections.sort(((PollenCloud)garden.getGardenSquareByPosition(goal.getPosition()).getSquareObject()).getPollenList());
        Collections.sort(((PollenCloud)garden.getGardenSquareByPosition(goal.getPosition()).getSquareObject()).getColorList());
        if(garden.getGardenSquareByPosition(goal.getPosition()).getSquareObject().equals(goal.getSquareObject())){
            System.out.println();
            System.out.println("====>> SUCCESSFUL"); 
        }
        else{
            System.out.println("====>> UNSUCCESSFUL");
        }
    }

    /**
     * Makes the gardener wait, during which all lights are lit up and all plants start blooming.
     * 
     * @param placedObjectsList the list of objects in the garden that can be either plants or light sources
     */
    private void wait(List<ISearchable> placedObjectsList){
        System.out.println("==> The gardener starts waiting. All lights are lit up. All plants start blooming.");
        for (ISearchable gardenObject : placedObjectsList) {
            if (gardenObject instanceof GardenPlant) {
                GardenPlant plant = (GardenPlant) gardenObject;
                plant.bloom(garden, plant.getPosition(), false, null, null);
            }
            if (gardenObject instanceof LightSource) {
                LightSource light = (LightSource) gardenObject;
                light.lightUp(garden, light.getPosition(), false, null);
            }
        }
    }

    /**
     * Prints the details of each garden object in the provided list.
     *
     * @param placedObjects a list of objects that implement the ISearchable interface.
     *                      Each object in the list is cast to a GardenObject and its
     *                      details are printed using the print() method.
     */
    private void printGardenObjects(List<ISearchable> placedObjects){
        for(ISearchable gardenObject: placedObjects){
            System.out.println(((GardenObject)gardenObject).print());
        }
    }
}

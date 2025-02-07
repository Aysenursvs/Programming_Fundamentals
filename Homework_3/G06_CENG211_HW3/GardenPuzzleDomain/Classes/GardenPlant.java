package GardenPuzzleDomain.Classes;
import GardenPuzzleDomain.Enums.ColorType;
import GardenPuzzleDomain.Enums.PlantType;
import GardenPuzzleDomain.Enums.PollenType;
import GardenPuzzleDomain.Enums.Position;
import GardenPuzzleDomain.Interfaces.ISearchable;

/**
 * The GardenPlant class represents a plant in a garden, extending the GardenObject class and implementing the ISearchable interface.
 * It includes properties such as type, name, and area of pollen spread, and provides various methods for manipulating and retrieving these properties.
 * The class also includes methods for blooming, spreading pollen, and searching for specific attributes.
 * 
 */
public class GardenPlant extends GardenObject implements ISearchable{

    private PlantType type;
    private String name;
    private int areaOfPollenSpread;
    

    //default constructor
    public GardenPlant() {
        super();
        this.type = PlantType.DEFAULT;
        this.name = "default";
        this.areaOfPollenSpread = 0;
    }

    //parameterized constructor
    public GardenPlant(String id, Position position, PlantType type, String name, int areaOfPollenSpread) {
        super(id, position);
        this.type = type;
        this.name = name;
        this.areaOfPollenSpread = areaOfPollenSpread;
    }

    //copy constructor
    public GardenPlant(GardenPlant gardenPlant) {
        super(gardenPlant);
        if(gardenPlant == null){
            System.out.println("Fatal Error: gardenPlant is null");
            System.exit(0);
        }
        this.type = gardenPlant.type;
        this.name = gardenPlant.name;
        this.areaOfPollenSpread = gardenPlant.areaOfPollenSpread;
    }

    //getter and setter methods
    public PlantType getType() {
        return type;
    }

    public void setType(PlantType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAreaOfPollenSpread() {
        return areaOfPollenSpread;
    }

    public void setAreaOfPollenSpread(int areaOfPollenSpread) {
        this.areaOfPollenSpread = areaOfPollenSpread;
    }

    //toString method
    @Override
    public String toString() {
        return "- " +
               "Type: " + type + 
               ", Id: " + getId() + 
               ", Name: " + Character.toUpperCase(name.charAt(0)) + name.substring(1) + 
               ", Area of reach: " + areaOfPollenSpread;
    }

    //equals method that takes an object as parameter
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        else if(getClass() != obj.getClass()){
            return false;
        }
        else{
            GardenPlant gardenPlant = (GardenPlant) obj;
            return (super.equals(obj) && type.equals(gardenPlant.type) && name.equals(gardenPlant.name) && areaOfPollenSpread == gardenPlant.areaOfPollenSpread);
        }
    }

    //equals method that takes a GardenPlant as parameter
    public boolean equals(GardenPlant gardenPlant){
        if(gardenPlant == null){
            return false;
        }
        return (super.equals(gardenPlant) && type.equals(gardenPlant.type) && name.equals(gardenPlant.name) && areaOfPollenSpread == gardenPlant.areaOfPollenSpread);
    }

    //clone method
    @Override
    public GardenPlant clone(){
        return new GardenPlant(this);
    }

    /**
     * Returns a formatted string representation of the garden plant.
     * The format is "-- {id}: {name} {type}" where {id} is the plant's ID,
     * {name} is the plant's name, and {type} is the plant's type in lowercase.
     *
     * @return a formatted string representing the garden plant.
     */
    public String print(){
        return "-- " + getId() + ": " + getName() + " " + getType().toString().toLowerCase();
    }

    //search method
    /**
     * Searches for a specific field in the GardenPlant object and compares it with the provided value.
     *
     * @param field The name of the field to search for. Valid values are "id", "name", "type", and "areaOfPollenSpread".
     * @param value The value to compare against the field's value. The type of this parameter should match the type of the field.
     *              For "id" and "name", it should be a String. For "type", it should be an Object. For "areaOfPollenSpread", it should be an int.
     * @return true if the field's value matches the provided value, false otherwise.
     */
    public boolean search(String field, Object value) {
        return switch (field.toLowerCase()) {
            case "id" -> getId().equalsIgnoreCase((String) value);
            case "name" -> name.equalsIgnoreCase((String) value);
            case "type" -> type == value;
            case "areaofpollenspread" -> areaOfPollenSpread == (int) value;
            default -> false;
        };
        
    }

    //bloom method
    /**
     * Causes the plant to bloom and spread pollen in the garden based on its type.
     *
     * @param garden The garden where the plant is blooming.
     * @param position The position of the plant in the garden.
     * @param isCombined A boolean indicating if the pollen spread is combined with other plants.
     * @param type The type of pollen being spread.
     * @param colorType The color type of the pollen.
     * @throws IllegalStateException if the plant type is unexpected.
     */
    public void bloom(Garden garden, Position position,   boolean isCombined, PollenType type, ColorType colorType) {
    switch (this.type) {
        case FLOWER -> spreadDiagonally(garden, position, this.areaOfPollenSpread, isCombined, type, colorType);
        case TREE -> spreadHorizontally(garden, position, this.areaOfPollenSpread,  isCombined, type, colorType);
        case BUSH -> spreadVertically(garden, position, this.areaOfPollenSpread,  isCombined,  type, colorType);
        default -> throw new IllegalStateException("Unexpected PlantType: " + this.type);
    }
}


//spread methods

/**
 * Spreads pollen diagonally from a given position in a garden.
 *
 * @param garden The garden where the pollen will be spread.
 * @param position The starting position from which the pollen will spread.
 * @param range The range or distance the pollen will spread diagonally.
 * @param isCombined A flag indicating whether the pollen should be combined with another type.
 * @param type The type of pollen to be spread if combined.
 * @param colorType The color type to be added to the square if combined.
 */
private void spreadDiagonally(Garden garden, Position position, int range,  boolean isCombined,  PollenType type, ColorType colorType) {
    // Spread top-left
    for (int i = 1; i <= range; i++) {
        Position topLeft = position.offset(-i, -i);
        if (topLeft == null) break; // Out of bounds
        if(garden.getGardenSquareByPosition(topLeft).getIsOccupied()){ 
            switch (garden.getGardenSquareByPosition(topLeft).getSquareObject().getClass().getSimpleName()) {
                case ("GardenPlant") -> reflectionOfPlants(garden, topLeft,  PollenType.FLOWER, colorType);
                
            }
            break;
        } // Stop spreading in this direction
        if(isCombined){
            garden.addPollenToSquare(topLeft, type);
            if(colorType != null){
                garden.addColorToSquare(topLeft, colorType);
            }
        }
        garden.addPollenToSquare(topLeft, PollenType.FLOWER );
    }

    // Spread top-right
    for (int i = 1; i <= range; i++) {
        Position topRight = position.offset(-i, i);
        if (topRight == null) break;
        if(garden.getGardenSquareByPosition(topRight).getIsOccupied()){ 
            switch (garden.getGardenSquareByPosition(topRight).getSquareObject().getClass().getSimpleName()) {
                case ("GardenPlant") -> reflectionOfPlants(garden, topRight,  PollenType.FLOWER, colorType);
                
            }
            break;} // Stop spreading in this direction
        if(isCombined){
            garden.addPollenToSquare(topRight, type);
            if(colorType != null){
                garden.addColorToSquare(topRight, colorType);
            }
        }
        garden.addPollenToSquare(topRight, PollenType.FLOWER );
    }

    // Spread bottom-left
    for (int i = 1; i <= range; i++) {
        Position bottomLeft = position.offset(i, -i);
        if (bottomLeft == null) break; // Out of bounds
        if(garden.getGardenSquareByPosition(bottomLeft).getIsOccupied()){
            switch (garden.getGardenSquareByPosition(bottomLeft).getSquareObject().getClass().getSimpleName()) {
                case ("GardenPlant") -> reflectionOfPlants(garden, bottomLeft, PollenType.FLOWER, colorType);
                
            } 
            break;} // Stop spreading in this direction
        if(isCombined){
            garden.addPollenToSquare(bottomLeft, type);
            if(colorType != null){
                garden.addColorToSquare(bottomLeft, colorType);
            }
        }
        garden.addPollenToSquare(bottomLeft, PollenType.FLOWER );
    }

    // Spread bottom-right
    for (int i = 1; i <= range; i++) {
        Position bottomRight = position.offset(i, i);
        if (bottomRight == null) break;
        if(garden.getGardenSquareByPosition(bottomRight).getIsOccupied()){
            switch (garden.getGardenSquareByPosition(bottomRight).getSquareObject().getClass().getSimpleName()) {
                case ("GardenPlant") -> reflectionOfPlants(garden, bottomRight,  PollenType.FLOWER, colorType);
                
            }
            break;
        } // Stop spreading in this direction
        if(isCombined){
            garden.addPollenToSquare(bottomRight, type);
            if(colorType != null){
                garden.addColorToSquare(bottomRight, colorType);
            }
        }
        garden.addPollenToSquare(bottomRight, PollenType.FLOWER );
    }
    }


/**
 * Spreads pollen horizontally from a given position in the garden.
 *
 * @param garden The garden where the pollen is being spread.
 * @param position The starting position from which the pollen spreads.
 * @param range The range or distance the pollen can spread horizontally.
 * @param isCombined A boolean indicating if the pollen should be combined with other types.
 * @param type The type of pollen to be spread.
 * @param colorType The color type to be added to the garden squares, if applicable.
 */
private void spreadHorizontally(Garden garden, Position position, int range,  boolean isCombined, PollenType type, ColorType colorType) {
    // Spread to the right
    for (int i = 1; i <= range; i++) {
        Position right = position.offset(0, i);
        if(right == null) break; // Out of bounds
        if(garden.getGardenSquareByPosition(right).getIsOccupied()){
            switch (garden.getGardenSquareByPosition(right).getSquareObject().getClass().getSimpleName()) {
                case ("GardenPlant") -> reflectionOfPlants(garden, right,  PollenType.TREE, colorType);
                
            }
            break;
         } // Stop spreading in this direction
        if(isCombined){
            garden.addPollenToSquare(right, type);
            if(colorType != null){
                garden.addColorToSquare(right, colorType);
            }
        }
        garden.addPollenToSquare(right, PollenType.TREE );
    }

    // Spread to the left
    for (int i = 1; i <= range; i++) {
        Position left = position.offset(0, -i);
        if (left == null) break; // Out of bounds
        if(garden.getGardenSquareByPosition(left).getIsOccupied()){ 
            switch (garden.getGardenSquareByPosition(left).getSquareObject().getClass().getSimpleName()) {
                case ("GardenPlant") -> reflectionOfPlants(garden, left,  PollenType.TREE, colorType);
                
            }
            break;
         } // Stop spreading in this direction
        if(isCombined){
            garden.addPollenToSquare(left, type);
            if(colorType != null){
                garden.addColorToSquare(left, colorType);
            }
            
        }
        garden.addPollenToSquare(left, PollenType.TREE );
    }
}

/**
 * Spreads pollen vertically in the garden from a given position within a specified range.
 * The method spreads pollen both downward and upward from the initial position.
 * 
 * @param garden The garden where the pollen will be spread.
 * @param position The initial position from where the pollen spreading starts.
 * @param range The range within which the pollen will be spread vertically.
 * @param isCombined A boolean flag indicating whether the pollen spreading is combined with color spreading.
 * @param type The type of pollen to be spread.
 * @param colorType The type of color to be spread, if applicable.
 */
private void spreadVertically(Garden garden, Position position, int range,  boolean isCombined, PollenType type, ColorType colorType) {
     // Spread downward
     for (int i = 1; i <= range; i++) {
        Position down = position.offset(i, 0);
        if (down == null) break; // Out of bounds 
        if(garden.getGardenSquareByPosition(down).getIsOccupied()){
            switch (garden.getGardenSquareByPosition(down).getSquareObject().getClass().getSimpleName()) {
                case ("GardenPlant") -> reflectionOfPlants(garden, down,  PollenType.BUSH, colorType);
                
            }
            break;
         } // Stop spreading in this direction
        if(isCombined){
            garden.addPollenToSquare(down, type);
            if(colorType != null){
                garden.addColorToSquare(down, colorType);
            }
        }
        garden.addPollenToSquare(down, PollenType.BUSH );
    }

    // Spread upward
    for (int i = 1; i <= range; i++) {
        Position up = position.offset(-i, 0);
        if (up == null) break; // Out of bounds
        if(garden.getGardenSquareByPosition(up).getIsOccupied()){
            switch (garden.getGardenSquareByPosition(up).getSquareObject().getClass().getSimpleName()) {
                case ("GardenPlant") -> reflectionOfPlants(garden, up, PollenType.BUSH, colorType);
                
            }
            break;
         } // Stop spreading in this direction
        if(isCombined){
            garden.addPollenToSquare(up, type);
            if(colorType != null){
                garden.addColorToSquare(up, colorType);
        }
       }
        garden.addPollenToSquare(up, PollenType.BUSH );
    }
}

    /**
     * Reflects the plants in the garden at the specified position.
     *
     * @param garden The garden where the plant is located.
     * @param position The position of the plant in the garden.
     * @param type The type of pollen the plant will produce.
     * @param colorType The color type of the plant.
     */
    public void reflectionOfPlants(Garden garden, Position position, PollenType type, ColorType colorType) {
        GardenPlant gardenPlant = (GardenPlant) garden.getGardenSquareByPosition(position).getSquareObject();
        gardenPlant.bloom(garden, position, true, type, colorType);
        
    }

    /**
     * Returns the PollenType corresponding to the type of the plant.
     *
     * @return the PollenType of the plant.
     * @throws IllegalStateException if the plant type is unexpected.
     */
    public PollenType takePollenType() {
        switch (this.type) {
        case FLOWER: { return PollenType.FLOWER; }
            case TREE: { return PollenType.TREE; }
            case BUSH: { return PollenType.BUSH; }
            default: { throw new IllegalStateException("Unexpected PlantType: " + this.type); }
        }
    }
}

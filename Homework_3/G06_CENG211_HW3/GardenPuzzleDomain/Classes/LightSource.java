package GardenPuzzleDomain.Classes;
import GardenPuzzleDomain.Enums.ColorType;
import GardenPuzzleDomain.Enums.LightType;
import GardenPuzzleDomain.Enums.Position;
import GardenPuzzleDomain.Interfaces.ISearchable;
import GardenPuzzleDomain.Interfaces.ISquare;

/**
 * The LightSource class represents a light source object in a garden.
 * It extends the GardenObject class and implements the ISearchable interface.
 * The LightSource can have different types, colors, and areas of light reach.
 * It provides methods for setting and getting these properties, as well as methods for
 * cloning, comparing, and searching light sources. Additionally, it includes methods
 * for spreading light in different directions within a garden.
 */
public class LightSource extends GardenObject implements ISearchable{
    private LightType type;
    private ColorType color;
    private int areaOfLightReach;

    //default constructor
    public LightSource() {
        super();
        this.type = LightType.DEFAULT;
        this.color = ColorType.DEFAULT;
        this.areaOfLightReach = 0;
    }

    //parameterized constructor
    public LightSource(String id, Position position, LightType type, ColorType color, int areaOfLightReach) {
        super(id, position);
        this.type = type;
        this.color = color;
        this.areaOfLightReach = areaOfLightReach;
    }

    //copy constructor
    public LightSource(LightSource lightSource) {
        super(lightSource);
        if(lightSource == null){
            System.out.println("Fatal Error: lightSource is null");
            System.exit(0);
        }
        this.type = lightSource.type;
        this.color = lightSource.color;
        this.areaOfLightReach = lightSource.areaOfLightReach;
    }

    //getter and setter methods
    public LightType getType() {
        return type;
    }

    public void setType(LightType type) {
        this.type = type;
    }

    public ColorType getColor() {
        return color;
    }

    public void setColor(ColorType color) {
        this.color = color;
    }

    public int getAreaOfLightReach() {
        return areaOfLightReach;
    }

    public void setAreaOfLightReach(int areaOfLightReach) {
        this.areaOfLightReach = areaOfLightReach;
    }

    //toString method
    @Override
    public String toString() {
        return "- " +
               "Type: " + type + 
               ", Id: " + getId() + 
               ", Color: " + color.toString().charAt(0) + color.toString().substring(1).toLowerCase() + 
               ", Area of reach: " + areaOfLightReach;
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
            LightSource lightSource = (LightSource) obj;
            return (super.equals(lightSource) && type.equals(lightSource.type) && color.equals(lightSource.color) && areaOfLightReach == lightSource.areaOfLightReach);
        }
    }

    //equals method that takes a LightSource as parameter
    public boolean equals(LightSource lightSource){
        if(lightSource == null){
            return false;
        }
        return (super.equals(lightSource) && type.equals(lightSource.type) && color.equals(lightSource.color) && areaOfLightReach == lightSource.areaOfLightReach);
    }

    //clone method
    public LightSource clone(){
        return new LightSource(this);
    }

    /**
     * Returns a string representation of the LightSource object.
     * The format of the returned string is:
     * "-- <id>: <type> with <color> color"
     * where <id> is the ID of the light source,
     * <type> is the type of the light source in lowercase,
     * and <color> is the color of the light source in lowercase.
     *
     * @return a string representation of the LightSource object
     */
    public String print(){
        return "-- " + getId() + ": " + getType().toString().toLowerCase() + " with " + getColor().toString().toLowerCase() + " color";
    }

    //search method
    /**
     * Searches for a field in the LightSource object and compares it with the given value.
     *
     * @param field the name of the field to search for (e.g., "id", "type", "color", "areaoflightreach")
     * @param value the value to compare the field against
     * @return true if the field matches the given value, false otherwise
     */
    public boolean search(String field, Object value) {
        return switch (field.toLowerCase()) {
            case "id" -> getId().equalsIgnoreCase((String) value);
            case "type" -> type == value;
            case "color" ->  color == value;
            case "areaoflightreach" -> areaOfLightReach == (int) value;
            default -> false;
        };
    }

    //lightUp method
    /**
     * Illuminates the garden starting from the specified position based on the type of light source.
     *
     * @param garden The garden to be illuminated.
     * @param position The starting position for the light source.
     * @param isCombined A boolean indicating if the light should be combined with existing light sources.
     * @param color The color type of the light.
     * @throws IllegalStateException if the light source type is unexpected.
     */
    public void lightUp(Garden garden, Position position,   boolean isCombined, ColorType color){
        switch (this.type) {
            case SMALL_LAMP -> spreadRight(garden, position, this.areaOfLightReach, isCombined, color);
            case LARGE_LAMP -> spreadLeft(garden, position, this.areaOfLightReach,  isCombined, color);
            case SPOTLIGHT -> spreadDown(garden, position, this.areaOfLightReach,  isCombined, color);
            default -> throw new IllegalStateException("Unexpected PlantType: " + this.type);
        }
    }

    //spreadRight method
    /**
     * Spreads the light source to the right within the specified range.
     *
     * @param garden The garden where the light source is spreading.
     * @param position The starting position of the light source.
     * @param range The range within which the light source spreads.
     * @param isCombined A flag indicating whether the light source is combined with another.
     * @param color The color of the light source.
     */
    private void spreadRight(Garden garden, Position position, int range, boolean isCombined, ColorType color ){
        //Spread to right
        for (int i = 1; i <= range; i++) {
            Position right = position.offset(0, i);
            if(right == null) break; // Out of bounds
            ISquare square = garden.getGardenSquareByPosition(right).getSquareObject();
            if(garden.getGardenSquareByPosition(right).getIsOccupied()){
                switch (square.getClass().getSimpleName()) {
                    case ("GardenPlant"): 
                        GardenPlant gardenPlant = (GardenPlant) square;
                        (gardenPlant).reflectionOfPlants(garden, right,  gardenPlant.takePollenType(), this.color);
                        break;
                    
                }
                break;
             } // Stop spreading in this direction
            if(isCombined){
                garden.addColorToSquare(right, color);
            }
            garden.addColorToSquare(right, this.color);;
        }
    }

    //spreadLeft method
    /**
     * Spreads light to the left in the garden from a given position within a specified range.
     *
     * @param garden The garden where the light is being spread.
     * @param position The starting position from which the light spreads.
     * @param range The range (number of squares) the light can spread.
     * @param isCombined A boolean indicating if the light should combine with existing colors.
     * @param color The color of the light being spread.
     */
    private void spreadLeft(Garden garden, Position position, int range,   boolean isCombined, ColorType color){
        //Spread to left
        for (int i = 1; i <= range; i++) {
            Position left = position.offset(0, -i);
            if(left == null) break; // Out of bounds
            ISquare square = garden.getGardenSquareByPosition(left).getSquareObject();
            if(garden.getGardenSquareByPosition(left).getIsOccupied()){
                switch (square.getClass().getSimpleName()) {
                    case ("GardenPlant"): 
                        GardenPlant gardenPlant = (GardenPlant) square;
                        (gardenPlant).reflectionOfPlants(garden, left,  gardenPlant.takePollenType(), this.color);
                        break;
                    
                }
                break;
             } // Stop spreading in this direction
            if(isCombined){
                garden.addColorToSquare(left, color);
            }
            garden.addColorToSquare(left, this.color);
        }
    }

    //spreadDown method
    /**
     * Spreads the light source downwards in the garden from the given position within the specified range.
     *
     * @param garden The garden where the light source is spreading.
     * @param position The starting position of the light source.
     * @param range The range within which the light source spreads.
     * @param isCombined A boolean indicating whether the light source is combined with another color.
     * @param color The color of the light source.
     */
    private void spreadDown(Garden garden, Position position, int range,   boolean isCombined, ColorType color){
        //Spread to down
        for (int i = 1; i <= range; i++) {
            Position down = position.offset(i, 0);
            if(down == null) break; // Out of bounds
            ISquare square = garden.getGardenSquareByPosition(down).getSquareObject();
            if(garden.getGardenSquareByPosition(down).getIsOccupied()){
                switch (square.getClass().getSimpleName()) {
                    case ("GardenPlant"): 
                        GardenPlant gardenPlant = (GardenPlant) square;
                        (gardenPlant).reflectionOfPlants(garden, down,  gardenPlant.takePollenType(), this.color);
                        break;
                    
                }
                break;
             } // Stop spreading in this direction
            if(isCombined){
                garden.addColorToSquare(down, color);
            }
            garden.addColorToSquare(down, this.color);
        }
    }

   
}

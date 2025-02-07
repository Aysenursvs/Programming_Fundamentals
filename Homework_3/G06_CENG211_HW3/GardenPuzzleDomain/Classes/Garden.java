package GardenPuzzleDomain.Classes;
import GardenPuzzleDomain.Enums.ColorType;
import GardenPuzzleDomain.Enums.PollenType;
import GardenPuzzleDomain.Enums.Position;
import GardenPuzzleDomain.Interfaces.ISearchable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Garden class represents a garden with a grid of GardenSquare objects.
 * It provides methods to initialize the garden, add objects to the garden,
 * and manipulate the garden squares. The garden is represented as a 6x8 grid.
 * 
 * The class includes constructors for creating a garden, methods for adding
 * objects and squares to the garden, and methods for printing and retrieving
 * garden squares. It also includes methods for adding pollen and color to
 * specific squares in the garden.
 * 
 * The class implements the cloneable interface and provides equals and toString
 * methods for comparing and representing garden objects.
 * 
 */
public class Garden {
    private List<ISearchable> gardenStorage = new ArrayList<ISearchable>(); 
    private List<ArrayList<GardenSquare>> gardenSquares = new ArrayList<ArrayList<GardenSquare>>();
    
    

    /**
     * Initializes the garden by creating a 6x8 grid of GardenSquare objects.
     * Each GardenSquare is assigned a position based on its row and column index.
     * The gardenSquares list is populated with rows of GardenSquare objects.
     */
    private void initializeGarden(){
        for (int i = 0; i < 6; i++) {
            ArrayList<GardenSquare> row = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                Position position = Position.values()[(i * 8 + j)];
                row.add(new GardenSquare(null, position, false));
            }
            gardenSquares.add(row);
        }
    }

    // Default constructor
    public Garden() {
        initializeGarden();
        
    }

    // Copy constructor
    public Garden(Garden other) {
        this.gardenStorage = new ArrayList<>(other.gardenStorage);
        this.gardenSquares = new ArrayList<>(other.gardenSquares);
        
    }

    // Parameterized constructor
    public Garden(List<ISearchable> gardenStorage, List<ArrayList<GardenSquare>> gardenSquares, GardenSquare target) {
        initializeGarden();
        this.gardenStorage= gardenStorage;
        this.gardenSquares = gardenSquares;
        

    }

    // Getters and Setters
    public List<ISearchable> getGardenStorage() {
        return new ArrayList<>(gardenStorage);
    }

    public void setGardenStorage(List<ISearchable> gardenStorage) {
        this.gardenStorage = gardenStorage;
    }

    public List<ArrayList<GardenSquare>> getGardenSquares() {
        return new ArrayList<>(gardenSquares);
    }

    public void setGardenSquares(List<ArrayList<GardenSquare>> gardenSquares) {
        this.gardenSquares = gardenSquares;
    }

    

    // Equals method for Object
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Garden garden = (Garden) obj;
        return gardenStorage.equals(garden.gardenStorage) && gardenSquares.equals(garden.gardenSquares);
    }

    // Equals method for Garden
    public boolean equals(Garden garden) {
        if (this == garden) return true;
        if (garden == null) return false;
        return gardenStorage.equals(garden.gardenStorage) && gardenSquares.equals(garden.gardenSquares);
    }

    // toString method
    @Override
    public String toString() {
        return "Garden: " +
                "Garden Objects: " + gardenStorage +
                ", Garden Squares: " + gardenSquares;
    }

    // Clone method
    @Override
    public Garden clone() {
        return new Garden(this);
    }

    // Add garden object to garden
    public void addGardenObject(ISearchable gardenObject) {
        gardenStorage.add(gardenObject);
    }

    // Add garden square to garden
    public void addGardenSquare(ArrayList<GardenSquare> gardenSquare) {
        gardenSquares.add(gardenSquare);
    }

    
    

    /**
     * Prints the garden layout to the console.
     * The garden is represented as a grid with rows labeled A-F and columns labeled 1-8.
     * Each cell in the grid can contain a GardenObject, a PollenCloud, or be empty.
     * If the cell matches the target position and the specified map number is 1, it will be marked as "Target".
     *
     * @param target The GardenSquare object representing the target position.
     * @param numberOfWhichMapWePrinted An integer indicating which map is being printed.
     */
    public void printGarden(GardenSquare target, int numberOfWhichMapWePrinted){
        String[] rows = {"A", "B", "C", "D", "E", "F"};
        String[] columns = {"1", "2", "3", "4", "5", "6", "7", "8"};
        for (int a = 0; a < 8; a++) {
            if (a == 0) {
                System.out.print("  ");
            }
            System.out.print("   " + columns[a] + "   ");
        }
        System.out.println();
        System.out.println("  " + "-".repeat(57));
        for (int i = 0; i < 6; i++) {
            System.out.print(rows[i] + " ");
            for (int j = 0; j < 8; j++) {
                if(gardenSquares.get(i).get(j).getPosition().equals(target.getPosition()) && numberOfWhichMapWePrinted == 1){
                    System.out.print("|" + "Target");
                }else if(gardenSquares.get(i).get(j).getSquareObject() != null){
                   if(gardenSquares.get(i).get(j).getSquareObject() instanceof GardenObject){
                    System.out.print("|  " + ((GardenObject)gardenSquares.get(i).get(j).getSquareObject()).getId() + "  ");
                    }else if(gardenSquares.get(i).get(j).getSquareObject() instanceof PollenCloud){
                    System.out.print("|" + ((PollenCloud)gardenSquares.get(i).get(j).getSquareObject()).printPollenCloud()); 
                    }
                
                }else{
                    System.out.print("|" + "      ");
                }
                }
            System.out.println("|");
            System.out.println("  " + "-".repeat(57));
        }
    }

    /**
     * Retrieves a GardenSquare object from the gardenSquares list based on the given position.
     *
     * @param position the Position object representing the coordinates to search for.
     * @return the GardenSquare object at the specified position, or null if no such GardenSquare exists.
     */
    public GardenSquare getGardenSquareByPosition(Position position){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                if(gardenSquares.get(i).get(j).getPosition().equals(position)){
                    return gardenSquares.get(i).get(j);
                }
            }
        }
        return null;
    }

    /**
     * Adds a specified type of pollen to a garden square at the given position.
     * If the square is not occupied and does not already contain a square object,
     * a new PollenCloud object is created and added to the square.
     * 
     * @param position The position of the garden square where the pollen is to be added.
     * @param pollenType The type of pollen to be added to the garden square.
     */
    public void addPollenToSquare(Position position, PollenType pollenType) {
        GardenSquare square = getGardenSquareByPosition(position);
        
        if (square != null && !square.getIsOccupied()) {
            if(square.getSquareObject() == null){
                square.setSquareObject(new PollenCloud());
                square.setIsOccupied(false);
            }
            ((PollenCloud)square.getSquareObject()).addPollenType(pollenType);
        }
    }

    /**
     * Adds a specified color to the square at the given position in the garden.
     * If the square is not occupied and does not already have a square object,
     * a new PollenCloud object is created and assigned to the square.
     * The specified color is then added to the PollenCloud object.
     *
     * @param position the position of the square in the garden
     * @param colorType the color to be added to the square
     */
    public void addColorToSquare(Position position, ColorType colorType) {
        GardenSquare square = getGardenSquareByPosition(position);
        
        if (square != null && !square.getIsOccupied()) {
            if(square.getSquareObject() == null){
                square.setSquareObject(new PollenCloud());
                square.setIsOccupied(false);
            }
            ((PollenCloud)square.getSquareObject()).addColorType(colorType);
        }
    }

    

}

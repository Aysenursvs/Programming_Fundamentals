package FrozenLakeDomain.LakePackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import FrozenLakeDomain.HazardsPackage.CliffEdge;
import FrozenLakeDomain.HazardsPackage.HoleInIce;
import FrozenLakeDomain.HazardsPackage.IceBlock;
import FrozenLakeDomain.HazardsPackage.IceSpike;
import FrozenLakeDomain.InterfacesPackage.IEffectable;
import FrozenLakeDomain.InterfacesPackage.ISquare;
/**
 * The FrozenLake class represents a frozen lake with various elements such as ice blocks, ice spikes, holes, and cliffs.
 * It provides methods to initialize, manipulate, and retrieve information about the lake.
 */
public class FrozenLake {
    private List<ArrayList<ISquare>> lake = new ArrayList<ArrayList<ISquare>>();
    private static final int numberOfRows = 8;
    private static final int numberOfColumns = 11;
    private static final int rowEntrance = 0;
    private static final int columnEntrance = (numberOfColumns + 2)/2;
    private Random random = new Random();
    private int cliffSide;


    //initialize the lake
    private void initializeLake() {
        List<EdgeSquare> wallLakeSquares = addEdgesObject();
        placeIceBlocksToLake();
        placeIceSpikeToLake(wallLakeSquares);
        placeHoleInIceToLake(); 

    }

    /**
     * Creates the lake with specified number of rows and columns.
     * The lake is represented as a 2D ArrayList of ISquare objects.
     * The outer edges of the lake are filled with EdgeSquare objects,
     * while the inner part is filled with LakeSquare objects.
     * 
     * The lake is surrounded by an additional row and column on each side
     * to represent the edges.
     * 
     * The method initializes the lake by iterating through the rows and columns,
     * and adding the appropriate ISquare objects to the lake.
     */
    private void createLake() {
        for (int i = 0; i < numberOfRows + 2; i++) {
            if(i == 0 || i == numberOfRows + 1){
                ArrayList<ISquare> edgeRow = new ArrayList<>();
                for(int j = 0; j < numberOfColumns + 2; j++){
                    edgeRow.add(new EdgeSquare(new ArrayList<IEffectable>(), i, j)); 
                }
                lake.add(edgeRow);
            }
            else{
                ArrayList<ISquare> row = new ArrayList<>();
                for (int j = 0; j < numberOfColumns + 2; j++) {
                    if(j == 0 || j == numberOfColumns + 1){
                        row.add(new EdgeSquare(new ArrayList<IEffectable>(), i, j));
                    }
                    else{
                        row.add(new LakeSquare(new ArrayList<IEffectable>(), i, j));}
                }
                lake.add(row);
            }
        }
    }

    // Default constructor
    public FrozenLake() {
        createLake();
        initializeLake();
    }

    // Parameterized constructor
    public FrozenLake(List<ArrayList<ISquare>> lake, int cliffSide) {
        this.lake = lake;
        this.cliffSide = cliffSide;
        initializeLake();
    } 

    // Copy constructor
    public FrozenLake(FrozenLake other) {
        if (other.lake == null) {
            System.out.println("The lake is null");
            System.exit(0);
        }
        this.lake = new ArrayList<>();
        for (ArrayList<ISquare> row : other.lake) {
            ArrayList<ISquare> rowCopy = new ArrayList<>();
            for (ISquare square : row) {
                rowCopy.add(square.clone());
            }
            this.lake.add(rowCopy);
        }
        this.cliffSide = other.cliffSide;
    }

    // Getter and setter methods
    public List<ArrayList<ISquare>> getLake() {
        List<ArrayList<ISquare>> lakeCopy = new ArrayList<>();
        for (ArrayList<ISquare> row : lake) {
            ArrayList<ISquare> rowCopy = new ArrayList<>();
            for (ISquare square : row) {
                rowCopy.add(square.clone());
            }
            lakeCopy.add(rowCopy);
        }
        
        return lakeCopy;
    }

    public void setLake(List<ArrayList<ISquare>> lake) {
        this.lake = lake;
    }

    public int getCliffSide() {
        return cliffSide;
    }

    public void setCliffSide(int cliffSide) {
        this.cliffSide = cliffSide;
    }

    // toString method
    @Override
    public String toString() { 
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                sb.append(lake.get(i).get(j).toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // equals method that takes an Object as a parameter
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        FrozenLake that = (FrozenLake) obj;
        return cliffSide == that.cliffSide &&
                lake.equals(that.lake);
    }

    // equals method that takes a FrozenLake as a parameter
    public boolean equals(FrozenLake other) {
        if(other == null) return false;
        return cliffSide == other.cliffSide &&
                lake.equals(other.lake);
    }

    //clone method
    @Override
    public FrozenLake clone() {
        return new FrozenLake(this);
    }

    /**
     * Sets an effectable object to a specific location in the lake.
     *
     * @param lakeSquareObject the effectable object to be added to the lake
     * @param row the row index of the lake where the object will be placed
     * @param column the column index of the lake where the object will be placed
     */
    public void setToLakeObjectByRowAndColumn(IEffectable lakeSquareObject, int row, int column) {
        ((lake.get(row).get(column))).addEffectable(lakeSquareObject); 
    }

    /**
     * Adds edges to the lake by placing cliff edges and walls.
     * 
     * This method first decides the cliff side and adds the cliff edge to the lake.
     * Then, it iterates through the lake grid and adds walls to the edge squares
     * that are not the entrance and do not already contain any objects.
     * 
     * @return A list of EdgeSquare objects that have walls added to them.
     */
    private List<EdgeSquare> addEdgesObject() {
        //Decide cliff side and add cliff edge to the lake
        placeCliffEdgeToLake();

        // Add walls to the lake
        List<EdgeSquare> wallLakeSquares = new ArrayList<EdgeSquare>();
        for(int i = 0; i < numberOfRows + 2; i++){
            for(int j = 0; j < numberOfColumns + 2; j++){
                ISquare square = lake.get(i).get(j);
                if(square instanceof EdgeSquare){
                    if(!(i == rowEntrance && j == columnEntrance) && !(lake.get(i).get(j).containsAny())){
                        square.addEffectable(new Wall());
                        wallLakeSquares.add((EdgeSquare)square);
                    }
                
                }
            }
        }
        return wallLakeSquares;
    }

    /**
     * Places a cliff edge on one of the sides of the lake.
     * The cliff can be placed on the left side (column 0), right side (column 10), or bottom side (row 7).
     * The side is chosen randomly.
     * 
     * The method modifies the lake by adding CliffEdge objects to the appropriate cells.
     * 
     * If an invalid cliff side is chosen, the method prints an error message and exits the program.
     */
    private void placeCliffEdgeToLake() { 
        // Add cliff to the lake
        cliffSide = random.nextInt(3);  //decide the cliff side
        switch(cliffSide) {
            // 0: left side, column = 0; 1: right side, column = 10; 2: bottom side, row = 7
            case 0:
                for (int i = 1; i < numberOfRows + 1; i++) {
                    (lake.get(i).get(0)).addEffectable(new CliffEdge());
                    //edgeobjects null
                }
                break;
            case 1:
                for (int i = 1; i < numberOfRows + 1; i++) {
                    (lake.get(i).get(numberOfColumns + 1)).addEffectable(new CliffEdge());
                }
                break;
            case 2:
                for (int i = 1; i < numberOfColumns + 1; i++) {
                    (lake.get(numberOfRows + 1).get(i)).addEffectable(new CliffEdge());
                }
                break;
            default:
                System.out.println("Invalid cliff side");
                System.exit(0);
        }
    }

    /**
     * Places ice blocks in the lake according to specific rules:
     * 1. Adds an ice block to the middle column of a randomly selected row, excluding the first row.
     * 2. Adds two ice blocks to the cliff side:
     *    - If the cliff side is at the bottom (cliffSide == 2), places the ice blocks in random columns of the last row.
     *    - Otherwise, places the ice blocks in random rows of the first or last column, depending on the cliff side.
     * 3. Adds remaining ice blocks to random positions in the lake, ensuring they do not block the entrance.
     */
    private void placeIceBlocksToLake() {
        List<Integer> unusedRows = new ArrayList<>();
        for (int i = 1; i < numberOfRows + 1; i++) {
            unusedRows.add(i);
        }

        int row;

        //Add 2 iceBlocks to the cliffSide
        if(cliffSide == 2){ //Bottom side
            for(int i = 0 ; i < 2;i++ ){
                int column = random.nextInt(1, numberOfColumns + 1);
                if(!lake.get(numberOfRows).get(column).containsAny())
                    setToLakeObjectByRowAndColumn(new IceBlock(), numberOfRows, column);
                else i--;
            }
            unusedRows.remove(unusedRows.indexOf(numberOfRows)); //8 , last row removed
        }else{
            for(int i = 0;i < 2;i++ ){
                Collections.shuffle(unusedRows);
                row = unusedRows.remove(0);
                if(cliffSide == 0){
                    setToLakeObjectByRowAndColumn(new IceBlock(), row, 1);
                }
                else{
                    setToLakeObjectByRowAndColumn(new IceBlock(), row, numberOfColumns);
                }
            }

        //Add an iceBlock to middleColumn of a row
        
        do { 
            Collections.shuffle(unusedRows);
            row = unusedRows.get(0);
        } while (row == 1);
        setToLakeObjectByRowAndColumn(new IceBlock(), row, columnEntrance);
        unusedRows.remove(0);

        
        }

        //Add remaining iceBlocks
        while (unusedRows.size() > 0) {
            Collections.shuffle(unusedRows);
            row = unusedRows.remove(0);
            int column;
            do {
                column = random.nextInt(1, numberOfColumns + 1);
            } while (column == columnEntrance && row == rowEntrance + 1);
            setToLakeObjectByRowAndColumn(new IceBlock(), row, column);
        }
    }

    /**
     * Places ice spikes in the lake at random positions.
     * The method shuffles the list of wall lake squares and attempts to place
     * ice spikes in up to three different locations. The placement is done
     * based on the position of the edge squares and ensures that the ice spikes
     * are not placed on the entrance or on squares that already contain hazards.
     *
     * @param wallLakeSquares A list of EdgeSquare objects representing the wall lake squares.
     */
    public void placeIceSpikeToLake(List<EdgeSquare> wallLakeSquares) {
        // remove corner wall objects
        List<EdgeSquare> wallSquares = new ArrayList<>(wallLakeSquares);
        wallSquares.remove(lake.get(0).get(0));
        wallSquares.remove(lake.get(0).get(12));
        wallSquares.remove(lake.get(9).get(0));
        wallSquares.remove(lake.get(9).get(12));
            
        
        for(int i = 0; i < 3; i++){
            Collections.shuffle(wallSquares);
            EdgeSquare square = wallSquares.get(0);
            int squareRow = square.getRow();
            int squareColumn = square.getColumn();
            int updatedRow = squareRow;
            int updatedColumn = squareColumn;
            if(squareRow == 0){
                if(Math.abs(squareColumn - columnEntrance) >= 3)
                    updatedRow += 1;
                else {
                    i--;
                    continue;
                }
                
            }
            else if(squareRow == 9){
                updatedRow -= 1;
            }
            else if(squareColumn == 12){
                updatedColumn -= 1;
            }
            else if(squareColumn == 0){
                updatedColumn += 1;
            }

            if(!lake.get(updatedRow).get(updatedColumn).containsAnyHazard() && !isNearToHazard((LakeSquare)lake.get(updatedRow).get(updatedColumn),"CliffEdge")){
               setToLakeObjectByRowAndColumn(new IceSpike(), updatedRow, updatedColumn);
            }
            else{
                i--;
            }
        }
    }

    /**
     * Places three holes in the ice at random positions in the lake.
     * The holes are placed such that they are at least 3 rows and 3 columns away from the entrance.
     * A hole is only placed if the selected square does not already contain any hazards and is not near a cliff edge.
     * 
     * Preconditions:
     * - The lake is initialized and contains squares.
     * - The entrance position (rowEntrance, columnEntrance) is defined.
     * - The random number generator is initialized.
     * 
     * Postconditions:
     * - Three holes are added to the lake at valid positions.
     */
    public void placeHoleInIceToLake() {
        int holesPlaced = 0;
        while (holesPlaced < 3) {
            int row = random.nextInt(1, numberOfRows + 1);
            int column = random.nextInt(1, numberOfColumns + 1);
            if (Math.abs(row - rowEntrance) >= 3 && Math.abs(column - columnEntrance) >= 3) {
                ISquare square = lake.get(row).get(column);
                if (!square.containsAnyHazard() && !isNearToHazard((LakeSquare)square,"CliffEdge")) {
                    square.addEffectable(new HoleInIce());
                    holesPlaced++;
                }
            }
        }
    }

    /**
     * Checks if the given LakeSquare has a direct view to the cliff side without any hazards in between.
     * Especially useful for placing research equipment such as cameras.
     *
     * @param square the LakeSquare to check for direct view to the cliff side
     * @return true if there is a direct view to the cliff side without any hazards, false otherwise
     */
    public boolean hasDirectViewToCliffSide(LakeSquare square){
        int row = square.getRow();
        int column = square.getColumn();

        switch(cliffSide){
            case 0:
                for(int c = column - 1; c > 0; c--){
                    if(lake.get(row).get(c).containsAnyHazard()){
                        return false;
                    }
                }
                return column > 0;
            case 1:
            for(int c = column + 1; c < numberOfColumns + 1; c++){
                if(lake.get(row).get(c).containsAnyHazard()){
                    return false;
                    }
                }
                return column < numberOfColumns + 1;
            case 2:
                for(int r = row + 1; r < numberOfRows + 1; r++){
                    if(lake.get(r).get(column).containsAnyHazard()){
                        return false;
                    }
                }
                return row < numberOfRows + 1;
            default:
                return false;
        }
    }

    /**
     * Checks if the given LakeSquare is near to a hazard of the specified type.
     * A square is considered near to a hazard if there is a hazard of the specified type
     * in any of the adjacent squares (right, left, down, or up).
     *
     * @param square the LakeSquare to check for proximity to a hazard
     * @param hazardType the type of hazard to check for
     * @return true if the given square is near to a hazard of the specified type, false otherwise
     */
    public boolean isNearToHazard(LakeSquare square,String hazardType){ 
        int row = square.getRow();
        int column = square.getColumn();

        //for the right
        if(this.getSquare(row, column+1).getHazard(hazardType) != null){
            return true;
        }
        //for the left
        else if(this.getSquare(row, column-1).getHazard(hazardType) != null){
            return true;
        }
        //for the down
        else if(this.getSquare(row+1, column).getHazard(hazardType) != null){
            return true;
        }
        //for the up
        else if(this.getSquare(row-1, column).getHazard(hazardType) != null){
            return true;
        }
        else return false;
    }

    /**
     * Checks if the given LakeSquare is near to an edge.
     *
     * This method determines if the specified LakeSquare is adjacent to any EdgeSquare
     * in the FrozenLake. It checks all four possible directions (right, left, down, up)
     * to see if any neighboring square is an instance of EdgeSquare.
     *
     * @param square the LakeSquare to check
     * @return true if the given LakeSquare is near to an edge, false otherwise
     */
    public boolean isNearToEdge(LakeSquare square){
        int row = square.getRow();
        int column = square.getColumn();

        //for the right
        if(this.getSquare(row, column+1) instanceof EdgeSquare){
            return true;
        }
        //for the left
        else if(this.getSquare(row, column-1) instanceof EdgeSquare){
            return true;
        }
        //for the down
        else if(this.getSquare(row+1, column) instanceof EdgeSquare){
            return true;
        }
        //for the up
        else if(this.getSquare(row-1, column) instanceof EdgeSquare){
            return true;
        }
        else return false;
    }

    /**
     * Retrieves the square at the specified row and column in the lake.
     *
     * @param row the row index of the square to retrieve
     * @param column the column index of the square to retrieve
     * @return the square at the specified row and column
     */
    public ISquare getSquare(int row, int column){ 
        return (lake.get(row).get(column));
    }

    /**
     * Prints the current state of the frozen lake.
     * The lake is represented as a grid with each cell displayed in uppercase.
     * The grid's dimensions and separator lines are adjusted based on the cliffSide value.
     * 
     * The method uses the following parameters:
     * - squareWidth: The width of each cell in the grid.
     * - rowEnd: The ending row index for the grid, adjusted based on the cliffSide value.
     * - colStart: The starting column index for the grid, adjusted based on the cliffSide value.
     * - colEnd: The ending column index for the grid, adjusted based on the cliffSide value.
     * - numberOfSeperationLines: The number of dashes used for separator lines, adjusted based on the cliffSide value.
     * 
     * The method iterates through the rows and columns of the lake grid, printing each cell's content
     * followed by a separator line.
     */
    public void printLake(){
        int squareWidth = 10;
        int rowEnd = (cliffSide == 2) ? numberOfRows + 2 : numberOfRows + 1;
        int colStart = (cliffSide == 0) ? 0 : 1;
        int colEnd = (cliffSide == 1) ? numberOfColumns + 2 : numberOfColumns + 1;
        int numberOfSeperationLines = (cliffSide == 2) ? 111 : 121;
        for (int i = 1; i < rowEnd; i++) {
            System.out.println("-".repeat(numberOfSeperationLines));  // Print separator line
            for (int j = colStart; j < colEnd; j++) {
                System.out.printf("%-" + squareWidth + "s", lake.get(i).get(j).toString().toUpperCase());
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("-".repeat(numberOfSeperationLines));
    }

    /**
     * Checks if moving from the current position by the specified row and column offsets
     * results in a valid position within the bounds of the lake.
     *
     * @param currentRow the current row position
     * @param currentColumn the current column position
     * @param dRow the row offset to move
     * @param dCol the column offset to move
     * @return true if the new position is within bounds, false otherwise
     */
    public boolean isValidDirection(int currentRow, int currentColumn, int dRow, int dCol) {
        int newRow = currentRow + dRow;
        int newCol = currentColumn + dCol;
        if(isWithinBounds(newRow, newCol))return true;
        else return false;
    }
    
    /**
     * Checks if the given row and column indices are within the bounds of the lake.
     *
     * @param newRow the row index to check
     * @param newCol the column index to check
     * @return true if the indices are within bounds or if the position is the entrance, false otherwise
     */
    private boolean isWithinBounds(int newRow, int newCol) { 
        if((newRow > 0 && newRow <= numberOfRows) && (newCol > 0 && newCol <= numberOfColumns) || isEntrance(newRow, newCol)) return true;
        else return false;
    }
    
    /**
     * Checks if the given coordinates correspond to the entrance of the lake.
     *
     * @param currentRow the row index to check
     * @param currentColumn the column index to check
     * @return true if the specified coordinates are the entrance, false otherwise
     */
    public boolean isEntrance(int currentRow, int currentColumn) { 
        if(currentRow == rowEntrance  && currentColumn == columnEntrance) return true;
        else return false;
    }
}

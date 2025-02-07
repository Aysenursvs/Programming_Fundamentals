package GardenPuzzleDomain.Enums;

/**
 * The {@code Position} enum represents positions in a grid with rows labeled A to F and columns labeled 1 to 8.
 * Each position is represented by a combination of a letter (A-F) and a number (1-8).
 * The enum also includes a {@code DEFAULT} position.
 * The {@code Position} enum provides a method to calculate a new position based on row and column offsets.
 * 
 */
public enum Position {
    A1, A2, A3, A4, A5, A6, A7, A8, 
    B1, B2, B3, B4, B5, B6, B7, B8,  
    C1, C2, C3, C4, C5, C6, C7, C8,  
    D1, D2, D3, D4, D5, D6, D7, D8, 
    E1, E2, E3, E4, E5, E6, E7, E8, 
    F1, F2, F3, F4, F5, F6, F7, F8, 
    DEFAULT;

    private static final int COLUMNS = 8;

    /**
     * Returns a new Position that is offset from the current position by the specified row and column offsets.
     * If the resulting position is out of bounds, null is returned.
     *
     * @param rowOffset the number of rows to offset from the current position
     * @param colOffset the number of columns to offset from the current position
     * @return the new Position after applying the offsets, or null if the new position is out of bounds
     */
    public Position offset(int rowOffset, int colOffset) {
        int currentRow = this.ordinal() / COLUMNS;
        int currentCol = this.ordinal() % COLUMNS;

        int newRow = currentRow + rowOffset;
        int newCol = currentCol + colOffset;

        // Check bounds
        if (newRow < 0 || newRow >= 6 || newCol < 0 || newCol >= COLUMNS) {
            return null; // Out of bounds
        }

        int newOrdinal = newRow * COLUMNS + newCol;
        return Position.values()[newOrdinal];
    }
}

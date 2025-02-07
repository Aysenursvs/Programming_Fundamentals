package FrozenLakeDomain.LakePackage;
/**
 * The Direction enum represents the four possible directions (UP, DOWN, LEFT, RIGHT)
 * that can be used to navigate in a grid or matrix. Each direction is associated with
 * a change in row and column indices.
 */
public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    private final int dRow;
    private final int dCol;

    /**
     * Constructs a Direction with the specified row and column changes.
     *
     * @param dRow the change in the row direction
     * @param dCol the change in the column direction
     */
    Direction(int dRow, int dCol) {
        this.dRow = dRow;
        this.dCol = dCol;
    }

    public int getDRow() {
        return dRow;
    }

    public int getDCol() {
        return dCol;
    }
}

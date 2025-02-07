package GardenPuzzleDomain.Enums;

/**
 * The PlantType enum represents different types of plants that can be used in the garden puzzle domain.
 * It includes the following types:
 * 
 * The enum also provides a method to choose a plant type based on an integer input.
 * 
 */
public enum PlantType {
    DEFAULT, 
    FLOWER, 
    TREE, 
    BUSH;

    public static PlantType choosePlant(int number) {
        switch (number) {
            case 1:
                return FLOWER;
            case 2:
                return TREE;
            case 3:
                return BUSH;
            default:
                return DEFAULT;
        }
    }
}

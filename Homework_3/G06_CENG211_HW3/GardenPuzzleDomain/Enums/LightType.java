package GardenPuzzleDomain.Enums;

/**
 * The {@code LightType} enum represents different types of lighting options available.
 * It provides a method to choose a specific type of light based on an integer input.
 * 
 */
public enum LightType {
    DEFAULT, 
    SMALL_LAMP, 
    LARGE_LAMP, 
    SPOTLIGHT;

    public static LightType chooseLight(int number) {
        switch (number) {
            case 1: 
                return SMALL_LAMP;
            case 2:
                return LARGE_LAMP;
            case 3:
                return SPOTLIGHT;
            default:
                return DEFAULT;
        }
    }
}

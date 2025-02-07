package GardenPuzzleDomain.Enums;

/**
 * The {@code ColorType} enum represents different color types that can be used in the application.
 * It provides a method to choose a color based on an integer input.
 * 
 */
public enum ColorType {
    DEFAULT,
    RED,
    BLUE,
    GREEN;

    public static ColorType chooseColor(int number) {
        switch (number) {
            case 1:
                return RED;
            case 2:
                return GREEN;
            case 3:
                return BLUE;
            default:
                return DEFAULT;
        }
    }
}

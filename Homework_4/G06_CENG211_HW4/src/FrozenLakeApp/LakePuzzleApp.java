package FrozenLakeApp;

/**
 * The LakePuzzleApp class is the entry point for the Frozen Lake Puzzle application.
 * It initializes a new instance of the LakePuzzle game and starts and finishes the game.
 * 
 * This class contains the main method which serves as the starting point of the application.
 * 
 * @author 300201044 Ekin Ece Bayrak
 * @author 300201051 Ayşenur Sivaslıgil
 * @author 300201052 Mustafa Erkoca
 * @author 310201060 Büşra Şeyma Küyner 
 */
public class LakePuzzleApp {
    public static void main(String[] args) throws Exception {
        LakePuzzle game = new LakePuzzle();
        game.start();
        game.finish();
    }
}

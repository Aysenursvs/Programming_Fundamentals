package TreasureHuntApp;
import ScoreboardWriter.ScoreboardWriter;
import TreasureHunt.Map;
import TreasureHunt.MapItem;
import TreasureHunt.Player;
import TreasureHunt.Scoreboard;
import java.util.ArrayList;

/**
 * The App class is the main entry point for the Treasure Hunt application.
 * It initializes the game components such as the map, scoreboard, and player,
 * and runs the game loop until the game is over.
 * 
 * The game loop continues as long as the player is alive, has points, and there are special items on the map.
 * During each iteration, the map is printed, and the player makes a random step.
 * 
 * Once the game is over, the final map is printed, and the highest score achieved during the play is displayed.
 * The scores are then written to a file named "Scores.txt".
 * 
 * @author 300201044 Ekin Ece Bayrak
 * @author 300201051 Ayşenur Sivaslıgil
 * @author 300201052 Mustafa Erkoca
 * @author 310201060 Büşra Şeyma Küyner
 */
public class App {
    public static void main(String[] args) throws Exception {
        Map map = new Map();
        Scoreboard scores = new Scoreboard();
        Player player = new Player();

        ArrayList<MapItem> specialItems = map.getSpecialItems();
        map.addMapItem(player);
        
        // game loop continues as long as the player is alive, has points, and there are special items on the map
        while ( player.getLive() > 0 && player.getPoint() > 0 && specialItems.size() > 0) {
            map.printMap();
            System.out.println(repeatString("*", 100));
            player.nextRandomStep(player, map, specialItems, scores);
            
        } 
        // print the final map 
        map.printMap(); 

        // display the highest score achieved during the play
        System.out.println(repeatString("*",44) +  " GAME  OVER " + repeatString("*",44));
        System.out.println(repeatString("-", 26) + " The Highest Score Achieved During the Play: " + scores.maxScore().getUpdatedPoint() + " " + repeatString("-", 26));
        ScoreboardWriter fileWriter = new ScoreboardWriter();
        fileWriter.writeScoreboardToFile(scores, "Scores.txt");
    }

    // helper method to repeat a string
    private static String repeatString(String str, int count) {
        return new String(new char[count]).replace("\0", str);
    }
}

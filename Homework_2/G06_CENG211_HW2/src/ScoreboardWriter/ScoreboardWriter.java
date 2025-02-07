package ScoreboardWriter;
import TreasureHunt.Score;
import TreasureHunt.Scoreboard;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The ScoreboardWriter class provides a method to write the scores from a Scoreboard
 * object to a file.
 * 
 * @author 300201044 Ekin Ece Bayrak
 * @author 300201051 Ayşenur Sivaslıgil
 * @author 300201052 Mustafa Erkoca
 * @author 310201060 Büşra Şeyma Küyner
 */
public class ScoreboardWriter {
    public void writeScoreboardToFile(Scoreboard scoreboard, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Score score : scoreboard.getScores()) {
                writer.write(score.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

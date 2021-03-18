package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Zohaib Ahmed, Zohaib.Ahmed@city.ac.uk
 *
 * Demonstrates how high-score data can be read from a text file and printed to the terminal.
 */
public class HighScoreReader {
    /**
     * Gets the file name which will be loaded.
     */
    private String fileName;
    public static String Scores;
    int count = 0;
    /**
     * Create a string array to store all the names and the scores of the players
     */
    public static String[][] quickestTimes = new String[10][2];

    /**
     * Initialise a new HighScoreReader
     * @param fileName the name of the high-score file
     */
    public HighScoreReader(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Read the high-score data from the high-score file
     * <p>
     * Reads the high score data from the file and appends it to the String array making it ready for display.
     *
     * @throws IOException Throws an IO Exception when there is a failure reading or writing to a file.
     */
    public void readScores() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (count != 10) {
                // file is assumed to contain one name, score pair per line
                String[] tokens = line.split(",");
                String name = tokens[0];
                String score = tokens[1];
                Scores = ("Name: " + name + ", Score: " + score);
                quickestTimes[count][0] = name;
                quickestTimes[count][1] = score;
                line = reader.readLine();
                count++;
            }
        }
        finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }
}

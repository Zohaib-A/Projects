package game;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Zohaib Ahmed, Zohaib.Ahmed@city.ac.uk
 *
 * Demonstrates how high-score data can be written to a text file.
 */
public class HighScoreWriter {
    /**
     * Gets the file name which will be loaded.
     */
    private String fileName;

    /**
     * Initialises a new HighScoreWriter
     * @param fileName The name of the high score file.
     */
    public HighScoreWriter(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Writes the high score to the text file
     * @param name Takes the player's name as a parameter and appends it with the relative score.
     * @param score Takes the player's score as a parameter and appends it to the text file.
     * @throws IOException Throws an IO Exception when there is a failure reading or writing to a file.
     */
    public void writeHighScore(String name, int score) throws IOException {
        boolean append = true;
        // Adds the score and names to the text file
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(name + "," + score + "\n");
        }
    }
}
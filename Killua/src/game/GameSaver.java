package game;

import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Zohaib Ahmed, Zohaib.Ahmed@city.ac.uk
 */

public class GameSaver {
    /**
     * Gets the file name which will be loaded.
     */
    private String fileName;

    /** Gets the name of the file which the class will write to.
     *
     * @param fileName The name of the file which will store the game state.
     */
    public GameSaver(String fileName) {
        this.fileName = fileName;
    }

    /** Writes the current game state to the file.
     * <p>
     * Takes all the current game information and stores it within the file and separates them all by name and item type.
     *
     * @param gameWorld Allows the class to get all the information needed to save all the information.
     * @throws IOException Throws an IO Exception when there is a failure reading or writing to a file.
     */
    public void saveGame(GameLevel gameWorld) throws IOException {
        boolean append = true;
        FileWriter writer = null;
        try {
            // Overwrites the current data with the new data
            writer = new FileWriter(fileName, false);
            //Writes the level number
            writer.write(gameWorld.getLevelNumber() + "\n");
            // Writes the players class name, co-ordinates and money
            writer.write(gameWorld.getPlayer().getClass().getSimpleName() + "," + gameWorld.getPlayer().getPosition().x +
                    "," + gameWorld.getPlayer().getPosition().y + "," + gameWorld.getPlayer().getMoneyCount() + "\n");

            // Saves the name of the dynamic body classes which are not the player and their positions
            for (DynamicBody body: gameWorld.getDynamicBodies()){
                if (!(body instanceof Killua)) {
                    writer.write(body.getClass().getSimpleName() + "," + body.getPosition().x + "," + body.getPosition().y + "\n");
                }
            }

            // Saves the static body position and size of the platforms
            for (StaticBody body: gameWorld.getStaticBodies()){
                if (body instanceof Platform)
                writer.write(body.getClass().getSimpleName() + "," + body.getPosition().x + "," + body.getPosition().y +
                        "," + ((Platform)body).getWidth() + "," + ((Platform)body).getHeight() + "\n");
                else{
                    writer.write(body.getClass().getSimpleName() + "," + body.getPosition().x + "," + body.getPosition().y + "\n");
                }
            }

        } finally {
            if (writer != null){
                writer.close();
            }
        }
    }
}

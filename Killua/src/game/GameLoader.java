package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Zohaib Ahmed, Zohaib.Ahmed@city.ac.uk
 */

public class GameLoader {
    /**
     * Gets the file name which will be loaded.
     */
    private String fileName;
    private Game game;


    /** Gets all the information needed and loads it.
     *
     * @param fileName Gets the file name as a parameter which would have the game be loaded.
     * @param game Takes game as a parameter to update player states.
     */
    public GameLoader(String fileName, Game game){
        this.fileName = fileName;
        this.game = game;
    }

    /** Loads the level.
     * <p>
     * Loads the level and updates the user states so that the user can pick up from where they had left off.
     *
     * @return The game level and updates it to what the user had saved.
     * @throws IOException Throws an IO Exception when there is a failure reading or writing to a file.
     */
    public GameLevel loadGame() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try{
            // Reads the filename
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            // Reads line to line
            String line = reader.readLine();
            int levelNumber = Integer.parseInt(line);

            // Sets the level
            GameLevel level = new LevelLoader(levelNumber, game);
            game.setLevel(levelNumber);

            // Loops and creates players, platforms, coins and enemies
            while ((line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                String className = tokens[0];
                float x = Float.parseFloat(tokens[1]);
                float y = Float.parseFloat(tokens[2]);
                Vec2 pos = new Vec2(x,y);
                if (className.equals("Killua")){
                    int count = Integer.parseInt(tokens[3]);
                    Killua b = new Killua(level);
                    b.setPosition(pos);
                    b.setMoneyCount(count);
                    level.setPlayer(b);
                }
                if (className.equals("Door")){
                    Body b = new Door(level, game);
                    b.setPosition(pos);
                }
                if (className.equals("Money")){
                    Body b = new Money(level);
                    b.addCollisionListener(new Grab(level.getPlayer()));
                    b.setPosition(pos);
                }
                if (className.equals("Platform")){
                    float w = Float.parseFloat(tokens[3]);
                    float h = Float.parseFloat(tokens[4]);
                    Body b = new Platform(level, w, h);
                    b.setPosition(pos);
                }
                if (className.equals("Hisoka")){
                    Hisoka b = new Hisoka(level);
                    b.setPosition(pos);
                    level.setHisoka(b);
                    level.addStepListener(new AutoController(b, new Vec2(5,0)));
                    b.addCollisionListener(new Kill(game.getPlayer(), game));
                }
                if (className.equals("Youpi")){
                    Youpi b = new Youpi(level);
                    b.setPosition(pos);
                    level.setYoupi(b);
                    level.addStepListener(new FlyController(b,new Vec2(0,6)));
                    b.addCollisionListener(new Kill(game.getPlayer(), game));
                }
            }
            // Sets the gravity
            level.setGravity(19);
            return level;
        }
        finally{
            if (reader != null){
                reader.close();
            }
            if (fr != null){
                fr.close();
            }
        }
    }
}
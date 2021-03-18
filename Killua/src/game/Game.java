package game;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import city.cs.engine.SoundClip;
import org.jbox2d.common.Vec2;


/**
 * @author      Zohaib Ahmed, Zohaib.Ahmed@city.ac.uk
 * A world with some bodies.
 */
public class Game {

    /**
     * The World in which the bodies move and interact.
     */
    private GameLevel world;
    /**
     * A graphical display of the world (a specialised JPanel).
     */
    private MyView view;
    /**
     * Current level number
     */
    public int level;
    /**
     * Allows the user to control the player
     */
    private Controller controller;
    /**
     * Draws the frame which displays the game and buttons
     */
    private JFrame frame;
    /**
     * Allows the user to control the user with the mouse controls
     */
    public MouseHandler mouseHandler;
    /**
     * Sets the music of the game and controls the sounds
     */
    private SoundClip gameMusic;
    /**
     * Creates the buttons
     */
    private ControlPanel buttons;
    private static Double X = 1.0;

    /**
     * Initialise a new Game.
     * <p>
     * Initialises the sound for the first two levels, creates the view for the game so that the game can be displayed,
     * and also creates the buttons.
     *
     */

    public Game() {
        // make the world
        level = 1;
        world = new Level1();
        world.populate(this);
//        try {
//            gameMusic = new SoundClip("data/Soundtrack2.wav");   // Open an audio input stream
//            gameMusic.loop();  // Set it to continuous playback (looping)
//        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
//            System.out.println(e);
//        }
        // make a view
        view = new MyView(world, this,500, 500);

        // uncomment this to draw a 1-metre grid over the view
        view.setGridResolution(1);

        // display the view in a frame
        frame = new JFrame("Hiatus x Hiatus");

        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // add buttons to the bottom of the frame
        buttons = new ControlPanel(frame, this, world);
        frame.add(buttons.getMainPanel(), BorderLayout.SOUTH);

        // display the world in the window
        frame.add(view, BorderLayout.NORTH);
        // don't let the game window be resized
        frame.setResizable(false);
        // size the game window to fit the world view
        frame.pack();
        // make the window visible
        frame.setVisible(true);
        // get keyboard focus
        frame.requestFocus();
        // give keyboard focus to the frame whenever the mouse enters the view
        view.addMouseListener(new GiveFocus(frame));

        // create Mouse and keyboard controls
        controller = new Controller(world.getPlayer(),this, world);
        mouseHandler = new MouseHandler(view);
        frame.addKeyListener(controller);
        //Follow the player
        world.addStepListener(new Tracker(view, world.getPlayer()));


        // start!
        world.start();
    }

    /**
     * The player in the current level.
     * <p>
     * Gets the player and the players information such as coins, lives, nen, positions etc.
     *
     * @return The method returns the player variable.
     */
    public Killua getPlayer() {
        return world.getPlayer();
    }

    /**
     * The method pauses the world
     */
    public void pause(){
        world.stop();
    }

    /**
     * The method continues the world.
     */
    public void resume(){
        world.start();
    }

    /**
     * Sets the volume higher
     * <p>
     * Increments the volume multiplier and then sets the gameMusic variable to X.
     */
    public void volUp(){
        if (X < 2.000000000000001) {
            X = X + 0.1;
            System.out.println(X);
            gameMusic.setVolume(X);
        }
        else{
            System.out.println("Max Volume");
        }
    }
    /**
     * Sets the volume lower
     * <p>
     * Decrements the volume multiplier and then sets the gameMusic variable to X.
     */
    public void volDown(){
        if (X > 0.10000000000000014) {
            X = X - 0.1;
            System.out.println(X);
            gameMusic.setVolume(X);
        }
        else{
            System.out.println("Min Volume");
        }
    }
    /**
     * Increments the time when the user skips a level
     * <p>
     * When skipping levels, time is incremented by different values each level
     *
     * @return Returns the new time with the incremented amount.
     */
    public int skipInc(){
        if (level == 1) {
            view.time = view.time + 20;
        }
        else if (level == 2){
            view.time = view.time + 40;
        }
        else if (level == 3){
            view.time = view.time + 60;
        }
        else if (level == 4){
            view.time = view.time + 80;
        }
        return view.time;
    }

    /** Checks whether the current level is completed.
     * <p>
     * Returns true or false as to whether the user has completed the level.
     *
     * @return Returns true or false as to if the world is completed or not.
     */
    public boolean isCurrentLevelCompleted() {
        return world.isCompleted();
    }

    /**Sets the level*
     *
     * @param x Sets the level to the parameter taken in.
     */
    public void setLevel(int x){level = x;}

    /**
     * Increments the time when the user skips a level
     * <p>
     * When skipping levels, time is incremented by different values each level
     *
     * @param gameLevel Gets the level of the game and the state of it.
     */
    public void goToLevel(GameLevel gameLevel){
        world.getPlayer().destroy();
        world.stop();
        world = gameLevel;
        controller.setBody(world.getPlayer());
        controller.setWorld(world);
        view.setWorld(world);
        world.addStepListener(new Tracker(view, world.getPlayer()));
        if (level == 3){
            view.removeMouseListener(mouseHandler);
            mouseHandler.killua= world.getPlayer();
            mouseHandler.hisoka = world.getHisoka();
            view.addMouseListener(mouseHandler);
        }
        else if (level == 4){
            view.removeMouseListener(mouseHandler);
            MouseHandler mouseHandler = new MouseHandler(view);
            mouseHandler.killua= world.getPlayer();
            mouseHandler.hisoka = world.getHisoka();
            mouseHandler.youpi = world.getYoupi();
            view.addMouseListener(mouseHandler);
        }
        world.start();
    }

    /**
     * Advance to the next level of the game.
     * <p>
     * Advances to the next level of the game. For levels 3 and 4, the soundtrack changes for each of them and also
     * enemies are added for those levels meaning that stepListeners must be added for those levels as well as
     * mouseListeners. For when level 4 is completed, the high scores are drawn and
     * @throws IOException Throws an IO Exception when there is a failure reading or writing to a file.
     */
    public void goNextLevel() throws IOException {
        if (level == 4) {
            HighScoreWriter hsWriter = new HighScoreWriter("data/quickest.txt");
            for (int i = 0; i < 2; i += 2) {
                String name = buttons.name;
                int score = Integer.parseInt(String.valueOf(1000-view.time));
                hsWriter.writeHighScore(name, score);
            }
            level++;
            BufferedReader reader = new BufferedReader(new FileReader("data/quickest.txt"));
            //Creating ArrayList to hold Student objects
            ArrayList<TopScores> leaderboard = new ArrayList<TopScores>();
            //Reading score records one by one
            String currentLine = reader.readLine();
            while (currentLine != null){
                String[] topscores = currentLine.split(",");
                String name = topscores[0];
                int score = Integer.parseInt(topscores[1]);
                //Creating player object for every score record and adding it to ArrayList
                leaderboard.add(new TopScores(name, score));
                currentLine = reader.readLine();
            }
            //Sorting ArrayList leaderboard based on marks
            leaderboard.sort(new scoreCompare());
            //Creating BufferedWriter object to write into output text file
            BufferedWriter writer = new BufferedWriter(new FileWriter("data/quickest.txt"));
            //Writing every score into output text file
            for (TopScores player : leaderboard)
            {
                writer.write(player.name);
                writer.write(","+player.score);
                writer.newLine();
            }
            //Closing the resources
            reader.close();
            writer.close();
            HighScoreReader demo = new HighScoreReader("data/quickest.txt");
            demo.readScores();
        }
        else if (level == 1){
            world.stop();
            level++;
            // get a new world
            world = new Level2();
            // fill it with bodies
            world.populate(this);
            view.setWorld(world);
            frame.add(view, BorderLayout.NORTH);
            view.addMouseListener(new GiveFocus(frame));
            // switch the keyboard control to the new player
            controller.setBody(world.getPlayer());
            controller.setWorld(world);
            world.addStepListener(new Tracker(view, getPlayer()));
            // show the new world in the view
            world.start();
        }
        else if (level == 2){
            world.stop();
            // Pauses the old song clip and plays the new one
//            gameMusic.pause();
//            try {
//                gameMusic = new SoundClip("data/Soundtrack1.wav");   // Open an audio input stream
//                gameMusic.loop();  // Set it to continuous playback (looping)
//            } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
//                System.out.println(e);
//            }
            view.removeMouseListener(mouseHandler);
            level++;
            // get a new world
            world = new Level3();
            // fill it with bodies
            world.populate(this);
            view.setWorld(world);
            frame.add(view);
            view.addMouseListener(new GiveFocus(frame));
            // switch the keyboard control to the new player
            controller.setBody(world.getPlayer());
            controller.setWorld(world);
            mouseHandler.killua= world.getPlayer();
            mouseHandler.hisoka = world.getHisoka();
            view.addMouseListener(mouseHandler);
            world.addStepListener(new AutoController(world.getHisoka(), new Vec2(5,0)));
            world.addStepListener(new Tracker(view, world.getPlayer()));
            // show the new world in the view
            world.start();
        }
        else if (level == 3) {
            world.stop();
//            gameMusic.close();
//            try {
//                gameMusic = new SoundClip("data/Soundtrack3.wav");   // Open an audio input stream
//                gameMusic.loop();  // Set it to continuous playback (looping)
//            } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
//                System.out.println(e);
//            }
            //Remove the mouse listener from the previous level so that there is a single new one
            view.removeMouseListener(mouseHandler);
            level++;
            // get a new world
            world = new Level4();
            // fill it with bodies
            world.populate(this);
            view.setWorld(world);
            frame.add(view);
            view.addMouseListener(new GiveFocus(frame));
            // switch the keyboard control to the new player
            controller.setBody(world.getPlayer());
            controller.setWorld(world);
            MouseHandler mouseHandler = new MouseHandler(view);
            mouseHandler.killua= world.getPlayer();
            mouseHandler.hisoka = world.getHisoka();
            mouseHandler.youpi = world.getYoupi();
            view.addMouseListener(mouseHandler);
            world.addStepListener(new AutoController(world.getHisoka(), new Vec2(5,0)));
            world.addStepListener(new FlyController(world.getYoupi(),new Vec2(0,6)));
            world.addStepListener(new Tracker(view, world.getPlayer()));
            // show the new world in the view
            world.start();
        }
    }

    /** Run the game.
     *
     * @param args Runs the program.
     */
    public static void main(String[] args) {
        new Game();
    }
}

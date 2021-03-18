package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * @author Zohaib Ahmed, Zohaib.Ahmed@city.ac.uk
 * Key handler to control an Walker.
 */
public class Controller extends KeyAdapter {
    /**
     * Sets the jumping speed of the player.
     */
    private static final float JUMPING_SPEED = 15;
    /**
     * Walking speed of the player
     */
    private static final float WALKING_SPEED = 5;
    private Walker body;
    private Game game;
    private Boolean direction = true;
    private GameLevel currentLevel;

    /**
     * Initialises the controller for the player
     * @param body Gets the player's walker so that it can be controlled.
     * @param game Gets the game the player is in so that all the events can occur within it.
     * @param level Gets the current level so that the player can save and load.
     */
    public Controller(Walker body, Game game, GameLevel level) {
        this.body = body;
        this.game = game;
        this.currentLevel = level;
    }

    /**
     * Handle key press events for walking and jumping.
     * @param e description of the key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_P) { // Q = quit
            System.exit(0);
        } else if (code == KeyEvent.VK_W) { // W = jump
            Vec2 v = body.getLinearVelocity();
            // only jump if body is not already jumping
            if (Math.abs(v.y) < 0.01f) {
                if (v.x>0) {
                    BodyImage bodyImageA = new BodyImage("data/KilluaJump.png", 2.5f);
                    body.removeAllImages();
                    body.addImage(bodyImageA);
                }
                else if(v.x <= 0) {
                    BodyImage bodyImageA = new BodyImage("data/KilluaJumpLeft.png", 2.5f);
                    body.removeAllImages();
                    body.addImage(bodyImageA);
                }
                body.jump(JUMPING_SPEED);
            }
        } else if (code == KeyEvent.VK_A) {
            BodyImage bodyImage_L = new BodyImage("data/KilluaRunL.png",2.5f);
            body.removeAllImages();
            body.addImage(bodyImage_L);
            body.startWalking(-WALKING_SPEED);
            direction = false; // A = walk left
        } else if (code == KeyEvent.VK_D) {
            BodyImage bodyImage_R = new BodyImage("data/KilluaRunR.png",2.5f);
            body.removeAllImages();
            body.addImage(bodyImage_R);
            body.startWalking(WALKING_SPEED);
            direction = true; // D = walk right
        } else if (code == KeyEvent.VK_S){
            /** To go down a platform */
            if (body.getPosition().y <= -9){
                System.out.println("You can't go more down!");
            }
            else {
                body.setPosition(new Vec2(body.getPosition().x, body.getPosition().y - 2));
            }
        } else if (code == KeyEvent.VK_M){
            /** Saves the current state of the level */
            GameSaver save = new GameSaver("data/scores.txt");
            try{
                save.saveGame(currentLevel);
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
        } else if (code == KeyEvent.VK_L){
            // Loads the saved state of the level
            GameLoader gl = new GameLoader("data/scores.txt", game);
            try{
                GameLevel loadedGame = gl.loadGame();
                game.goToLevel(loadedGame);
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    /**
     * Handle key release events (stop walking).
     * @param e description of the key event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        /** Change the images to more natural images depending on the situation and key pressed */
        if (code == KeyEvent.VK_A) {
            BodyImage bodyImage_L = new BodyImage("data/KilluaLeft.png",2.5f);
            body.removeAllImages();
            body.addImage(bodyImage_L);
            body.stopWalking();
        } else if (code == KeyEvent.VK_D) {
            BodyImage bodyImage_R = new BodyImage("data/KilluaRight.png",2.5f);
            body.removeAllImages();
            body.addImage(bodyImage_R);
            body.stopWalking();
        } else if (code == KeyEvent.VK_W){
            if (body.getLinearVelocity().x > 0) {
                BodyImage bodyImage_R = new BodyImage("data/KilluaRunR.png", 2.5f);
                body.removeAllImages();
                body.addImage(bodyImage_R);
            }
            else if (body.getLinearVelocity().x < 0){
                BodyImage bodyImage_L = new BodyImage("data/KilluaRunL.png",2.5f);
                body.removeAllImages();
                body.addImage(bodyImage_L);
            }
            else{
                BodyImage bodyImage = new BodyImage("data/KilluaRight.png", 2.5f);
                body.removeAllImages();
                body.addImage(bodyImage);
            }
        }
    }

    /**
     * Define the body being used.
     * @param body The players body which will be controlled.
     */
    public void setBody(Walker body) {
        this.body = body;
    }

    /**
     * Define the world in which the player will be playing in.
     * @param world The world in which the player will be playing in.
     */
    public void setWorld(GameLevel world){
        this.currentLevel = world;
    }
}


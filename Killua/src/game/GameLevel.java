package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;

/**
 * A level of the game.
 */
public abstract class GameLevel extends World{

    private Killua player;
    private Hisoka hisoka;
    private Youpi youpi;

    // Create a getter and setter for the player and enemies
    public void setPlayer(Killua k){
        player = k;
    }

    public Killua getPlayer() {
        return player;
    }

    public void setHisoka(Hisoka h){
        this.hisoka = h;
    }

    public Hisoka getHisoka() { return hisoka; }

    public void setYoupi(Youpi youpi) {
        this.youpi = youpi;
    }

    public Youpi getYoupi() {
        return youpi;
    }

    /**
     * Populate the world of this level.
     * Child classes should this method with additional bodies.
     */
    public void populate(Game game) {
        setGravity(19);
        player = new Killua(this);
        player.setPosition(startPosition());
        Door door = new Door(this, game);
        door.setPosition(doorPosition());
        door.addCollisionListener(new LevelDone(game));

    }

    /** The initial position of the player. */
    public abstract Vec2 startPosition();

    /** The position of the exit door. */
    public abstract Vec2 doorPosition();


    /** Is this level complete? */
    public abstract boolean isCompleted();

    /** Get the level number */
    public abstract int getLevelNumber();
}

package game;

import org.jbox2d.common.Vec2;

import javax.swing.*;

public class LevelLoader extends GameLevel {

    private int level;

    // Loads the level when the game is loaded from a save file
    public LevelLoader(int level, Game game){
       this.level = level;
    }

    @Override
    public Vec2 startPosition() {
        return null;
    }

    @Override
    public Vec2 doorPosition() {
        return null;
    }

    @Override
    public boolean isCompleted() {
        return true;
    }

    @Override
    public int getLevelNumber() {
        return level;
    }
}

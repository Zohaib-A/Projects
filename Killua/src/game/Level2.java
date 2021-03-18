package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;

/**
 * Level 2 of the game
 */
public class Level2 extends GameLevel {

    private static final int coins = 19;

    /**
     * Populate the world.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);

        // make the ground
        Platform groundShape = new Platform(this,30, 0.5f);
        groundShape.setPosition(new Vec2(19, -11.5f));

        // walls
        Platform leftWallShape = new Platform(this,0.5f, 6);
        leftWallShape.setPosition(new Vec2(-11.5f, -6f));
        Platform rightWallShape = new Platform(this,0.5f, 6);
        rightWallShape.setPosition(new Vec2(49.5f, -6f));

        Platform platform1 = new Platform(this, 4, 0.5f);
        platform1.setPosition(new Vec2(-5, -6));

        Platform platform2 = new Platform(this, 4, 0.5f);
        platform2.setPosition(new Vec2(4, -1));

        Platform platform3 = new Platform(this, 4, 0.5f);
        platform3.setPosition(new Vec2(13, 4));

        Platform platform3half = new Platform(this, 4, 0.5f);
        platform3half.setPosition(new Vec2(20, 0));

        Platform platform4 = new Platform(this, 4, 0.5f);
        platform4.setPosition(new Vec2(27, 4));

        Platform platform5 = new Platform(this, 4, 0.5f);
        platform5.setPosition(new Vec2(36, 9));


        for (int i = 0; i < coins; i++) {
            Body cash = new Money(this);
            cash.setPosition(new Vec2(i *2.5f - 7.5f, 10));
            cash.addCollisionListener(new Grab(getPlayer()));
        }
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(10, -11);
    }

    @Override
    public Vec2 doorPosition() {
        return new Vec2(40, 11.5f);
    }

    @Override
    public boolean isCompleted() {
        return true;
    }


    @Override
    public int getLevelNumber() {
        return 2;
    }
}

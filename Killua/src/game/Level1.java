package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Level 2 of the game
 */
public class Level1 extends GameLevel{

    private static final int coins = 8;

    /**
     * Populate the world.
     */
    @Override
    public void populate(Game game) {
        super.populate(game);

        // make the ground
        Platform groundShape = new Platform(this,11, 0.5f);
        groundShape.setPosition(new Vec2(0, -11.5f));

        // walls
        Platform leftWallShape = new Platform(this,0.5f, 6);
        leftWallShape.setPosition(new Vec2(-11.5f, -6f));
        Platform rightWallShape = new Platform(this,0.5f, 6);
        rightWallShape.setPosition(new Vec2(11.5f, -6f));

        Platform platform1 = new Platform(this,4, 0.5f);
        platform1.setPosition(new Vec2(0, -6.75f));

        for (int i = 0; i < coins; i++) {
            Body cash = new Money(this);
            cash.setPosition(new Vec2(i *2.5f - 7.5f, -6));
            cash.addCollisionListener(new Grab(getPlayer()));
        }
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(0, -6.5f);
    }

    @Override
    public Vec2 doorPosition() {
        return new Vec2(-11f, -8f);
    }

    @Override
    public boolean isCompleted() {
        return true;
    }

    @Override
    public int getLevelNumber() {
        return 1;
    }

}

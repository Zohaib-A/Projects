package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.*;

/**
 * Doors in a game. When the actor collides with a door, if
 * the current level is complete the game is advanced to the
 * next level.
 */
public class Door extends StaticBody {

    /**
     * Initialise a new door.
     * @param world The world.
     */
    public Door(World world, Game game) {
        // Creates a door body and fills its colour
        super(world, new BoxShape(0.1f, 3f));
        setFillColor(new Color(101, 67, 33));
        // Adds a collision listener so that the level can be completed
        addCollisionListener(new LevelDone(game));
    }

}

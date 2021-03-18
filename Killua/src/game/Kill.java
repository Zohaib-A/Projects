package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Collision listener that allows Killua to be killed.
 */
public class Kill implements CollisionListener {
    private Killua killua;
    Game game;

    public Kill(Killua killua, Game game) {
        this.killua = killua;
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent e) {
        // Sets the player position for when the player is killed and decreases their lives
        if (e.getOtherBody() == killua) {
            killua.decreaseLives();
            if (game.level == 3) {
                killua.setPosition(new Vec2(0,-9));
            }
            else if (game.level == 4){
                killua.setPosition(new Vec2(-30,-9));
            }
        }
    }
}




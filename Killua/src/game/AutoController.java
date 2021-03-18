package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;


/**
 * Key handler to control a Walker.
 */
public class AutoController implements StepListener {

    /** The velocity for the enemy to auto move*/
    private static final Vec2 vel = new Vec2(3,0);
    private static final Vec2 vel2 = new Vec2(-3,0);

    private DynamicBody body;
    private Vec2 pos;

    public AutoController(Walker body, Vec2 pos) {
        this.body = body;
        this.pos = pos;
    }

    @Override
    public void preStep(StepEvent e) {
    }

    @Override
    public void postStep(StepEvent e) {
        // If statements to get the enemy moving in the correct position
        if (body.getPosition().x <= (pos.x-2) && body.getLinearVelocity().x <= 0){
            body.setLinearVelocity(vel);
        }
        else if (body.getPosition().x >= (pos.x+2) && body.getLinearVelocity().x >= 0){
            body.setLinearVelocity(vel2);
        }
        else if(body.getLinearVelocity().x <= 0){
            body.setLinearVelocity(vel2);
        }
        else{
            body.setLinearVelocity(vel);
        }
    }
}


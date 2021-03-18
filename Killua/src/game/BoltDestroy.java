package game;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class BoltDestroy implements CollisionListener {
    private Killua killua;

    public BoltDestroy(Killua killua){
        this.killua=killua;
    }
    @Override
    public void collide(CollisionEvent collisionEvent) {
        /** Destroy the bolt upon collision unless it touches the player */
        if (collisionEvent.getOtherBody() != killua) {
            collisionEvent.getReportingBody().destroy();
        }
    }
}

package game;
import city.cs.engine.*;

public class BoltKill implements CollisionListener{
    private Walker body;
    private Killua killua;

    public BoltKill(Walker body, Killua killua){
        this.body = body;
        this.killua = killua;
    }
    @Override
    public void collide(CollisionEvent e){
        /** If the other body it collides with is the enemy, it will destroy the enemy */
        if(e.getOtherBody() == body){
            body.destroy();
            killua.incrementMoney();
        }
    }
}

package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {
    private Walker body;
    public Killua killua;
    public Hisoka hisoka;
    public Youpi youpi;
    public int i = 8;

    private WorldView view;


    public MouseHandler(WorldView view) {
        this.view = view;
    }


    public void mousePressed(MouseEvent e) {
        if (i > 0) {
            i--;
            // Creates the thunderbolt and adds the image to it
            Shape thunderShape = new PolygonShape(-1.17f, 1.34f, -0.97f, 0.73f, 0.36f, -0.88f, 1.07f, -1.33f, 0.31f, -0.2f);
            DynamicBody bolt = new DynamicBody(view.getWorld(), thunderShape);
            bolt.addImage(new BodyImage("data/thunderbolt.png", 3f));
            // Sets the bolt position to start from the players position and go in the direction of the mouse pointer
            bolt.setPosition(new Vec2(killua.getPosition().x + 0, killua.getPosition().y + 2));
            bolt.setLinearVelocity(new Vec2((e.getX() - 250) / 10, -(e.getY() - 250) / 10));
            // Changes the image depending on where the mouse is
            if ((e.getX() - 250) >= 0) {
                BodyImage bodyImageA = new BodyImage("data/KilluaAttack.png", 3f);
                killua.removeAllImages();
                killua.addImage(bodyImageA);
            } else {
                BodyImage bodyImageA = new BodyImage("data/KilluaAttackLeft.png", 3f);
                killua.removeAllImages();
                killua.addImage(bodyImageA);
            }
            // Decrements the energy, adds a collision listener which kills the enemies and destroys the bolts
            killua.maxNen();
            bolt.addCollisionListener(new BoltKill(hisoka, killua));
            bolt.addCollisionListener(new BoltKill(youpi, killua));
            bolt.addCollisionListener(new BoltDestroy(killua));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if ((e.getX()-250)>=0) {
            BodyImage bodyImage_R = new BodyImage("data/KilluaRight.png",2.5f);
            killua.removeAllImages();
            killua.addImage(bodyImage_R);
        }
        else{
            BodyImage bodyImage_L = new BodyImage("data/KilluaLeft.png",2.5f);
            killua.removeAllImages();
            killua.addImage(bodyImage_L);
        }
    }
}

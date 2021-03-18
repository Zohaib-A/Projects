package game;

import city.cs.engine.*;

public class Hisoka extends Walker {
    //hitbox for walker
    private static final Shape hisokaShape = new PolygonShape(-0.66f,0.46f, -0.06f,1.49f, 0.36f,1.27f, 0.54f,-0.65f, 0.46f,-1.47f, -0.48f,-1.47f);
    //image for walker
    private static final BodyImage image =
            new BodyImage("data/hisoka.png", 3f);

    public Hisoka(World world) {
        super(world, hisokaShape);
        addImage(image);
    }
}



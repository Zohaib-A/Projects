package game;

import city.cs.engine.*;

public class Youpi extends Walker {
    //hitbox for walker
    private static final Shape youpiShape = new PolygonShape(-1.27f,-1.39f, -1.43f,-0.98f, -1.41f,0.82f, -1.21f,1.35f, -0.04f,1.48f, 0.68f,0.91f, 1.41f,-0.91f, 0.77f,-1.5f);
    //image for walker
    private static final BodyImage image =
            new BodyImage("data/Youpi.png", 3f);

    public Youpi(World world) {
        super(world, youpiShape);
        addImage(image);
    }
}



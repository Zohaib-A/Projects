package game;
import city.cs.engine.*;
import city.cs.engine.Shape;

import java.awt.*;

public class Platform extends StaticBody{
    private float width;
    private float height;
    public Platform(World w, float width, float height){
        // Creates the base for the platform allowing the height and width to be changed
        super(w);
        Shape s = new BoxShape(width, height);
        Fixture f = new SolidFixture(this, s);
        this.width = width;
        this.height = height;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }
}

package game;

import city.cs.engine.*;

import java.lang.*;


public class Killua extends Walker {
    //hitbox for walker

    public static Shape killuaShape = new PolygonShape(
            -0.493f,0.0f, -0.463f,-1.235f, 0.472f,-1.23f, 0.507f,0.815f, 0.077f,1.2f, -0.423f,1.08f
    );
    //image for walker
    private static final BodyImage image =
            new BodyImage("data/KilluaRight.png", 2.5f);

    private int moneyCount;
    private int lives;
    private int nen;

    public Killua(World world){
        super(world, killuaShape);
        addImage(image);
        moneyCount = 0;
        lives = 3;
        nen = 8;
    }

    public int getMoneyCount() {
        return moneyCount;
    }

    public int getLives(){
        return lives;
    }

    public int getNen(){
        return nen;
    }

    public void incrementMoney() {
        moneyCount++;
    }

    public void decreaseLives(){
        lives = lives - 1;
    }

    public void maxNen(){
        nen--;
    }

    public void setMoneyCount(int cash){
        moneyCount = cash;
    }
}


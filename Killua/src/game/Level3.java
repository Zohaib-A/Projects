package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.Color;

/**
 * A world with some bodies.
 *
 */

public class Level3 extends GameLevel {
    private Hisoka hisoka;

    public void populate(Game game) {
        super.populate(game);

        // make the ground
        Platform groundShape = new Platform(this,25, 10f);
        groundShape.setFillColor(new Color(0, 170, 0));
        groundShape.setPosition(new Vec2(0, -20f));
        // walls
        Platform leftWallShape = new Platform(this,0.5f, 6);
        leftWallShape.setPosition(new Vec2(-24.5f, -6f));
        Platform rightWallShape = new Platform(this,0.5f, 6);
        rightWallShape.setPosition(new Vec2(24.5f, -6f));

        /*Shape bawks = new BoxShape(4, 9, new Vec2(5,5));
        GhostlyFixture nonce = new GhostlyFixture(ground, bawks);*/

        // make platforms
        BodyImage SPlatform = new BodyImage("data/SPlatform.png", 1f);
        BodyImage BPlatform = new BodyImage("data/BPlatform.png", 1f);
        Platform platform1 = new Platform(this, 2, 0.5f);
        platform1.addImage(SPlatform);
        platform1.setClipped(true);
        platform1.setPosition(new Vec2(-5, -6.75f));
        Platform platform2 = new Platform(this, 4, 0.5f);
        platform2.addImage(BPlatform);
        platform2.setClipped(true);
        platform2.setPosition(new Vec2(5, -2.5f));
        Platform platform3 = new Platform(this, 2, 0.5f);
        platform3.addImage(SPlatform);
        platform3.setClipped(true);
        platform3.setPosition(new Vec2(-4,2.5f));
        Platform platform4 = new Platform(this, 4, 0.5f);
        platform4.addImage(BPlatform);
        platform4.setClipped(true);
        platform4.setPosition((new Vec2(5, 6.5f)));
        Platform platform5 = new Platform(this, 2, 0.5f);
        platform5.addImage(SPlatform);
        platform5.setClipped(true);
        platform5.setPosition((new Vec2(-6, 10)));
        Platform platform6 = new Platform(this, 4, 0.5f);
        platform6.addImage(BPlatform);
        platform6.setClipped(true);
        platform6.setPosition((new Vec2(5, 14)));
        Platform platform7 = new Platform(this, 2, 0.5f);
        platform7.addImage(SPlatform);
        platform7.setClipped(true);
        platform7.setPosition((new Vec2(-3, 19.5f)));
        Platform platform8 = new Platform(this, 4, 0.5f);
        platform8.addImage(BPlatform);
        platform8.setClipped(true);
        platform8.setPosition((new Vec2(6, 25)));
        Platform platform9 = new Platform(this, 4, 0.5f);
        platform9.addImage(BPlatform);
        platform9.setClipped(true);
        platform9.setPosition((new Vec2(-6, 25)));

        hisoka = new Hisoka(this);
        hisoka.setPosition(new Vec2(5,8));
        hisoka.addCollisionListener(new Kill(getPlayer(),game));

        //insert collectible balls
        //platform8
        for (int i=0;i<3;i++){
            Body money = new Money(this);
            money.setPosition(new Vec2(i*3+3,26f));
            money.addCollisionListener(new Grab(getPlayer()));
        }
        //platform7
        for (int i=0;i<2;i++){
            Body money = new Money(this);
            money.setPosition(new Vec2(i*4-5,21f));
            money.addCollisionListener(new Grab(getPlayer()));
        }
        //platform6
        for (int i=0;i<2;i++){
            Body money = new Money(this);
            money.setPosition(new Vec2(i*8+1,15f));
            money.addCollisionListener(new Grab(getPlayer()));
        }
        //platform5
        for (int i=0;i<2;i++){
            Body money = new Money(this);
            money.setPosition(new Vec2(i*4-8,11f));
            money.addCollisionListener(new Grab(getPlayer()));
        }
        //platform4
        for (int i=0;i<2;i++){
            Body money = new Money(this);
            money.setPosition(new Vec2(i*8+1,9));
            money.addCollisionListener(new Grab(getPlayer()));
        }
        //platform3
        for (int i=0;i<2;i++){
            Body money = new Money(this);
            money.setPosition(new Vec2(i*4-6,4));
            money.addCollisionListener(new Grab(getPlayer()));
        }
        //platform2
        for (int i=0;i<2;i++){
            Body money = new Money(this);
            money.setPosition(new Vec2(i*8+1,-1.5f));
            money.addCollisionListener(new Grab(getPlayer()));
        }
        //platform1
        for (int i=0;i<2;i++){
            Body money = new Money(this);
            money.setPosition(new Vec2(i*4-7,-5.75f));
            money.addCollisionListener(new Grab(getPlayer()));
        }
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(0,-9);
    }

    @Override
    public Vec2 doorPosition() {
        return new Vec2(-9.9f, 28);
    }

    @Override
    public boolean isCompleted() {
        return true;
    }

    public Hisoka getHisoka() {
        return hisoka;
    }

    @Override
    public int getLevelNumber() {
        return 3;
    }
}

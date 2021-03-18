package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.Color;

/**
 * A world with some bodies.
 *
 */

public class Level4 extends GameLevel {
    private Hisoka hisoka;
    private Youpi youpi;

    public void populate(Game game) {
        super.populate(game);
        // make the ground
        Platform groundShape = new Platform(this,50, 10f);
        groundShape.setPosition(new Vec2(15, -20f));
        // walls
        Platform leftWall = new Platform(this, 0.5f, 6);
        leftWall.setPosition(new Vec2(-34.5f, -5));
        Platform rightWall = new Platform(this, 0.5f, 6);
        rightWall.setPosition(new Vec2(64.5f, -5));


        // make platforms
        BodyImage SPlatform = new BodyImage("data/SPlatform.png", 1f);
        BodyImage BPlatform = new BodyImage("data/BPlatform.png", 1f);
        Platform platform1 = new Platform(this, 2, 0.5f);
        platform1.addImage(SPlatform);
        platform1.setClipped(true);
        platform1.setPosition(new Vec2(-25, -5));
        Platform platform2 = new Platform(this, 2, 0.5f);
        platform2.addImage(SPlatform);
        platform2.setClipped(true);
        platform2.setPosition(new Vec2(-20, 0));
        Platform platform3 = new Platform(this, 2, 0.5f);
        platform3.addImage(SPlatform);
        platform3.setClipped(true);
        platform3.setPosition(new Vec2(-10, 0));
        Platform platform4 = new Platform(this, 4, 0.5f);
        platform4.addImage(BPlatform);
        platform4.setClipped(true);
        platform4.setPosition(new Vec2(-2, 5));
        Platform platform5 = new Platform(this, 4, 0.5f);
        platform5.addImage(BPlatform);
        platform5.setClipped(true);
        platform5.setPosition(new Vec2(5, 10));
        Platform platform6 = new Platform(this, 2, 0.5f);
        platform6.addImage(SPlatform);
        platform6.setClipped(true);
        platform6.setPosition(new Vec2(13, 15));
        Platform platform7 = new Platform(this, 4, 0.5f);
        platform7.addImage(BPlatform);
        platform7.setClipped(true);
        platform7.setPosition(new Vec2(5, 20));
        Platform platform8 = new Platform(this, 4, 0.5f);
        platform8.addImage(BPlatform);
        platform8.setClipped(true);
        platform8.setPosition(new Vec2(21, 10));
        Platform platform9 = new Platform(this, 4, 0.5f);
        platform9.addImage(BPlatform);
        platform9.setClipped(true);
        platform9.setPosition(new Vec2(21, 20));
        Platform platform10 = new Platform(this, 2, 0.5f);
        platform10.addImage(SPlatform);
        platform10.setClipped(true);
        platform10.setPosition(new Vec2(33, 10));
        Platform platform11 = new Platform(this, 4, 0.5f);
        platform11.addImage(BPlatform);
        platform11.setClipped(true);
        platform11.setPosition(new Vec2(42, 0));

        hisoka = new Hisoka(this);
        hisoka.setPosition(new Vec2(5,12));
        youpi = new Youpi(this);
        youpi.setPosition(new Vec2(-15, 0));
        hisoka.addCollisionListener(new Kill(getPlayer(),game));
        youpi.addCollisionListener(new Kill(getPlayer(),game));


        //Platform 1
        for (int i=0;i<2;i++){
            Body money = new Money(this);
            money.setPosition(new Vec2(i*4-27,0));
            money.addCollisionListener(new Grab(getPlayer()));
        }
        //Platform 2
        for (int i=0;i<2;i++){
            Body money = new Money(this);
            money.setPosition(new Vec2(i*4-22,5));
            money.addCollisionListener(new Grab(getPlayer()));
        }
        //Platform 3
        for (int i=0;i<2;i++){
            Body money = new Money(this);
            money.setPosition(new Vec2(i*4-12,5));
            money.addCollisionListener(new Grab(getPlayer()));
        }
        //Platform 4
        for (int i=0;i<2;i++){
            Body money = new Money(this);
            money.setPosition(new Vec2(i*8-6,8));
            money.addCollisionListener(new Grab(getPlayer()));
        }
        //Platform 5
        for (int i=0;i<2;i++){
            Body money = new Money(this);
            money.setPosition(new Vec2(i*8+1,15));
            money.addCollisionListener(new Grab(getPlayer()));
        }
        //Platform 6
        for (int i=0;i<2;i++){
            Body money = new Money(this);
            money.setPosition(new Vec2(i*4+11,18));
            money.addCollisionListener(new Grab(getPlayer()));
        }
        //Platform 7
        for (int i=0;i<2;i++){
            Body money = new Money(this);
            money.setPosition(new Vec2(i*8+1,22));
            money.addCollisionListener(new Grab(getPlayer()));
        }
        //Platform 8
        for (int i=0;i<2;i++){
            Body money = new Money(this);
            money.setPosition(new Vec2(i*8+17,15));
            money.addCollisionListener(new Grab(getPlayer()));
        }
        //Platform 9
        for (int i=0;i<2;i++){
            Body money = new Money(this);
            money.setPosition(new Vec2(i*8+17,22));
            money.addCollisionListener(new Grab(getPlayer()));
        }
        //Platform 10
        for (int i=0;i<2;i++){
            Body money = new Money(this);
            money.setPosition(new Vec2(i*4+31,12));
            money.addCollisionListener(new Grab(getPlayer()));
        }
        //Platform 11
        for (int i=0;i<2;i++){
            Body money = new Money(this);
            money.setPosition(new Vec2(i*4+38,5));
            money.addCollisionListener(new Grab(getPlayer()));
        }
    }

    @Override
    public Vec2 startPosition() {
        return new Vec2(-30,-9);
    }

    @Override
    public Vec2 doorPosition() {
        return new Vec2(45.5f, 3);
    }

    @Override
    public boolean isCompleted() {
        return true;
    }

    public Hisoka getHisoka() {
        return hisoka;
    }

    public Youpi getYoupi(){return youpi;}

    @Override
    public int getLevelNumber() {
        return 4;
    }
}

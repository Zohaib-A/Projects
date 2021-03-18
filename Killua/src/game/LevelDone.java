package game;

import city.cs.engine.*;

import java.io.IOException;

public class LevelDone implements CollisionListener {
    private Game game;

    public LevelDone(Game game) {
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent e) {
        // When the level is done then the game will go onto the next level
        Killua killua = game.getPlayer();
        if (e.getOtherBody() == killua && game.isCurrentLevelCompleted()) {
            System.out.println("Going to next level...");
            try {
                game.goNextLevel();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import city.cs.engine.*;

/**
 * extended view
 */
public class MyView extends UserView implements ActionListener {
    private Game game;
    public int time;

    public MyView(World world, Game game, int width, int height) {
        super(world, width, height);
        this.game = game;
        // Create a timer with a second increment
        Timer timer = new Timer(1000, this);
        timer.setInitialDelay(100);
        timer.start();
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        // Change background based on level
        Image background = null;
        if (game.level == 1){
            background = new ImageIcon("data/background2.jpg").getImage();
        }
        else if (game.level == 2){
            background = new ImageIcon("data/background1.jpg").getImage();
        }
        else if (game.level == 3){
            background = new ImageIcon("data/background3.jpg").getImage();
        }
        else if (game.level == 4){
            background = new ImageIcon("data/background4.jpg").getImage();
        }
        // Draw the background
        g.drawImage(background, 0, -500, this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        if (game.level == 4){
            g.setColor(new Color(0, 176, 240));
        }
        else {
            g.setColor(new Color(0, 176, 240, 102));
        }
        g.fillRect(0,0,180,80);
        g.fillRect(180, 0, 130, 30);
        // Draw the strings for user information
        g.setColor(Color.black);
        Image money = new ImageIcon("data/Coin.png").getImage();
        g.drawImage(money,10,5, 20, 20, null, null);
        g.setFont(new Font("Futura", Font.PLAIN, 14));
        g.drawString("count: " + game.getPlayer().getMoneyCount(), 35, 20);
        g.drawString("Lives remaining: ", 10,40);
        g.drawString("Nen: " + game.getPlayer().getNen(),10,60);
        g.drawString("Time: " + time, 225, 20);
        Image health1 = new ImageIcon("data/health1.png").getImage();
        Image health2 = new ImageIcon("data/health2.png").getImage();
        Image health3 = new ImageIcon("data/health3.png").getImage();
        // Change heart images
        if (game.getPlayer().getLives() == 3){
            g.drawImage(health3, 115, 30, 45, 15, null, null);
        }
        else if (game.getPlayer().getLives() == 2){
            g.drawImage(health2, 115, 30, 30, 15, null, null);
        }
        else if (game.getPlayer().getLives() == 1){
            g.drawImage(health1, 115, 30, 15, 15, null, null);
        }
        // Game over when player reaches a certain number of lives
        if (game.getPlayer().getLives() == -1){
            g.setColor(Color.black);
            g.fillRect(0,0,500,500);
            g.setColor(Color.RED);
            g.setFont(new Font("Courier", Font.BOLD,60));
            g.drawString("Game over", 100,250);
            g.setFont(new Font("Courier", Font.BOLD,30));
            g.drawString("Ran out of lives", 125, 300);
            game.getPlayer().destroy();
        }
        // Draw the leaderboard
        if (game.level == 5){
            g.setColor(Color.black);
            g.fillRect(0,0,1000,1000);
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Futura", Font.BOLD,30));
            g.drawString("Quickest Times", 120,40);
            g.setFont(new Font("Futura", Font.BOLD,20));
            g.drawString("Name", 160, 90);
            g.drawString("Score", 260, 90);
            for(int i=0; i< 10; i++){
                g.drawString("" + (HighScoreReader.quickestTimes[i][0]), 160, 120+i*30);
                g.drawString("" + (HighScoreReader.quickestTimes[i][1]), 260, 120+i*30);
            }
            game.getPlayer().destroy();
        }
    }


    // Increments the timer
    @Override
    public void actionPerformed(ActionEvent e) {
        time++;
    }
}

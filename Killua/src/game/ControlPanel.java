package game;

import city.cs.engine.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class ControlPanel {
    private JPanel mainPanel;
    private JButton quitButton;
    private JButton restartButton;
    private JButton pauseButton;
    private JButton resumeButton;
    private JButton restartLevelButton;
    private JTextField userNameInput;
    private JButton saveButton;
    private JButton loadButton;
    private JButton volumeUButton;
    private JButton volumeDButton;
    private GameLevel currentLevel;
    public String name = "Player";
    Game game;

    public ControlPanel(JFrame frame, Game game, GameLevel gameLevel) {
        this.currentLevel = gameLevel;
        this.game = game;
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Game();
            }
        });
        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.resume();
            }
        });
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.pause();
            }
        });
        restartLevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Skips the level
                game.skipInc();
                try {
                    game.goNextLevel();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        userNameInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = userNameInput.getText();
                System.out.println(name);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameSaver save = new GameSaver("data/scores.txt");
                try{
                    save.saveGame(currentLevel);
                }
                catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        });
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameLoader gl = new GameLoader("data/scores.txt", game);
                try{
                    GameLevel loadedGame = gl.loadGame();
                    game.goToLevel(loadedGame);
                }
                catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        });
        volumeUButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.volUp();
            }
        });
        volumeDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.volDown();
            }
        });
    }
    public JPanel getMainPanel(){
        return mainPanel;
    }
}

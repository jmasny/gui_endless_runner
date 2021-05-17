package com.janmasny;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame {

    Game game;

    public Window() {
        super();
        this.setTitle("Zombie endless runner");
        this.setSize(544, 320);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.initComponents();
        this.setVisible(true);

        (new Timer(0, (ActionEvent a) -> {
            this.game.repaint();
        })).start();
    }

    private void initComponents() {

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                        if(game.getGameState() == Game.GAME_START) {
                            game.setGameState(Game.GAME_PLAY);
                        } else if (game.getGameState() == Game.GAME_PLAY) {
                            if (game.getHero().getOnGround()) {
                                game.getHero().jump();
                            }
                        } else if (game.getGameState() == Game.GAME_END) {
                            game.resetGame();
                            game.setGameState(Game.GAME_PLAY);
                        }
                        break;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        game = new Game();
        this.add(game, BorderLayout.CENTER);;
    }

    public void startGame() {
        game.startGame();
    }

}

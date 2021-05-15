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
        this.setTitle("Endless runner");
        this.setSize(800, 375);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.initComponents();
        this.setVisible(true);

        // one bug, background not showing, only game class paint
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
                System.out.println("Key pressed");
                game.getHero().jump();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                        if(game.getGameState() == Game.GAME_START) {
                            game.setGameState(Game.GAME_PLAY);
                        } else if (game.getGameState() == Game.GAME_END) {
                            game.setGameState(Game.GAME_START);
                            game.resetGame(); //must be enhanced
                        }
                        break;
                }
                System.out.println("Key realeased");
            }
        });

        game = new Game();
        this.add(game, BorderLayout.CENTER);;
    }

    public void startGame() {
        game.startGame();
    }

    public void paint(Graphics g) {
        super.paint(g); //bez tego nie wyswietla mi klasy Game, rowniez wyswietla background ponownie
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("resources/obstacle1.png"));
            g.drawImage(image, 200, 200, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.fillOval(600,600,50,50);
    }
}

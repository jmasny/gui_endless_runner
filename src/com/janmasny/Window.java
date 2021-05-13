package com.janmasny;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame {

    Game game;

    public Window() {
        super();
        this.setTitle("Endless runner");
        this.setSize(1024, 768);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.initComponents();
        this.setVisible(true);
    }

    private void initComponents() {
        game = new Game();
        this.add(game, BorderLayout.CENTER);
    }

    public void startGame() {
        game.startGame();
    }

    public void paint(Graphics g) {
        super.paint(g); //bez tego nie wyswietla mi klasy Game
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

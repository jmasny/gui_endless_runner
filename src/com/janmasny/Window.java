package com.janmasny;

import javax.swing.*;
import java.awt.*;

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
}

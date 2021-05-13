package com.janmasny;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game extends JPanel implements Runnable {
    public static final float GRAVITY = 0.1f;
    private int x = 0;
    private int y = 0;
    private float speedY = 0;
    private Thread thread;

    public Game(){
        thread = new Thread(this);
    }

    public void jumpSpeedY() {
        this.speedY = -10;
    }

    public void startGame() {
        thread.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 100, 100);
    }

    @Override
    public void run() {
        while(true) {
            //System.out.println(i++);
            try {
                x+=1;
                speedY+=GRAVITY;
                y+=speedY;
                // repaint(); zrobione w ten sposob nie wyswietla tla z Window, pewnie dlatego, ze metoda paint z game caly czas to zaslania
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

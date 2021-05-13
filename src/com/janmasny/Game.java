package com.janmasny;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable {

    private int i = 0;
    private Thread thread;

    public Game(){
        thread = new Thread(this);
    }

    public void startGame() {
        thread.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(100, 100, 100, 100);
    }

    @Override
    public void run() {
        while(true) {
            System.out.println(i++);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

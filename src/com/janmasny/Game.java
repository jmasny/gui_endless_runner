package com.janmasny;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable {
    public static final float GRAVITY = 0.11f;
    public static final float FLOOR = 300;
    private int x = 0;
    private int y = (int) FLOOR;
    private float speedY = 0;
    private Thread thread;
    private Hero hero;
    private Ground ground;
    private Cloud cloud;
    private Zombie zombieOne;

    public Game(){
        this.thread = new Thread(this);
        this.hero = new Hero();
        this.hero.setX(100);
        this.ground = new Ground(this);
        this.cloud = new Cloud();
        this.zombieOne = new Zombie();
    }

    public void jumpSpeedY() {
        this.speedY += -3;
        this.y += (int) this.speedY;
    }

    public void startGame() {
        thread.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 100, 100);
        g.setColor(Color.GREEN);
        g.drawLine(0, (int) FLOOR, this.getWidth(), (int) FLOOR);
        zombieOne.draw(g);
        ground.draw(g);
        cloud.draw(g); //kolejnosc rysowania ma znaczenie dla warst
        hero.draw(g);
    }

    @Override
    public void run() {
        while(true) {
//            x+=1;
//            if ( y >= FLOOR-100) {
//                speedY = 0;
//                y = (int) FLOOR - 100; //rectangle width
//            } else {
//                speedY+=GRAVITY;
//                y+=speedY;
//            }
            try {
                hero.update();
                ground.update();
                cloud.update();
                zombieOne.update();
                if(zombieOne.getBounds().intersects(hero.getHeroBounds())) {
                    System.out.println("Collision detected");
                }
                // repaint(); zrobione w ten sposob nie wyswietla tla z Window, pewnie dlatego, ze metoda paint z game caly czas to zaslania
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Hero getHero() {
        return hero;
    }
}

package com.janmasny;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Game extends JPanel implements Runnable {
    public static final int GAME_START = 0;
    public static final int GAME_PLAY = 1;
    public static final int GAME_END = 2;
    public static final float GRAVITY = 0.31f;
    public static final float FLOOR = 300;
    private int x = 0;
    private int y = (int) FLOOR;
    private float speedY = 0;
    private Thread thread;
    private Hero hero;
    private Ground ground;
    private Cloud cloud;
    //private Zombie zombieOne;
    private Obstacles obstacles;
    private int gameState = GAME_START;
    private BufferedImage gameOverImage;
    private float score = 0;
    private float highestScore = 0;

    public Game(){
        this.thread = new Thread(this);
        this.hero = new Hero();
        this.hero.setX(100);
        this.ground = new Ground(this);
        this.cloud = new Cloud();
       // this.zombieOne = new Zombie();
        this.obstacles = new Obstacles(hero);
        this.gameOverImage = Resource.getResourceImage("resources/game_over.png");
    }

    public void jumpSpeedY() {
        this.speedY += -3;
        this.y += (int) this.speedY;
    }

    public void update() {
        switch(gameState) {
            case GAME_PLAY:
                hero.update();
                ground.update();
                cloud.update();
                obstacles.update();
                if(!hero.getAlive()) {
                    gameState = GAME_END;
                }
                this.score += 0.05;
                break;
        }
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void resetGame() { //must be enhanced
        hero.setX(0);
        hero.setY(0);
        hero.setAlive(true);
    }

    public void startGame() {
        thread.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 50, 50);
        g.setColor(Color.GREEN);
        g.drawLine(0, (int) FLOOR, this.getWidth(), (int) FLOOR);
        //zombieOne.draw(g);
        switch (gameState) {
            case GAME_START:
                hero.draw(g);
                break;
            case GAME_PLAY:
                obstacles.draw(g);
                ground.draw(g);
                cloud.draw(g); //kolejnosc rysowania ma znaczenie dla warst
                hero.draw(g);
                g.setColor(Color.BLACK);
                g.drawString("SCORE: " + (int) score, 700, 20);
                g.drawString("HIGHEST: " + (int) highestScore, 700, 40);
                break;
            case GAME_END:
                obstacles.draw(g);
                ground.draw(g);
                cloud.draw(g); //kolejnosc rysowania ma znaczenie dla warst
                hero.draw(g);
                g.drawImage(gameOverImage, ((this.getWidth()-gameOverImage.getWidth())/2),((this.getHeight()-gameOverImage.getHeight())/2),null);
                break;
        }

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
                this.update();
                //zombieOne.update();
                //if(zombieOne.getBounds().intersects(hero.getHeroBounds())) {
                 //   System.out.println("Collision detected");
                //}
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

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }
}

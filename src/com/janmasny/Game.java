package com.janmasny;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Game extends JPanel implements Runnable {

    public static final int GAME_START = 0;
    public static final int GAME_PLAY = 1;
    public static final int GAME_END = 2;
    public static final float GRAVITY = 0.31f;
    public static final float FLOOR = 295;
    private BufferedImage gameOverImage;
    private BufferedImage backgroundImage;
    private Clip backgroundMusic;
    private Clip deathSound;
    private Thread thread;
    private Hero hero;
    private Background background;
    private Ground ground;
    private Cloud cloud;
    private Obstacles obstacles;
    private float speedY = 0;
    private int gameState = GAME_START;
    private float score = 0;
    private float highestScore = 0;


    public Game(){
        this.thread = new Thread(this);
        this.hero = new Hero();
        this.hero.setX(0);
        this.hero.setY(200);
        this.background = new Background();
        this.ground = new Ground();
        this.cloud = new Cloud();
        this.obstacles = new Obstacles(hero);
        this.gameOverImage = Loader.loadImage("resources/game_over.png");
        this.backgroundImage = Loader.loadImage("resources/background/back-big.png");
    }

    public void startGame() {
        thread.start();
    }

    public void resetGame() {
        hero.setX(0);
        hero.setY(200);
        hero.setAlive(true);
        hero.setOnGround(true);
        score = 0;
        obstacles.reset();
    }

    public void update() {
        switch(gameState) {
            case GAME_PLAY:
                background.update();
                cloud.update();
                ground.update();
                hero.update();
                obstacles.update();
                if(!hero.getAlive()) {
                    gameState = GAME_END;
                }
                this.score += 0.05;
                break;
        }
    }

    @Override
    public void run() {
        while(true) {
            try {
                this.update();
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundImage, 0,0,null);
        g.setColor(Color.GREEN);
        g.drawLine(0, (int) FLOOR, this.getWidth(), (int) FLOOR);
        switch (gameState) {
            case GAME_START:
                background.draw(g);
                ground.draw(g);
                hero.draw(g);
                break;
            case GAME_PLAY:
                background.draw(g);
                ground.draw(g);
                cloud.draw(g);
                hero.draw(g);
                obstacles.draw(g);
                g.setColor(Color.WHITE);
                g.drawString("SCORE: " + (int) score, getWidth()-80, 20);
                g.drawString("HIGHEST: " + (int) highestScore, getWidth()-80, 40);
                break;
            case GAME_END:
                background.draw(g);
                ground.draw(g);
                cloud.draw(g);
                hero.draw(g);
                obstacles.draw(g);
                g.drawImage(gameOverImage, ((this.getWidth()-gameOverImage.getWidth())/2),((this.getHeight()-gameOverImage.getHeight())/2),null);
                break;
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

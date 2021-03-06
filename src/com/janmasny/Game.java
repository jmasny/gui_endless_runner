package com.janmasny;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Game extends JPanel implements Runnable {

    public static final int GAME_START = 0;
    public static final int GAME_PLAY = 1;
    public static final int GAME_END = 2;
    public static final float GRAVITY = 1.3f;
    public static final float FLOOR = 295;
    public static final int SPRITE_FLOOR = (int) FLOOR - 95;
    private BufferedImage titleImage;
    private BufferedImage startImage;
    private BufferedImage gameOverImage;
    private BufferedImage endImage;
    //private Clip backgroundMusic;
    //private Clip deathSound;
    private Thread thread;
    private Hero hero;
    private Background background;
    private Obstacles obstacles;
    private float speedY = 0;
    private int gameState = GAME_START;
    private float score = 0;
    private int highestScore;
    private int speedIncrease = 0;
    private int speedScoreThreshold = 25;


    public Game(){
        this.thread = new Thread(this);
        this.hero = new Hero(38, 12,22, 80);
        this.hero.setX(0);
        this.hero.setY(SPRITE_FLOOR);
        this.background = new Background();
        this.obstacles = new Obstacles(hero);
        this.titleImage = Loader.loadImage("resources/start/zombie_endless_runner.png");
        this.startImage = Loader.loadImage("resources/start/space_play.png");
        this.gameOverImage = Loader.loadImage("resources/end/gameover.png");
        this.endImage = Loader.loadImage("resources/end/play_again.png");
        this.highestScore = Loader.loadHighestScore();
    }

    public void startGame() {
        thread.start();
    }

    public void resetGame() {
        hero.setX(0);
        hero.setY(SPRITE_FLOOR);
        hero.setAlive(true);
        hero.setOnGround(true);
        score = 0;
        obstacles.reset();
    }

    public void update() {
        switch(gameState) {
            case GAME_PLAY:
                speedIncrease = (int) score / speedScoreThreshold;
                background.setSpeedIncrease(speedIncrease);
                background.update();
                hero.update();
                obstacles.setSpeedIncrease(speedIncrease);
                obstacles.update();
                if(!hero.getAlive()) {
                    gameState = GAME_END;
                    if (score > highestScore) {
                        highestScore = (int) score;
                        Loader.saveHighestScore(highestScore);
                    }
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
        switch (gameState) {
            case GAME_START:
                background.draw(g);
                hero.draw(g);
                g.drawImage(titleImage, ((this.getWidth()-titleImage.getWidth())/2),((this.getHeight()-titleImage.getHeight())/2)-40,null );
                g.drawImage(startImage, ((this.getWidth()-startImage.getWidth())/2),((this.getHeight()-startImage.getHeight())/2),null );
                break;
            case GAME_PLAY:
                background.draw(g);
                hero.draw(g);
                obstacles.draw(g);
                g.setColor(Color.WHITE);
                g.drawString("SCORE: " + (int) score, getWidth()-120, 20);
                g.drawString("HIGHEST: " + (int) highestScore, getWidth()-120, 40);
                break;
            case GAME_END:
                background.draw(g);
                hero.draw(g);
                obstacles.draw(g);
                g.drawImage(gameOverImage, ((this.getWidth()-gameOverImage.getWidth())/2),((this.getHeight()-gameOverImage.getHeight())/2)-40,null );
                g.drawImage(endImage, ((this.getWidth()-endImage.getWidth())/2),((this.getHeight()-endImage.getHeight())/2),null );
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

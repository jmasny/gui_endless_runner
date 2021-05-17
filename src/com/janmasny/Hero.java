package com.janmasny;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;

import static com.janmasny.Game.FLOOR;
import static com.janmasny.Game.GRAVITY;

public class Hero {

    private Animation heroRunAnimation;
    private BufferedImage heroDeadImage;
    private Clip jumpSound;
    private Rectangle heroBounds;
    private int x = 0;
    private int y = 0;
    private float speedY = 0;
    private boolean alive;
    private boolean onGround;

    public Hero() {
        heroRunAnimation = new Animation(100);
        heroRunAnimation.addFrame(Loader.loadImage("resources/walk_one.png"));
        heroRunAnimation.addFrame(Loader.loadImage("resources/walk_two.png"));
        heroRunAnimation.addFrame(Loader.loadImage("resources/walk_three.png"));
        heroRunAnimation.addFrame(Loader.loadImage("resources/walk_four.png"));
        heroDeadImage = Loader.loadImage("resources/dead.png");
        this.jumpSound = Loader.loadClip("resources/music/jump.wav");
        this.heroBounds = new Rectangle();
        this.alive = true;
        this.onGround = true;
    }

    public void update() {
        heroRunAnimation.update();
        // floor and jumping logic
        if ( y >= FLOOR - heroRunAnimation.getFrame().getHeight()) {
            speedY = 0;
            y = (int) FLOOR - heroRunAnimation.getFrame().getHeight();
            onGround = true;
        } else {
            speedY+=GRAVITY;
            y+=speedY;
        }
        heroBounds.x = x;
        heroBounds.y = y;
        heroBounds.width = (heroRunAnimation.getFrame().getWidth());
        heroBounds.height = (heroRunAnimation.getFrame().getHeight());
    }

    public void jump() {
        jumpSound.flush(); //bugged
        this.speedY += -12;
        this.y += (int) this.speedY;
        jumpSound.start(); //bugged
        onGround = false;
    }

    public void draw(Graphics g) {
        if (alive) {
            g.setColor(Color.RED);
            g.drawRect(heroBounds.x, heroBounds.y, heroBounds.width, heroBounds.height);
            g.drawImage(heroRunAnimation.getFrame(), x, y, null);
        } else {
            g.drawImage(heroDeadImage, x, y, null);
        }
    }

    public Rectangle getHeroBounds(){
        return heroBounds;
    }

    public boolean getOnGround() { return onGround; }

    public void setOnGround(boolean onGround) { this.onGround = onGround; }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean getAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}

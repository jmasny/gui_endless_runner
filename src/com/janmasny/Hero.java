package com.janmasny;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;

import static com.janmasny.Game.FLOOR;
import static com.janmasny.Game.GRAVITY;

public class Hero {
    private int x = 0;
    private int y = 0;
    private float speedY = 0;
    private BufferedImage heroDead;
    private Animation heroRun;
    private Rectangle heroBounds;
    private boolean alive = true;
    private Clip jumpSound;
    private boolean onGround;

    public boolean isOnGround() {
        return onGround;
    }

    public void setOnGround(boolean onGround) {
        this.onGround = onGround;
    }

    public Hero() {
        heroRun = new Animation(100);
        heroRun.addFrame(Resource.getResourceImage("resources/walk_one.png"));
        heroRun.addFrame(Resource.getResourceImage("resources/walk_two.png"));
        heroRun.addFrame(Resource.getResourceImage("resources/walk_three.png"));
        heroRun.addFrame(Resource.getResourceImage("resources/walk_four.png"));
        heroDead = Resource.getResourceImage("resources/dead.png");
        this.heroBounds = new Rectangle();
        this.jumpSound = Resource.loadClip("resources/music/jump.wav");
        this.onGround = true;
    }

    public void update() {
        heroRun.update();
        // this is made for jumping
        if ( y >= FLOOR - heroRun.getFrame().getHeight()) {
            speedY = 0;
            y = (int) FLOOR - heroRun.getFrame().getHeight(); //rectangle width
            onGround = true;
        } else {
            speedY+=GRAVITY;
            y+=speedY;
        }
        heroBounds.x = x;
        heroBounds.y = y;
        heroBounds.width = (heroRun.getFrame().getWidth());
        heroBounds.height = (heroRun.getFrame().getHeight());
    }

    public void jump() {
        jumpSound.flush();
        this.speedY += -12;
        this.y += (int) this.speedY;
        jumpSound.start();
        onGround = false;
    }

    public void draw(Graphics g) {
        if (alive) {
            g.setColor(Color.RED);
            g.drawRect(heroBounds.x, heroBounds.y, heroBounds.width, heroBounds.height);
            g.drawImage(heroRun.getFrame(), x, y, null);
        } else {
            g.drawImage(heroDead, x, y, null);
        }
    }

    public Rectangle getHeroBounds(){
        return heroBounds;
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public boolean getAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}

package com.janmasny;

import java.awt.*;

import static com.janmasny.Game.FLOOR;
import static com.janmasny.Game.GRAVITY;

public class Hero {
    private int x = 0;
    private int y = 0;
    private float speedY = 0;
    private Animation heroRun;
    private Rectangle heroBounds;
    private boolean alive = true;

    public Hero() {
        heroRun = new Animation(100);
        heroRun.addFrame(Resource.getResourceImage("resources/walk_one.png"));
        heroRun.addFrame(Resource.getResourceImage("resources/walk_two.png"));
        heroRun.addFrame(Resource.getResourceImage("resources/walk_three.png"));
        heroRun.addFrame(Resource.getResourceImage("resources/walk_four.png"));
        this.heroBounds = new Rectangle();
    }

    public void update() {
        heroRun.update();
        // this is made for jumping
        if ( y >= FLOOR - heroRun.getFrame().getHeight()) {
            speedY = 0;
            y = (int) FLOOR - heroRun.getFrame().getHeight(); //rectangle width
        } else {
            speedY+=GRAVITY;
            y+=speedY;
        }
        heroBounds.x = x;
        heroBounds.y = y;
        heroBounds.width = heroRun.getFrame().getWidth();
        heroBounds.height = heroRun.getFrame().getHeight();
    }

    public void jump() {
        this.speedY += -8;
        this.y += (int) this.speedY;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(x, y, heroRun.getFrame().getWidth(), heroRun.getFrame().getHeight());
        g.drawImage(heroRun.getFrame(), x, y, null);
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

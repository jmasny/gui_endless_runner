package com.janmasny;

import java.awt.*;

public class Zombie extends Obstacle {

    private int x;
    private int y;
    private Animation zombieWalk;
    private Rectangle zombieBounds;

    public Zombie(){
        this.x = 500;
        this.y = 200;
        this.zombieWalk = new Animation(500);
        this.zombieWalk.addFrame(Resource.getResourceImage("resources/zombie_one/walk_one.png"));
        this.zombieWalk.addFrame(Resource.getResourceImage("resources/zombie_one/walk_two.png"));
        this.zombieWalk.addFrame(Resource.getResourceImage("resources/zombie_one/walk_three.png"));
        this.zombieWalk.addFrame(Resource.getResourceImage("resources/zombie_one/walk_four.png"));
        this.zombieBounds = new Rectangle();
    }

    @Override
    public void update() {
        this.x += -3;
        this.zombieWalk.update();
        this.zombieBounds.x = x;
        this.zombieBounds.y = y;
        this.zombieBounds.width = zombieWalk.getFrame().getWidth();
        this.zombieBounds.height = zombieWalk.getFrame().getHeight();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(zombieWalk.getFrame(), x, y, null);
        g.setColor(Color.BLUE);
        g.drawRect(zombieBounds.x, zombieBounds.y, zombieBounds.width, zombieBounds.height);
    }

    @Override
    public Rectangle getBounds() {
        return zombieBounds;
    }

    @Override
    public boolean isOutOfScreen() {
        return false;
    }
}

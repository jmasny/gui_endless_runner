package com.janmasny;

import java.awt.*;

public class Zombie extends Obstacle {

    private int x;
    private int y;
    private Animation zombieWalk;
    private Rectangle zombieBounds;
    private Hero hero;

    public Zombie(Hero hero){
        this.x = 800;
        this.y = 200;
        this.zombieWalk = new Animation(500);
        this.zombieWalk.addFrame(Loader.loadImage("resources/zombie_one/walk_one.png"));
        this.zombieWalk.addFrame(Loader.loadImage("resources/zombie_one/walk_two.png"));
        this.zombieWalk.addFrame(Loader.loadImage("resources/zombie_one/walk_three.png"));
        this.zombieWalk.addFrame(Loader.loadImage("resources/zombie_one/walk_four.png"));
        this.zombieBounds = new Rectangle();
        this.hero = hero;
    }

    public Zombie(String path, int animationDelay){
        this.x = 800;
        this.y = 200;
        this.zombieWalk = new Animation(animationDelay);
        this.zombieWalk.addFrame(Loader.loadImage(path + "walk_one.png"));
        this.zombieWalk.addFrame(Loader.loadImage(path + "walk_two.png"));
        this.zombieWalk.addFrame(Loader.loadImage(path + "walk_three.png"));
        this.zombieWalk.addFrame(Loader.loadImage(path + "walk_four.png"));
        this.zombieBounds = new Rectangle();
    }

    @Override
    public void update() {
        this.x += -5;
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
        if(x + zombieWalk.getFrame().getWidth() < 0) {
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZombieWalk(Animation zombieWalk) {
        this.zombieWalk = zombieWalk;
    }

    public Animation getZombieWalk() {
        return zombieWalk;
    }
}

package com.janmasny;

import java.awt.*;

public class Zombie extends Obstacle {

    private int x;
    private int y;
    private Animation zombieWalk;
    private Rectangle zombieBounds;
    private Hero hero;
    private int detectX;
    private int detectY;

    public Zombie(Hero hero){
        this.x = 700;
        this.y = Game.SPRITE_FLOOR;
        this.zombieWalk = new Animation(500);
        this.zombieWalk.addFrame(Loader.loadImage("resources/zombies/one/walk_one.png"));
        this.zombieWalk.addFrame(Loader.loadImage("resources/zombies/one/walk_two.png"));
        this.zombieWalk.addFrame(Loader.loadImage("resources/zombies/one/walk_three.png"));
        this.zombieWalk.addFrame(Loader.loadImage("resources/zombies/one/walk_four.png"));
        this.zombieBounds = new Rectangle();
        this.hero = hero;
    }

    public Zombie(String path, int animationDelay, int detectX, int detectY, int detectWidth, int detectHeight){
        this.x = 700;
        this.y = Game.SPRITE_FLOOR;
        this.detectX = detectX;
        this.detectY = detectY;
        this.zombieWalk = new Animation(animationDelay);
        this.zombieWalk.addFrame(Loader.loadImage(path + "walk_one.png"));
        this.zombieWalk.addFrame(Loader.loadImage(path + "walk_two.png"));
        this.zombieWalk.addFrame(Loader.loadImage(path + "walk_three.png"));
        this.zombieWalk.addFrame(Loader.loadImage(path + "walk_four.png"));
        this.zombieBounds = new Rectangle(detectWidth, detectHeight);
    }

    @Override
    public void update() {
        this.x += -5;
        this.zombieWalk.update();
        this.zombieBounds.x = x + this.detectX;
        this.zombieBounds.y = y + this.detectY;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(zombieWalk.getFrame(), x, y, null);
        //g.setColor(Color.BLUE);
        //g.drawRect(zombieBounds.x, zombieBounds.y, zombieBounds.width, zombieBounds.height);
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

    public void setX(int x) {
        this.x = x;
    }

    public int getDetectX() {
        return detectX;
    }

    public void setDetectX(int detectX) {
        this.detectX = detectX;
    }

    public int getDetectY() {
        return detectY;
    }

    public void setDetectY(int detectY) {
        this.detectY = detectY;
    }

    public void setZombieBoundWidth(int width) {
        this.zombieBounds.width = width;
    }

    public void setZombieBoundHeight(int height) {
        this.zombieBounds.height = height;
    }

    public int getZombieBoundWidth() {
        return this.zombieBounds.width;
    }

    public int getZombieBoundHeight() {
        return this.zombieBounds.height;
    }

    public void setZombieWalk(Animation zombieWalk) {
        this.zombieWalk = zombieWalk;
    }

    public Animation getZombieWalk() {
        return zombieWalk;
    }
}

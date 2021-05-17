package com.janmasny;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Obstacles {

    private List<Obstacle> obstacleList;
    private Hero hero;
    private Zombie zombieOne;
    private Zombie zombieTwo;
    private Zombie zombieThree;
    private Random random;

    public Obstacles(Hero hero) {
        this.random = new Random();
        this.hero = hero;
        obstacleList = new ArrayList<Obstacle>();
        this.zombieOne = new Zombie("resources/zombies/one/", 400);
        this.zombieTwo = new Zombie("resources/zombies/two/", 600);
        this.zombieThree = new Zombie("resources/zombies/three/", 250);
        obstacleList.add(getRandomZombie());
    }

    public void update() {
        for (Obstacle obstacle: obstacleList) {
            obstacle.update();
            if(obstacle.getBounds().intersects(hero.getHeroBounds())) {
                hero.setAlive(false);
            }
        }
        if(obstacleList.get(0).isOutOfScreen()) {
            obstacleList.remove(obstacleList.get(0));
            obstacleList.add(getRandomZombie());
        }
    }

    public void reset() {
        obstacleList.clear();
        obstacleList.add(getRandomZombie());
    }

    public void draw(Graphics g) {
        for (Obstacle obstacle: obstacleList) {
            obstacle.draw(g);
        }
    }

    private Zombie getRandomZombie() {

        Zombie zombie = new Zombie(hero);
        int span = random.nextInt(150);
        zombie.setX(544+span);
        int i = random.nextInt(3);
        switch (i) {
            case 0:
                zombie.setZombieWalk(zombieOne.getZombieWalk());
                break;
            case 1:
                zombie.setZombieWalk(zombieTwo.getZombieWalk());
                break;
            case 2:
                zombie.setZombieWalk(zombieThree.getZombieWalk());
                break;
        }
        return zombie;
        }
}

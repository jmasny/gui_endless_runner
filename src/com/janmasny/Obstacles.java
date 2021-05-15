package com.janmasny;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Obstacles {

    private List<Obstacle> obstacleList;
    private Random random;
    private Animation zombieAnimationOne;
    private Animation zombieAnimationTwo;

    public Obstacles() {
        obstacleList = new ArrayList<Obstacle>();
        this.zombieAnimationOne = new Animation(500);
        this.zombieAnimationOne.addFrame(Resource.getResourceImage("resources/zombie_one/walk_one.png"));
        this.zombieAnimationOne.addFrame(Resource.getResourceImage("resources/zombie_one/walk_two.png"));
        this.zombieAnimationOne.addFrame(Resource.getResourceImage("resources/zombie_one/walk_three.png"));
        this.zombieAnimationOne.addFrame(Resource.getResourceImage("resources/zombie_one/walk_four.png"));
        this.zombieAnimationTwo = new Animation(300);
        this.zombieAnimationTwo.addFrame(Resource.getResourceImage("resources/zombie_two/walk_one.png"));
        this.zombieAnimationTwo.addFrame(Resource.getResourceImage("resources/zombie_two/walk_two.png"));
        this.zombieAnimationTwo.addFrame(Resource.getResourceImage("resources/zombie_two/walk_three.png"));
        this.zombieAnimationTwo.addFrame(Resource.getResourceImage("resources/zombie_two/walk_four.png"));
        random = new Random();
        obstacleList.add(getRandomZombie());
    }

    public void update() {
        for (Obstacle obstacle: obstacleList) {
            obstacle.update();
        }
        Obstacle firstObstacle = obstacleList.get(0);
        if(obstacleList.get(0).isOutOfScreen()) {
            obstacleList.remove(firstObstacle);
            obstacleList.add(getRandomZombie());
        }
    }

    public void draw(Graphics g) {
        for (Obstacle obstacle: obstacleList) {
            obstacle.draw(g);
        }
    }

    private Zombie getRandomZombie() {
        Zombie zombie = new Zombie();
        zombie.setX(500);
        int i = random.nextInt(2);
        switch (i) {
            case 0:
                zombie.setZombieWalk(zombieAnimationOne);
                break;
            case 1:
                zombie.setZombieWalk(zombieAnimationTwo);
                break;
        }
        return zombie;

    }
}

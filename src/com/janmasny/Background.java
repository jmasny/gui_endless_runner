package com.janmasny;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Background {

    private List<BackgroundImage> fenceImageList;
    private List<BackgroundImage> fogImageList;
    private List<BackgroundImage> fronttreesImageList;
    private List<BackgroundImage> fartreesImageList;
    private List<BackgroundImage> groundImageList;
    private BufferedImage backgroundImage;
    private BufferedImage fenceImage;
    private BufferedImage fogImage;
    private BufferedImage fronttreesImage;
    private BufferedImage fartreesImage;
    private BufferedImage groundImage;
    private int speedIncrease;

    public Background() {
        this.speedIncrease = 0;
        this.fenceImageList = new ArrayList<BackgroundImage>();
        this.fogImageList = new ArrayList<BackgroundImage>();
        this.fronttreesImageList = new ArrayList<BackgroundImage>();
        this.fartreesImageList = new ArrayList<BackgroundImage>();
        this.groundImageList = new ArrayList<BackgroundImage>();
        this.backgroundImage = Loader.loadImage("resources/background/back.png");
        this.fenceImage = Loader.loadImage("resources/background/fence.png");
        this.fogImage = Loader.loadImage("resources/background/fog.png");
        this.fronttreesImage = Loader.loadImage("resources/background/fronttrees.png");
        this.fartreesImage = Loader.loadImage("resources/background/fartrees.png");
        this.groundImage = Loader.loadImage("resources/background/ground.png");
        for (int i = 0; i < 2; i++) {
            BackgroundImage fenceBackgroundImage = new BackgroundImage();
            BackgroundImage fogBackgroundImage = new BackgroundImage();
            BackgroundImage fronttreesBackgroundImage = new BackgroundImage();
            BackgroundImage fartreesBackgroundImage = new BackgroundImage();
            BackgroundImage groundBackgroundImage = new BackgroundImage();

            fenceBackgroundImage.x = i * fenceImage.getWidth();
            fogBackgroundImage.x = i * fogImage.getWidth();
            fronttreesBackgroundImage.x = i * fronttreesImage.getWidth();
            fartreesBackgroundImage.x = i * fartreesImage.getWidth();
            groundBackgroundImage.x = i * groundImage.getWidth();

            fenceBackgroundImage.image = fenceImage;
            fogBackgroundImage.image = fogImage;
            fronttreesBackgroundImage.image = fronttreesImage;
            fartreesBackgroundImage.image = fartreesImage;
            groundBackgroundImage.image = groundImage;

            fenceImageList.add(fenceBackgroundImage);
            fogImageList.add(fogBackgroundImage);
            fronttreesImageList.add(fronttreesBackgroundImage);
            fartreesImageList.add(fartreesBackgroundImage);
            groundImageList.add(groundBackgroundImage);
        }
    }

    public void update() {
        this.updateImageList(fartreesImageList, 1 + speedIncrease);
        this.updateImageList(fronttreesImageList, 2 + speedIncrease);
        this.updateImageList(fogImageList, 3 + speedIncrease);
        this.updateImageList(fenceImageList, 4 + speedIncrease);
        this.updateImageList(groundImageList, 5 + speedIncrease);
    }

    private void updateImageList(List<BackgroundImage> imageList, int speed) {
        for (BackgroundImage image:imageList) {
            image.x += -speed;
        }
        if(imageList.get(0).x + imageList.get(0).image.getWidth() < 0) {
            imageList.get(0).x = imageList.get(imageList.size() - 1).x + imageList.get(0).image.getWidth();
            imageList.add(imageList.get(0));
            imageList.remove(0);
        }
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0,0,null);
        for (BackgroundImage fartrees:fartreesImageList) {
            g.drawImage(fartrees.image, fartrees.x, 0, null);
        }
        for (BackgroundImage fronttrees:fronttreesImageList) {
            g.drawImage(fronttrees.image, fronttrees.x, 0, null);
        }
        for (BackgroundImage fog:fogImageList) {
            g.drawImage(fog.image, fog.x, 135, null);
        }
        for (BackgroundImage fence:fenceImageList) {
            g.drawImage(fence.image, fence.x, 205, null);
        }
        for (BackgroundImage ground:groundImageList) {
            g.drawImage(ground.image, ground.x, (int) Game.FLOOR - 60, null);
        }

    }

    public void setSpeedIncrease(int speedIncrease) {
        this.speedIncrease = speedIncrease;
    }

    private class BackgroundImage {
        public int x;
        public BufferedImage image;
    }

}

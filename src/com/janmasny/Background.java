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
    private BufferedImage fenceImage;
    private BufferedImage fogImage;
    private BufferedImage fronttreesImage;
    private BufferedImage fartreesImage;

    public Background() {
        this.fenceImageList = new ArrayList<BackgroundImage>();
        this.fogImageList = new ArrayList<BackgroundImage>();
        this.fronttreesImageList = new ArrayList<BackgroundImage>();
        this.fartreesImageList = new ArrayList<BackgroundImage>();
        this.fenceImage = Loader.loadImage("resources/background/fence.png");
        this.fogImage = Loader.loadImage("resources/background/fog.png");
        this.fronttreesImage = Loader.loadImage("resources/background/fronttrees.png");
        this.fartreesImage = Loader.loadImage("resources/background/fartrees.png");
        for (int i = 0; i < 2; i++) {
            BackgroundImage fenceBackgroundImage = new BackgroundImage();
            BackgroundImage fogBackgroundImage = new BackgroundImage();
            BackgroundImage fronttreesBackgroundImage = new BackgroundImage();
            BackgroundImage fartreesBackgroundImage = new BackgroundImage();
            fenceBackgroundImage.x = i * fenceImage.getWidth();
            fogBackgroundImage.x = i * fogImage.getWidth();
            fronttreesBackgroundImage.x = i * fronttreesImage.getWidth();
            fartreesBackgroundImage.x = i * fartreesImage.getWidth();
            fenceBackgroundImage.image = fenceImage;
            fogBackgroundImage.image = fogImage;
            fronttreesBackgroundImage.image = fronttreesImage;
            fartreesBackgroundImage.image = fartreesImage;
            fenceImageList.add(fenceBackgroundImage);
            fogImageList.add(fogBackgroundImage);
            fronttreesImageList.add(fronttreesBackgroundImage);
            fartreesImageList.add(fartreesBackgroundImage);
        }
    }

    public void update() {
        this.updateImageList(fartreesImageList, 1);
        this.updateImageList(fronttreesImageList, 2);
        this.updateImageList(fogImageList, 3);
        this.updateImageList(fenceImageList, 4);
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
        for (BackgroundImage fartrees:fartreesImageList) {
            g.drawImage(fartrees.image, fartrees.x, 0, null);
        }
        for (BackgroundImage fronttrees:fronttreesImageList) {
            g.drawImage(fronttrees.image, fronttrees.x, 0, null);
        }
        for (BackgroundImage fog:fogImageList) {
            g.drawImage(fog.image, fog.x, 150, null);
        }
        for (BackgroundImage fence:fenceImageList) {
            g.drawImage(fence.image, fence.x, 205, null);
        }

    }

    private class BackgroundImage {
        public int x;
        public BufferedImage image;
    }

}

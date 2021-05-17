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
    private BufferedImage fartreesImage;

    public Background() {
        this.fenceImageList = new ArrayList<BackgroundImage>();
        this.fartreesImageList = new ArrayList<BackgroundImage>();
        this.fenceImage = Loader.loadImage("resources/background/fence-big.png");
        this.fartreesImage = Loader.loadImage("resources/background/far-trees-big.png");
        for (int i = 0; i < 2; i++) {
            BackgroundImage fenceBackgroundImage = new BackgroundImage();
            BackgroundImage fartreesBackgroundImage = new BackgroundImage();
            fenceBackgroundImage.x = i * fenceImage.getWidth();
            fartreesBackgroundImage.x = i * fartreesImage.getWidth();
            fenceBackgroundImage.image = fenceImage;
            fartreesBackgroundImage.image = fartreesImage;
            fenceImageList.add(fenceBackgroundImage);
            fartreesImageList.add(fartreesBackgroundImage);
        }
    }

    public void update() {

        this.updateImageList(fenceImageList, 3);
        this.updateImageList(fartreesImageList, 1);
    }

    private void updateImageList(List<BackgroundImage> ImageList, int speed) {
        for (BackgroundImage image:ImageList) {
            image.x += -speed;
        }
        if(ImageList.get(0).x + ImageList.get(0).image.getWidth() < 0) {
            ImageList.get(0).x = ImageList.get(ImageList.size() - 1).x + ImageList.get(0).image.getWidth();
            ImageList.add(ImageList.get(0));
            ImageList.remove(0);
        }
    }

    public void draw(Graphics g) {
        for (BackgroundImage fartrees:fartreesImageList) {
            g.drawImage(fartrees.image, fartrees.x, 20, null);
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

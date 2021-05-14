package com.janmasny;


import java.awt.*;
import java.awt.image.AreaAveragingScaleFilter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Ground {

    public static final int GROUND_LEVEL = 235;
    private List<GroundImage> listImage;
    private BufferedImage groundImageOne;
    private BufferedImage groundImageTwo;
    private BufferedImage groundImageThree;

    public Ground(Game game) {
        this.groundImageOne = Resource.getResourceImage("resources/ground_one.png");
        this.groundImageTwo = Resource.getResourceImage("resources/ground_two.png");
        this.groundImageThree = Resource.getResourceImage("resources/ground_three.png");
        this.listImage = new ArrayList<GroundImage>();
        int groundCount = 800 / groundImageOne.getWidth() + 2;
        for (int i = 0; i < groundCount; i++) {
            GroundImage groundImage = new GroundImage();
            groundImage.x = i * groundImageOne.getWidth();
            groundImage.image = groundImageOne;
            listImage.add(groundImage);
        }
    }

    public void update() {
        for (GroundImage groundImage:listImage) {
            groundImage.x --;
        }
        GroundImage firstGroundImage = listImage.get(0);
        if(listImage.get(0).x + groundImageOne.getWidth() < 0) {
            firstGroundImage.x = listImage.get(listImage.size() - 1).x + groundImageOne.getWidth();
            listImage.add(listImage.get(0));
            listImage.remove(0);
        }
    }

    public void draw(Graphics g) {
        for (GroundImage groundImage:listImage) {
            g.drawImage(groundImage.image, groundImage.x, GROUND_LEVEL, null);
        }
//        g.drawImage(groundImageOne, 0, GROUND_LEVEL, null);
//        g.drawImage(groundImageTwo, groundImageOne.getWidth(), GROUND_LEVEL, null);
//        g.drawImage(groundImageThree, groundImageOne.getWidth()+groundImageTwo.getWidth(), GROUND_LEVEL, null);
    }

    private class GroundImage {
        int x;
        BufferedImage image;
    }
}

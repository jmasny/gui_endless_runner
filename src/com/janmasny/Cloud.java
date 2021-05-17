package com.janmasny;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Cloud {

    private BufferedImage cloudImage;
    private List<CloudImage> clouds;

    public Cloud() {
        this.cloudImage = Loader.loadImage("resources/cloud.png");
        this.clouds = new ArrayList<CloudImage>();

        CloudImage cloudImageOne = new CloudImage();
        cloudImageOne.x = 100;
        cloudImageOne.y = 50;
        clouds.add(cloudImageOne);

        cloudImageOne = new CloudImage();
        cloudImageOne.x = 220;
        cloudImageOne.y = 120;
        clouds.add(cloudImageOne);

        cloudImageOne = new CloudImage();
        cloudImageOne.x = 400;
        cloudImageOne.y = 80;
        clouds.add(cloudImageOne);

        cloudImageOne = new CloudImage();
        cloudImageOne.x = 600;
        cloudImageOne.y = 150;
        clouds.add(cloudImageOne);
    }

    public void update() {
        for (CloudImage cloudImage: clouds) {
            cloudImage.x += -1;
        }
        CloudImage firstCloud = clouds.get(0);
        if (firstCloud.x + cloudImage.getWidth() < 0) {
            firstCloud.x = 600;
            clouds.remove(firstCloud);
            clouds.add(firstCloud);
        }
    }

    public void draw(Graphics g) {
        for(CloudImage cloud: clouds) {
            g.drawImage(cloudImage, cloud.x, cloud.y, null);
        }
    }

    private class CloudImage {
        int x;
        int y;

    }
}

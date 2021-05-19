package com.janmasny;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.*;

public class Loader {

    public static BufferedImage loadImage(String path) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public static Clip loadClip(String path) {
        Clip sound = null;
        try {
            sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(new File(path)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sound;
    }

    public static int loadHighestScore() {
        File file = null;
        try {
            file = new File("resources/results/highest_score.txt");
            if(file.createNewFile()) {
                System.out.println(file.getName());
            }
            System.out.println(file.getName());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String text = reader.readLine();
            int highestScore = 0;
            if (text != null) {
                highestScore = Integer.parseInt(text);
            }
            System.out.println(highestScore);
            reader.close();
            return highestScore;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static void saveHighestScore(int highestScore) {
        File file = null;
        try {
            file = new File("resources/results/highest_score.txt");
            if(file.createNewFile()) {
                //System.out.println(file.getName());
            }
            FileWriter writer = new FileWriter(file);
            String toSave = Integer.toString(highestScore);
            writer.write(toSave);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

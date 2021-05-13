package com.janmasny;

import java.awt.*;

import static com.janmasny.Game.FLOOR;
import static com.janmasny.Game.GRAVITY;

public class Hero {
    private int x = 0;
    private int y = 0;
    private float speedY = 0;

    public void updatePosition() {
        // this is made for jumping
        if ( y >= FLOOR-100) {
            speedY = 0;
            y = (int) FLOOR - 100; //rectangle width
        } else {
            speedY+=GRAVITY;
            y+=speedY;
        }
    }

    public void jump() {
        this.speedY += -3;
        this.y += (int) this.speedY;
    }

    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 100, 100);
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }
}

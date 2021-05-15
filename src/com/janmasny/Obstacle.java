package com.janmasny;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Obstacle {

    public abstract void update();
    public abstract void draw(Graphics g);
    public abstract Rectangle getBounds();
    public abstract boolean isOutOfScreen();
}

package com.tuhkisgames.engine;

import java.awt.Graphics;
import java.util.LinkedList;

public class Raycast extends GameObject {
    float rot = 0;
    int lenght = 1024;
    int rx, ry = 0;

    public Raycast(int p_x, int p_y, Camera p_cam, float p_rot) {
        super(p_x, p_y, p_cam);
        rot = p_rot;
        rx = p_x;
        ry = p_y;
    }

    @Override
    public void tick(LinkedList<Rect> tiles) {
        hitbox.setX(rx);
        hitbox.setY(ry);
        
    }

    @Override
    public void render(Graphics g, Camera cam) {
        // Raycast is not supposed to be rendered!
    }
    
}

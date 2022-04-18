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

        hitbox.setW(8); hitbox.setH(8);
    }

    @Override
    public void tick(LinkedList<Rect> tiles) {
        hitbox.setX(rx);
        hitbox.setY(ry);

        for (int i = 0; i < lenght; i++) {
            hitbox.setX( (int) (hitbox.getX() + Math.cos(Math.toRadians(rot)) * hitbox.getW()) );
            hitbox.setY( (int) (hitbox.getY() + Math.sin(Math.toRadians(rot)) * hitbox.getH()) );

            if (!hitbox.collideTiles(tiles).isEmpty()) {
                break;
            }
        }
    }

    @Override
    public void render(Graphics g, Camera cam) {
        // Raycast is not supposed to be rendered!
    }
    public void setRot(float p_rot) {
        rot = p_rot;
    }
    public float getRot() {
        return rot;
    }

    public void setRX(int x) { rx = x; }
    public void setRY(int y) { ry = y; }

    public int getRX() { return rx; }
    public int getRY() { return ry; }
    
}

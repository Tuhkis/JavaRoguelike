package com.tuhkisgames.engine;

import java.awt.Graphics;
import java.util.LinkedList;

import com.tuhkisgames.Game;

public abstract class GameObject {

    protected Rect hitbox;
    protected float velX, velY;

    protected boolean[] states;
    protected Camera cam;
    public GameObject(int p_x, int p_y, Camera p_cam) {
        this.hitbox = new Rect(p_x, p_y, 32, 32);
        this.cam = p_cam;
    }

    public abstract void tick(LinkedList<Rect> tiles);
    public abstract void render(Graphics g, Camera cam);

    public void setX(int p_x) {
        this.hitbox.setX(p_x);
    }
    public void setY(int p_y) {
        this.hitbox.setY(p_y);
    }
    public Rect getHitbox() {
        return this.hitbox;
    }

    public void setVelX(int p_x) {
        this.velX = p_x;
    }
    public void setVelY(int p_y) {
        this.velY = p_y;
    }
    public float getVelX() {
        return this.velX;
    }
    public float getVelY() {
        return this.velY;
    }

    public GameObject getCollidingObjects() {
        for (int o = 0; o < Game.getMainScene().getObjects().size(); o++) {
            GameObject g = Game.getMainScene().getObjects().get(o);
            if (hitbox.collideRect(g.getHitbox()) & g != this) {
                return g;
            }
        }
        return null;
    }
    
}

package com.tuhkisgames.gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import com.tuhkisgames.Game;
import com.tuhkisgames.engine.Camera;
import com.tuhkisgames.engine.GameObject;
import com.tuhkisgames.engine.Rect;

public class TestObject extends GameObject {

    Color colour = Color.BLUE;

    public TestObject(int p_x, int p_y, Camera p_cam) {
        super(p_x, p_y, p_cam);
    }

    @Override
    public void tick(LinkedList<Rect> tiles) {
        
    }

    @Override
    public void render(Graphics g, Camera cam) {
        g.setColor(colour);
        g.fillRect(hitbox.getX() - cam.getX(), hitbox.getY() - cam.getY(), hitbox.getW(), hitbox.getH());
        
    }

    public void die() {
        Game.getMainScene().getObjects().remove(this);
    }
    
}

package com.tuhkisgames.gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import com.tuhkisgames.Game;
import com.tuhkisgames.engine.Camera;
import com.tuhkisgames.engine.GameObject;
import com.tuhkisgames.engine.Rect;

public class Player extends GameObject {
    private int moveSpeed = 300;

    public Player(int p_x, int p_y, Camera p_cam) {
        super(p_x, p_y, p_cam);
    }

    @Override
    public void tick(LinkedList<Rect> tiles) {
        // TODO Auto-generated method stub
        move();
        hitbox.move((int) (velX), (int) (velY), tiles);
    }

    @Override
    public void render(Graphics g, Camera cam) {
        g.setColor(new Color(255, 255, 255));
        g.fillRect(hitbox.getX() - cam.getX(), hitbox.getY() - cam.getY(), hitbox.getW(), hitbox.getH());
    }
    
    private void move() {
        velX = 0; velY = 0;
        if (Game.input.isPressed("D")) { velX += moveSpeed; }
        if (Game.input.isPressed("A")) { velX += -moveSpeed; }

        if (Game.input.isPressed("S")) { velY += moveSpeed; }
        if (Game.input.isPressed("W")) { velY += -moveSpeed; }
    }
}

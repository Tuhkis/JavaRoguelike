package com.tuhkisgames.gameObjects;

import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import com.tuhkisgames.Game;
import com.tuhkisgames.engine.Camera;
import com.tuhkisgames.engine.FPS;
import com.tuhkisgames.engine.GameObject;
import com.tuhkisgames.engine.Rect;
import com.tuhkisgames.engine.Sprite;

public class Player extends GameObject {
    private int moveSpeed = 300;
    private float rot = 0.0f;

    private Sprite spr;

    public Player(int p_x, int p_y, Camera p_cam) {
        super(p_x, p_y, p_cam);
        hitbox.setW(42); hitbox.setH(58);
        spr = new Sprite("./assets/char/player/placeholder.png", hitbox.getW(), hitbox.getH(), 1);
    }

    @Override
    public void tick(LinkedList<Rect> tiles) {
        rotate();
        move();
        hitbox.move((int) (velX), (int) (velY), tiles);
    }

    @Override
    public void render(Graphics g, Camera cam) {
        // g.setColor(new Color(255, 255, 255));
        // g.fillRect(hitbox.getX() - cam.getX(), hitbox.getY() - cam.getY(), hitbox.getW(), hitbox.getH());

        spr.render(g, hitbox.getX() - cam.getX(), hitbox.getY() - cam.getY(), 0, rot);
    }
    
    private void move() {
        velX = 0; velY = 0;
        if (Game.input.isPressed("D")) { velX += moveSpeed; }
        if (Game.input.isPressed("A")) { velX += -moveSpeed; }

        if (Game.input.isPressed("S")) { velY += moveSpeed; }
        if (Game.input.isPressed("W")) { velY += -moveSpeed; }
    }
    private void rotate() {
        int rel_x = (int) (Game.mousePosition.getX() + cam.getX() - (hitbox.getX() + hitbox.getW() / 2));
        int rel_y = (int) (Game.mousePosition.getY() + cam.getY() - (hitbox.getY() + hitbox.getH() / 2));
        rot = -((int) ((180 / Math.PI) * -Math.atan2(rel_y, rel_x)));
    }
}

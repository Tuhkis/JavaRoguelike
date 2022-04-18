package com.tuhkisgames.gameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;

import com.tuhkisgames.Game;
import com.tuhkisgames.engine.Camera;
import com.tuhkisgames.engine.GameObject;
import com.tuhkisgames.engine.Raycast;
import com.tuhkisgames.engine.Rect;
import com.tuhkisgames.engine.Sprite;

public class Player extends GameObject {
    private int moveSpeed = 300;
    private float rot = 0.0f;

    private Sprite spr;

    private Raycast rtest = new Raycast(0, 0, cam, rot);

    public Player(int p_x, int p_y, Camera p_cam) {
        super(p_x, p_y, p_cam);
        hitbox.setW(16); hitbox.setH(16);
        spr = new Sprite("./assets/char/player/placeholder.png", 42, 58, 1);
    }

    @Override
    public void tick(LinkedList<Rect> tiles) {
        rotate();
        move();
        hitbox.move((int) (velX), (int) (velY), tiles);

        Game.getMainScene().getCam().setX(
            (int) ( hitbox.getX() + hitbox.getW() / 2 - Game.WIDTH / 2 * (1 - 0.05) + Game.mousePosition.getX() * 0.05 )
        );

        Game.getMainScene().getCam().setY(
            (int) ( hitbox.getY() + hitbox.getH() / 2 - Game.HEIGHT / 2 * (1 - 0.05) + Game.mousePosition.getY() * 0.05 )
        );

        rtest.setRX(hitbox.getX() + hitbox.getW() / 2);
        rtest.setRY(hitbox.getY() + hitbox.getH() / 2);

        rtest.tick(tiles);

        System.out.println(rtest.getCollidingObjects());
    }

    @Override
    public void render(Graphics g, Camera cam) {
        // g.setColor(new Color(55, 255, 100));
        // g.fillRect(hitbox.getX() - cam.getX(), hitbox.getY() - cam.getY(), hitbox.getW(), hitbox.getH());

        g.setColor(Color.RED);
        g.drawLine(
            rtest.getHitbox().getX() - cam.getX(), rtest.getHitbox().getY() - cam.getY(),
            rtest.getRX() - cam.getX(), rtest.getRY() - cam.getY());
        spr.render(g, hitbox.getX() - spr.getW() / 2 + hitbox.getW() / 2 - cam.getX(), hitbox.getY() - spr.getH() / 2 + hitbox.getH() / 2 - cam.getY(), 0, rot);
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

        rtest.setRot(rot);
    }
}

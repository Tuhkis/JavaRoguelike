package com.tuhkisgames.gameObjects.enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.tuhkisgames.Game;
import com.tuhkisgames.engine.Camera;
import com.tuhkisgames.engine.GameObject;
import com.tuhkisgames.engine.Rect;
import com.tuhkisgames.gameObjects.Player;

public class BaseEnemy extends GameObject {
    int hp = 10;
    Random r = new Random();
    Player player;

    public BaseEnemy(int p_x, int p_y, Camera p_cam) {
        super(p_x, p_y, p_cam);
        
        for (int o = 0; o < Game.getMainScene().getObjects().size(); o++) {
            if (Game.getMainScene().getObjects().get(o).getClass() == new Player(0, 0, cam).getClass()) {
                player = (Player) (Game.getMainScene().getObjects().get(o));
                break;
            }
        }
    }

    @Override
    public void tick(LinkedList<Rect> tiles) {
        int rel_x = (int) (player.getHitbox().getX() - (hitbox.getX() + hitbox.getW() / 2));
        int rel_y = (int) (player.getHitbox().getY() - (hitbox.getY() + hitbox.getH() / 2));
        int rot = -((int) ((180 / Math.PI) * -Math.atan2(rel_y, rel_x)));

        hitbox.move((int) (Math.cos(rot) * 300), (int) (Math.sin(rot) * 300), tiles);
        
    }

    @Override
    public void render(Graphics g, Camera cam) {
        g.setColor(Color.CYAN);
        g.fillOval(hitbox.getX() - cam.getX(), hitbox.getY() - cam.getY(), hitbox.getW(), hitbox.getH());
        
    }
    public void damage(int dmg) {
        int dmg2 = (dmg + r.nextInt(3)) * (r.nextInt(1) + 1);
        hp -= dmg2;
        System.out.println(hp);

        if (hp <= 0) kill();
    }
    public void kill() {
        Game.getMainScene().getObjects().remove(this);
    }
    
}

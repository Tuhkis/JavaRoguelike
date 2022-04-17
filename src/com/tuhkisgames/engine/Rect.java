package com.tuhkisgames.engine;

import java.util.LinkedList;

public class Rect {
    private int x;
    private int y;
    private int w;
    private int h;

    public Rect(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public boolean collideRect(Rect r2) {
        if (x + w > r2.x && x < r2.x + r2.w && y + h > r2.y && y < r2.y + r2.h) {
            return true;
        }
        else {
            return false;
        }
    }
    public LinkedList<Rect> collideTiles(LinkedList<Rect> tiles) {
        LinkedList<Rect> collisions = new LinkedList<Rect>();
        for (int t = 0; t < tiles.size(); t++) {
            Rect tileInst = tiles.get(t);

            if (collideRect(tileInst)) {
                collisions.add(tileInst);
            };
        }
        return collisions;
    }
    public void move(int movX, int movY, LinkedList<Rect> tiles) {
        x += (int)(movX * FPS.getDeltaTime());
        LinkedList<Rect> collisions = collideTiles(tiles);
        for (int i = 0; i < collisions.size(); i++) {
            Rect tileInst = collisions.get(i);
            if (movX < 0) {
                x = tileInst.getX() + tileInst.getW();
            }
            else if (movX > 0) {
                x = tileInst.getX() - w;
            }
        }

        y += (int)(movY * FPS.getDeltaTime());
        collisions = collideTiles(tiles);
        for (int i = 0; i < collisions.size(); i++) {
            Rect tileInst = collisions.get(i);
            if (movY < 0) {
                y = tileInst.getY() + tileInst.getH();
            }
            else if (movY > 0) {
                y = tileInst.getY() - h;
            }
        }
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }


    public int getW() {
        return w;
    }
    public int getH() {
        return h;
    }

    public void setW(int w) {
        this.w = w;
    }
    public void setH(int h) {
        this.h = h;
    }
}

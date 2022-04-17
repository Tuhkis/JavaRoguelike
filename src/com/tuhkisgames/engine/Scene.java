package com.tuhkisgames.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Scene {
    LinkedList<GameObject> object = new LinkedList<GameObject>();
    Camera cam = new Camera(0, 0);
    LinkedList<Rect> tiles = new LinkedList<>();
    private ParticlesEmitter particles = new ParticlesEmitter(0, 0, cam, 0);

    public Scene() {
    }

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.tick(tiles);
        }
        cam.tick();
        particles.tick(tiles);
    }
    public void render(Graphics g) {
        for (int i = 0; i < tiles.size(); i++) {
            Rect rectInst = tiles.get(i);

            g.setColor(new Color(218, 219, 189));
            g.fillRect(rectInst.getX() - cam.getX(), rectInst.getY() - cam.getY(), rectInst.getW(), rectInst.getH());
        }

        for (int i = 0; i < object.size(); i++) {
            GameObject tempObject = object.get(i);

            tempObject.render(g, cam);
        }
        particles.render(g, cam);
    }

    public void addObject(GameObject gameObject) {
        this.object.add(gameObject);
    }
    public void removeObject(GameObject gameObject) {
        this.object.remove(gameObject);
    }

    public Camera getCam() {
        return this.cam;
    }

    public void addTile(Rect tile) {
        this.tiles.add(tile);
    }

    public LinkedList<GameObject> getObjects() {
        return object;
    }

    public ParticlesEmitter getParticles() {
        return particles;
    }
}

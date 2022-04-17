package com.tuhkisgames.engine;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.Image;
public class Sprite {
    BufferedImage image;

    int w;
    int h;
    int frames;
    AffineTransform trans = new AffineTransform();
    AffineTransform identity = new AffineTransform();
    public Sprite(String path, int w, int h, int f) {
        try {
            image = ImageIO.read(new File(path));
            //image = (BufferedImage)image.getScaledInstance(w * f, h, BufferedImage.SCALE_FAST);

            this.w = w;
            this.h = h;
            frames = f;
        } catch (IOException e) {
            System.out.println("PROBLEM LOADING SPRITE '" + path + "'" + System.lineSeparator() + " ERROR: " + e);
        }
    }
    public void render(Graphics g, int x, int y, int f, float rot) {
        Graphics2D g2d = (Graphics2D)g;
        Image img = image.getSubimage(f * (image.getWidth() / frames), 0, image.getWidth() / frames, image.getHeight()).getScaledInstance(w, h, BufferedImage.SCALE_FAST);
        trans.setTransform(identity);
        trans.translate(x, y);
        trans.rotate(rot, w / 2, h / 2);

        g2d.drawImage(img, trans, null);
    }
}

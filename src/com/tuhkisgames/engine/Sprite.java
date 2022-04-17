package com.tuhkisgames.engine;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
// ~/.config/Code/User/settings.json
public class Sprite {
    BufferedImage image;

    int w;
    int h;
    int frames;
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
    public void render(Graphics g, int x, int y, int f) {
        g.drawImage(image.getSubimage(f * (image.getWidth() / frames), 0, image.getWidth() / frames, image.getHeight()).getScaledInstance(w, h, BufferedImage.SCALE_FAST), x, y, null);
    }
}

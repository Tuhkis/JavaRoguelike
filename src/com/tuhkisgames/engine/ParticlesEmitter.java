package com.tuhkisgames.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class ParticlesEmitter extends GameObject {
    private int GRAVITY;
    private LinkedList<float[]> particles = new LinkedList<>();
    public ParticlesEmitter(int p_x, int p_y, Camera p_cam, int p_gravity) {
        super(p_x, p_y, p_cam);
        GRAVITY = p_gravity;
    }

    public void emitParticle(int d, int r, int g, int b, int p_velX, int p_velY, int shrink) {
        // 0 = x, 1 = y, 2 = d, 3 = red, 4 = green, 5 = blue, 6 = velX, 7 = velY, 8 = Lifetime, 9 = Shrink Factor

        particles.add( new float[] {
            hitbox.getX(), hitbox.getY(), d, r, g, b, p_velX, p_velY, 0, shrink
        });
    }

    @Override
    public void tick(LinkedList<Rect> tiles) {
        for (int i = 0; i < particles.size(); i++) {
            float[] particle = particles.get(i);

            particle[7] += GRAVITY * FPS.getDeltaTime();

            particle[0] += particle[6] * FPS.getDeltaTime();
            particle[1] += particle[7] * FPS.getDeltaTime();

            particle[2] -= particle[9] * FPS.getDeltaTime();

            particle[8] += 100 * FPS.getDeltaTime();
            if (particle[8] >= 250) {
                particles.remove(particle);
            }
        }
    }

    @Override
    public void render(Graphics g, Camera cam) {
        for (int i = 0; i < particles.size(); i++) {
            float[] particle = particles.get(i);

            g.setColor(new Color((int)(particle[3]), (int)(particle[4]), (int)(particle[5])));
            g.fillOval((int)(particle[0]) - cam.getX() - (int)((particle[2]) / 2), (int)(particle[1]) - cam.getY() - (int)((particle[2]) / 2), (int)(particle[2]), (int)(particle[2]));
        }
    }

    public LinkedList<float[]> getParticles() {
        return particles;
    }
    
}

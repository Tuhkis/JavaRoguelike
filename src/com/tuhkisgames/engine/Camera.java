package com.tuhkisgames.engine;

public class Camera {
    int x;
    int y;
    int trauma = 0;
    public Camera(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return (int)(this.x + (Math.cos(trauma) * (trauma / 10)));
    }
    public int getY() {
        return (int)(this.y + (Math.sin(trauma) * (trauma / 10)));
    }
    public int getTrauma() {
        return trauma;
    }

    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setTrauma(int trauma) {
        this.trauma = trauma;
    }
    public void tick() {
        this.trauma -= 0.1 * FPS.getDeltaTime();

        if (this.trauma < 0) this.trauma = 0;
    }
}

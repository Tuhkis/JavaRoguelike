package com.tuhkisgames;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.awt.Font;
import java.awt.Point;

import com.tuhkisgames.engine.FPS;
import com.tuhkisgames.engine.KeyInput;
import com.tuhkisgames.engine.MouseInput;
import com.tuhkisgames.engine.Scene;
import com.tuhkisgames.engine.Window;
import com.tuhkisgames.gameObjects.Player;
import com.tuhkisgames.gameObjects.TestObject;
import com.tuhkisgames.gameObjects.enemies.BaseEnemy;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 1024, HEIGHT = 600;

    private Thread thread;
    private boolean RUNNING = false;

    private static Scene mainScene;

    public static KeyInput input = new KeyInput();
    public static MouseInput mInput = new MouseInput();
    public static Point mousePosition;

    private static Random r = new Random();

    Window win;

    public Game() {
        mousePosition = new Point(0, 0);
        mainScene = new Scene();
        this.addKeyListener(input);
        this.addMouseListener(mInput);
        win = new Window(WIDTH, HEIGHT, "Roguelite Game", this);

        // win.frame.initComponents();

        // Make Tiles
        //mainScene.addTile(new Rect(128, 128, 512, 32));

        // Create Game Objects
        mainScene.addObject(new Player(0, 0, mainScene.getCam()));

        for (int i = 0; i < 128; i++) {
            mainScene.addObject(new BaseEnemy(r.nextInt(999), r.nextInt(999), mainScene.getCam()));
        }
        mainScene.getCam().setTrauma(0);
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        RUNNING = true;
    }
    public synchronized void stop() {
        try {
            thread.join();
            RUNNING = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        FPS.calcBeginTime();
        while(RUNNING) {
                long now = System.nanoTime();
                delta += (now - lastTime) / ns;
                lastTime = now;
                while(delta >=1) {
                    FPS.calcDeltaTime();
                    tick();
                    delta--;
                    FPS.tick();

                    if (!(getMousePosition() == null)) mousePosition = getMousePosition();
                }
                if(RUNNING)
                render();
        }
        stop();
    }

    private void tick() {
        mainScene.tick();
    }
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(2);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setFont(new Font("Arial", Font.BOLD, 32));

        g.setColor(new Color(99, 99, 99));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        mainScene.render(g);
        // blurLayer.paint(g);
        // g.drawImage(, 0, 0, this;

        g.dispose();
        bs.show();
    }

    public static Scene getMainScene() {
        return mainScene;
    }

    public static void main(String[] args) throws Exception {
        System.setProperty("sun.java2d.opengl", "true");
        new Game();
    }
}

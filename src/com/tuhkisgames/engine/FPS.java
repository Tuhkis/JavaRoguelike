package com.tuhkisgames.engine;

import java.time.Duration;
import java.time.Instant;

public class FPS {
    private FPS() {}

    private static Duration fpsDeltaTime = Duration.ZERO;
    private static Duration lastTime = Duration.ZERO;
    private static Instant beginTime = Instant.now();
    private static double deltaTime = fpsDeltaTime.toMillis() - lastTime.toMillis();
    private static float time = 0;

    public static void calcBeginTime() {
        beginTime = Instant.now();
        fpsDeltaTime = Duration.ZERO;
    }
    public static void calcDeltaTime() {
        fpsDeltaTime = Duration.between(beginTime, Instant.now());
        deltaTime = (float)fpsDeltaTime.toMillis() - lastTime.toMillis();
        lastTime = fpsDeltaTime;
    }
    public static double getDeltaTime() {
        return deltaTime / 1000;
    }

    public static void tick() {
        time += 1 * deltaTime;
    }
    public static float getTime() {
        return time / 1000;
    }
}

package com.tuhkisgames.engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class KeyInput extends KeyAdapter {

    private LinkedList<String> pressed = new LinkedList<String>();
    
    public KeyInput() {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String key = KeyEvent.getKeyText(e.getKeyCode());

        if (!pressed.contains(key)) {
            pressed.add(key);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        String key = KeyEvent.getKeyText(e.getKeyCode());

        if (pressed.contains(key)) {
            pressed.remove(key);
        }
    }
    public boolean isPressed(String key) {
        return pressed.contains(key);
    }
}

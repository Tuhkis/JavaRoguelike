package com.tuhkisgames.engine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class MouseInput extends MouseAdapter {
    private LinkedList<Integer> pressed = new LinkedList<Integer>();

    public MouseInput() {
        super();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int butt = e.getButton();

        if (!pressed.contains(butt)) {
            pressed.add(butt);
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        int butt = e.getButton();

        if (pressed.contains(butt)) {
            pressed.removeLastOccurrence(butt);
        }
    }

    public boolean isPressed(int b) {
        return pressed.contains(b);
    }
}

package com.tuhkisgames.engine;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.tuhkisgames.Game;

public class Window extends Canvas {
    public JFrame frame;
    public Window(int width, int height, String title, Game game) {
        frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.add(game);

        frame.setVisible(true);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        game.start();
    }
}

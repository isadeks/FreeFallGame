package org.cis1200.freefall;

import javax.swing.*;
import java.awt.*;

public class RunFreeFall implements Runnable {
    public void run() {
        // Top-level frame in which game components live.
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("Free Fall");
        frame.setLocation(300, 50);

        ImageIcon image = new ImageIcon("files/instructions.png"); // imports the image
        JOptionPane.showMessageDialog(null, image);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);

        // // Main playing area
        final GameCourt b = new GameCourt(status);
        frame.add(b, BorderLayout.CENTER);

        // Reset button
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);
        // Note here that when we add an action listener to the reset button, we
        // define it as an anonymous inner class that is an instance of
        // ActionListener with its actionPerformed() method overridden. When the
        // button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> b.reset());
        control_panel.add(reset);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        if (!b.loadData()) {
            b.reset();
        }
    }
}
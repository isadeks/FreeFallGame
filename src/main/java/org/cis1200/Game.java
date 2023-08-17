package org.cis1200;

import javax.swing.*;

public class Game {
    /**
     * Main method run to start and run the game.
     */
    public static void main(String[] args) {
        // Set the game you want to run here
        Runnable game = new org.cis1200.freefall.RunFreeFall();
        SwingUtilities.invokeLater(game);
    }
}

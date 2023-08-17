package org.cis1200.freefall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;

public class GameCourt extends JPanel {

    private static int score = 0;
    public static final int INTERVAL = 35;

    private Background background = new Background();

    private Character character = new Character(400, 500);

    private ArrayList<Platform> platforms = new ArrayList<>();

    private boolean playing = true; // whether the game is running

    private final JLabel status; // Current status text, i.e. "Running..."

    public Character getCharacter() {
        return character;
    }

    public ArrayList<Platform> getPlatformsCopy() {
        ArrayList<Platform> platforms1 = new ArrayList<>();
        for (Platform p : platforms) {
            platforms1.add(p);
        }
        return platforms1;
    }

    public boolean isPlaying() {
        return playing;
    }

    public static int getScore() {
        return score;
    }

    public static void incrementScore() {
        score++;
    }

    public GameCourt(JLabel status) {
        this.status = status;
        Timer timer = new Timer(INTERVAL, e -> tick());
        timer.start();
        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);
        // This key listener allows the square to move as long as an arrow key
        // is pressed, by changing the square's velocity accordingly. (The tick
        // method below actually moves the square.)

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    character.setVx(-3);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    character.setVx(3);
                }
            }

            public void keyReleased(KeyEvent e) {
                character.setVx(0);
            }
        });
    }

    void tick() {
        if (playing) {
            check();
            boolean platformOutOfBounds = false;
            for (Platform platform : platforms) {
                platform.move();
                if (platform.getPy() <= -25) {
                    platformOutOfBounds = true;
                }
            }
            if (platformOutOfBounds) {
                platforms.remove(0);
                platforms.remove(0);
                platforms.remove(0);
                for (int i = 1; i <= 3; i++) {
                    int chance = (int) (Math.random() * (12 - 1) + 1);
                    if (chance <= 3) {
                        platforms.add(new PoisonPlatform(400, 500, i, 500, false));
                    } else if (chance >= 4 && chance <= 5) {
                        platforms.add(new BrokenPlatform(400, 500, i, 500, false));
                    } else if (chance >= 6 && chance >= 10) {
                        platforms.add(new MoneyPlatform(400, 500, i, 500, false));
                    } else {
                        platforms.add(new Platform(400, 500, i, 500, false));
                    }
                }
            }
            character.move();
            repaint();
            if (character.getPy() <= -40 || character.getPy() >= 520) {
                status.setText("You LOSE");
                playing = false;
            }
            storeData();
        }
        // update the display
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        background.draw(g);
        character.draw(g);
        for (Platform platform : platforms) {
            platform.draw(g);
        }
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + score, 10, 25);
    }

    public void check() {
        for (Platform platform : platforms) {
            if (character.hitObj(platform)) {
                if (platform.getClass().equals(PoisonPlatform.class)) {
                    status.setText("You got POISONED :(");
                    playing = false;
                }
                return;
            }
        }
    }

    public boolean loadData() {
        try {
            FileReader fileReader = new FileReader("files/saved.txt");
            BufferedReader output = new BufferedReader(fileReader);
            String i = output.readLine();
            if (i == null || i.equals("NO DATA")) {
                return false;
            } else {
                String[] data = i.split(" ");
                character = new Character(400, 500);
                character.setPx(Integer.parseInt(data[1]));
                character.setPy(Integer.parseInt(data[2]));
            }
            int count = 1;
            platforms.clear();
            i = output.readLine();

            String[] data = i.split(" ");
            while (data[0].equals("Platform")) {
                if (data[3].equals("BrokenPlatform")) {
                    BrokenPlatform plat = new BrokenPlatform(
                            400,
                            500, count, Integer.parseInt(data[2]), false
                    );
                    plat.setPx(Integer.parseInt(data[1]));
                    platforms.add(plat);
                } else if (data[3].equals("MoneyPlatform")) {
                    MoneyPlatform plat = new MoneyPlatform(
                            400,
                            500, count, Integer.parseInt(data[2]), false
                    );
                    plat.setPx(Integer.parseInt(data[1]));
                    platforms.add(plat);
                } else if (data[3].equals("PoisonPlatform")) {
                    PoisonPlatform plat = new PoisonPlatform(
                            400,
                            500, count, Integer.parseInt(data[2]), false
                    );
                    plat.setPx(Integer.parseInt(data[1]));
                    platforms.add(plat);
                } else {
                    Platform plat = new Platform(
                            400,
                            500, count, Integer.parseInt(data[2]),
                            false
                    );
                    plat.setPx(Integer.parseInt(data[1]));
                    platforms.add(plat);
                }
                count++;
                if (count == 4) {
                    count = 1;
                }
                i = output.readLine();
                data = i.split(" ");
            }
            score = Integer.parseInt(data[1]);
            playing = true;
            status.setText("Running...");
            requestFocusInWindow();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public void storeData() {
        try {
            FileWriter fw = new FileWriter("files/saved.txt", false);
            BufferedWriter output = new BufferedWriter(fw);
            if (!playing) {
                output.write("NO DATA");
                output.close();
                return;
            }
            output.write("Character " + character.getPx() + " " + character.getPy());
            for (Platform p : platforms) {
                output.write(
                        "\nPlatform " + p.getPx() + " " + p.getPy() + " "
                                + p.getClass().getSimpleName()
                );
            }
            output.write("\nScore " + score);
            output.close();
        } catch (Exception exception) {
            System.out.println("Exception have been caught");
        }
    }

    public void reset() {
        platforms.clear();
        platforms.add(new Platform(400, 500, 1, 100, true));
        platforms.add(new Platform(400, 500, 2, 100, true));
        platforms.add(new Platform(400, 500, 3, 100, true));

        platforms.add(new Platform(400, 500, 1, 200, false));
        platforms.add(new PoisonPlatform(400, 500, 2, 200, false));
        platforms.add(new Platform(400, 500, 3, 200, false));

        platforms.add(new Platform(400, 500, 1, 300, false));
        platforms.add(new BrokenPlatform(400, 500, 2, 300, false));
        platforms.add(new PoisonPlatform(400, 500, 3, 300, false));

        platforms.add(new Platform(400, 500, 1, 400, false));
        platforms.add(new MoneyPlatform(400, 500, 2, 400, false));
        platforms.add(new Platform(400, 500, 3, 400, false));

        platforms.add(new PoisonPlatform(400, 500, 1, 500, false));
        platforms.add(new Platform(400, 500, 2, 500, false));
        platforms.add(new Platform(400, 500, 3, 500, false));

        character = new Character(400, 500);

        playing = true;
        status.setText("Running...");

        score = 0;
        requestFocusInWindow();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(400, 500);
    }
}

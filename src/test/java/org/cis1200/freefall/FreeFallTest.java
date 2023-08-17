package org.cis1200.freefall;

import org.junit.jupiter.api.*;

import javax.swing.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FreeFallTest {
    private GameCourt game;

    @BeforeEach
    public void setUp() {
        // We initialize a fresh ServerModel for each test
        final JLabel status = new JLabel("Running...");
        game = new GameCourt(status);
        game.reset();
    }

    @Test
    public void testLosingWhenCharacterGoingOutOfBoundsVertically() {
        Character character;
        for (int i = 0; i < 100; i++) {
            game.tick();
        }
        assertFalse(game.isPlaying());
        game.reset();
        character = game.getCharacter();
        character.setPy(1000);
        game.tick();
        assertFalse(game.isPlaying());
    }

    @Test
    public void testPlatformsLeavingScreen() {
        ArrayList<Platform> platforms = game.getPlatformsCopy();
        Platform p = platforms.get(0);
        Character character = game.getCharacter();

        for (int i = 0; i < 200; i++) {
            character.setPy(300);
            game.tick();
        }
        assertFalse(game.getPlatformsCopy().contains(p));
        // see if the platform has been replaced
        assertEquals(15, game.getPlatformsCopy().size());
    }

    @Test
    public void testDiesWhenOnTopOfPoison() {
        ArrayList<Platform> platforms = game.getPlatformsCopy();
        Platform poisonPlatform = platforms.get(4);
        Character character = game.getCharacter();
        character.setPy(159);
        character.setPx(poisonPlatform.getPx() - 39);
        assertTrue(game.isPlaying());
        game.tick();
        assertFalse(game.isPlaying());
    }

    @Test
    public void testScoreIncreasesWhenOnTopOfGoldPlatform() {
        ArrayList<Platform> platforms = game.getPlatformsCopy();
        Platform moneyPlatform = platforms.get(10);
        Character character = game.getCharacter();
        character.setPy(359);
        character.setPx(moneyPlatform.getPx() - 39);
        assertTrue(game.isPlaying());
        assertEquals(GameCourt.getScore(), 0);
        game.tick();
        assertEquals(GameCourt.getScore(), 1);
        game.tick();
        assertEquals(GameCourt.getScore(), 1);
    }

    @Test
    public void testFallsThroughBrokenPlatformAfterFewSeconds() {
        ArrayList<Platform> platforms = game.getPlatformsCopy();
        Platform moneyPlatform = platforms.get(7);
        Character character = game.getCharacter();
        character.setPy(259);
        character.setPx(moneyPlatform.getPx() - 39);
        assertTrue(game.isPlaying());
        assertEquals(character.getVy(), 2);
        for (int i = 0; i < 7; i++) {
            game.tick();
        }
        assertEquals(character.getVy(), -1);
        game.tick();
        assertEquals(character.getVy(), 2);
    }
}

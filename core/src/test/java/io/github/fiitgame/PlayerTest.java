package io.github.fiitgame;

import org.junit.jupiter.api.Test;

import javax.management.InvalidAttributeValueException;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {


    @Test
    void gainXpTest(){
        Player player = new Mage(false);
        player.gainXp(50);
        assertEquals(50, player.getXp());
        assertEquals(1, player.getLvl());

        assertThrows(IllegalArgumentException.class, () -> {
            player.gainXp(-10);
        });
    }


    @Test
    void LevelUpTest() throws IllegalArgumentException {
        Player player = new Mage(false);
        player.gainXp(150); // Should level up to level 2
        assertEquals(2, player.getLvl());
        assertEquals(50, player.getXp());
        assertEquals(108, player.getHealth());
        assertEquals(7, player.getdamage());

        player.gainXp(60);
        assertEquals(0, player.getXp());
        assertEquals(124, player.getHealth());
        assertEquals(11, player.getdamage());
        assertEquals(3, player.getLvl());
    }
}

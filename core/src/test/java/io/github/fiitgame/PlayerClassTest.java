package io.github.fiitgame;

import io.github.fiitgame.Player.PlayerClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerClassTest {

    @Test
    void values_containsMage() {
        PlayerClass[] values = PlayerClass.values();
        boolean found = false;
        for (PlayerClass v : values) {
            if (v == PlayerClass.MAGE) found = true;
        }
        assertTrue(found);
    }

    @Test
    void values_containsWarrior() {
        PlayerClass[] values = PlayerClass.values();
        boolean found = false;
        for (PlayerClass v : values) {
            if (v == PlayerClass.WARRIOR) found = true;
        }
        assertTrue(found);
    }

    @Test
    void values_exactlyTwo() {
        assertEquals(3, PlayerClass.values().length);
    }

    @Test
    void valueOf_mage() {
        assertEquals(PlayerClass.MAGE, PlayerClass.valueOf("MAGE"));
    }

    @Test
    void valueOf_warrior() {
        assertEquals(PlayerClass.WARRIOR, PlayerClass.valueOf("WARRIOR"));
    }

    @Test
    void valueOf_invalid_throws() {
        assertThrows(IllegalArgumentException.class, () -> PlayerClass.valueOf("ROGUE"));
    }

    @Test
    void mage_notEqualsWarrior() {
        assertNotEquals(PlayerClass.MAGE, PlayerClass.WARRIOR);
    }

    @Test
    void ordinal_mage_is0() {
        assertEquals(0, PlayerClass.MAGE.ordinal());
    }

    @Test
    void ordinal_warrior_is1() {
        assertEquals(1, PlayerClass.WARRIOR.ordinal());
    }

    @Test
    void name_mage() {
        assertEquals("MAGE", PlayerClass.MAGE.name());
    }

    @Test
    void name_warrior() {
        assertEquals("WARRIOR", PlayerClass.WARRIOR.name());
    }

}

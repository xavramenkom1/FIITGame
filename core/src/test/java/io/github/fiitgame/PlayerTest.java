package io.github.fiitgame;

import io.github.fiitgame.Player.Mage;
import io.github.fiitgame.Player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Mage(false);
    }

    // ===== Initial State ======

    @Test
    void initialHealth_is100() {
        assertEquals(100, player.getHealth());
    }

    @Test
    void initialDamage_is5() {
        assertEquals(5, player.getDamage());
    }

    @Test
    void initialLevel_is1() {
        assertEquals(1, player.getLvl());
    }

    @Test
    void initialXp_is0() {
        assertEquals(0, player.getXp());
    }

    @Test
    void initialNeededXp_is100() {
        assertEquals(100, player.getNeededXp());
    }

    @Test
    void initialMaxHealth_is100() {
        assertEquals(100f, player.getMaxHealth());
    }

    @Test
    void initialPosition_notNull() {
        assertNotNull(player.getPosition());
    }

    @Test
    void initialPosition_coordinates() {
        assertEquals(200f, player.getPosition().x);
        assertEquals(200f, player.getPosition().y);
    }

    // ====== Gain Xp ======

    @Test
    void gainXp_addsXp() {
        player.gainXp(50);
        assertEquals(50, player.getXp());
    }

    @Test
    void gainXp_noLevelUp_belowThreshold() {
        player.gainXp(99);
        assertEquals(1, player.getLvl());
        assertEquals(99, player.getXp());
    }

    @Test
    void gainXp_negative_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> player.gainXp(-1));
    }

    @Test
    void gainXp_zero_allowed() {
        assertDoesNotThrow(() -> player.gainXp(0));
        assertEquals(0, player.getXp());
    }

    @Test
    void gainXp_exactThreshold_levelsUp() {
        player.gainXp(100);
        assertEquals(2, player.getLvl());
    }

    // ====== Gain Xp lvl up ======

    @Test
    void levelUp_to2_xpCarryover() {
        player.gainXp(150);
        assertEquals(2, player.getLvl());
        assertEquals(50, player.getXp()); // 150 - 100 = 50
    }

    @Test
    void levelUp_to2_damageIncrease() {
        player.gainXp(100);
        // damage += 2 * lvl(1) => 5 + 2 = 7
        assertEquals(7, player.getDamage());
    }

    @Test
    void levelUp_to2_maxHealthIncrease() {
        player.gainXp(100);
        // maxHealth += 8 * lvl(1) => 100 + 8 = 108
        assertEquals(108f, player.getMaxHealth());
    }

    @Test
    void levelUp_to2_healthRestoredToMax() {
        player.gainXp(100);
        assertEquals(108, player.getHealth());
    }

    @Test
    void levelUp_to2_neededXpIncrease() {
        player.gainXp(100);
        // neededXp += 10 * lvl(1) => 100 + 10 = 110
        assertEquals(110, player.getNeededXp());
    }

    @Test
    void levelUp_to3_chain() {
        player.gainXp(150); // -> lvl 2, xp=50, neededXp=110
        player.gainXp(60);  // xp=110 >= 110 -> lvl 3
        assertEquals(3, player.getLvl());
        assertEquals(0, player.getXp());
        assertEquals(124, player.getHealth());
        assertEquals(11, player.getDamage());
    }

    @Test
    void levelUp_to3_neededXp() {
        player.gainXp(150);
        player.gainXp(60);
        // neededXp after lvl 2: 110 + 10*2 = 130
        assertEquals(130, player.getNeededXp());
    }

    // ======= Mage ======

    @Test
    void mage_initialMana_100() {
        Mage mage = (Mage) player;
        assertEquals(100, mage.getMana());
    }

    @Test
    void mage_mana_doesNotExceedMax() {
        Mage mage = (Mage) player;
        assertEquals(100, mage.getMana());
    }

    @Test
    void gainXp_largeAmount_multipleIndirectLevels() {
        player.gainXp(9999);
        // lvl 1 -> 2 at 100 xp (remaining 9899)
        assertEquals(2, player.getLvl());
    }
}

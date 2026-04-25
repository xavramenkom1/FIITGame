package io.github.fiitgame;

import com.badlogic.gdx.math.Rectangle;
import io.github.fiitgame.Enemy.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    static class TestEnemy extends Enemy {
        public TestEnemy(int health, int damage, int lvl) {
            super(health, damage, lvl);
        }
    }

    private TestEnemy enemy;

    @BeforeEach
    void setUp() {
        enemy = new TestEnemy(100, 10, 1);
    }

    // ===== takeDamage ======

    @Test
    void takeDamage_reducesHealth() {
        enemy.takeDamage(30);
        assertEquals(70, enemy.getHealth());
    }

    @Test
    void takeDamage_doesNotGoBelowZero() {
        enemy.takeDamage(200);
        assertEquals(0, enemy.getHealth());
    }

    @Test
    void takeDamage_exactlyToZero() {
        enemy.takeDamage(100);
        assertEquals(0, enemy.getHealth());
    }

    @Test
    void takeDamage_zeroDamage_noChange() {
        enemy.takeDamage(0);
        assertEquals(100, enemy.getHealth());
    }

    @Test
    void takeDamage_multiple_accumulates() {
        enemy.takeDamage(30);
        enemy.takeDamage(30);
        assertEquals(40, enemy.getHealth());
    }

    // ====== isDead ======

    @Test
    void isDead_falseWhenHealthy() {
        assertFalse(enemy.isDead());
    }

    @Test
    void isDead_trueWhenHealthZero() {
        enemy.takeDamage(100);
        assertTrue(enemy.isDead());
    }

    @Test
    void isDead_trueWhenOverkill() {
        enemy.takeDamage(9999);
        assertTrue(enemy.isDead());
    }

    @Test
    void isDead_falseWhenOneHpLeft() {
        enemy.takeDamage(99);
        assertFalse(enemy.isDead());
    }

    // ==== getHealth ======

    @Test
    void getHealthValue() {
        assertEquals(100, enemy.getHealth());
    }

    // ====== getDroppedXp ======

    @Test
    void getDroppedXp_defaultZeroForTestEnemy() {
        assertEquals(0, enemy.getDroppedXp());
    }

    // ====== getBounds ======

    @Test
    void getBounds_notNull() {
        assertNotNull(enemy.getBounds());
    }

    @Test
    void getBounds_correctSize() {
        Rectangle bounds = enemy.getBounds();
        assertEquals(32f, bounds.width);
        assertEquals(32f, bounds.height);
    }

    @Test
    void getBounds_initialPosition() {
        Rectangle bounds = enemy.getBounds();
        assertEquals(0f, bounds.x);
        assertEquals(0f, bounds.y);
    }

    // ====== getTexture ======

    @Test
    void getTexture_nullForTestConstructor() {
        assertNull(enemy.getTexture());
    }

    // ====== Constructor ======

    @Test
    void constructor_differentHealth() {
        TestEnemy e = new TestEnemy(50, 5, 2);
        assertEquals(50, e.getHealth());
    }

    @Test
    void constructor_lowHealth_oneHit() {
        TestEnemy e = new TestEnemy(1, 1, 1);
        assertFalse(e.isDead());
        e.takeDamage(1);
        assertTrue(e.isDead());
    }

    @Test
    void constructor_highLevel() {
        TestEnemy e = new TestEnemy(500, 50, 10);
        assertEquals(500, e.getHealth());
        assertFalse(e.isDead());
    }
}

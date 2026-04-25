package io.github.fiitgame;

import com.badlogic.gdx.math.Rectangle;
import io.github.fiitgame.Enemy.Enemy;
import io.github.fiitgame.Listeners.EventListener;
import io.github.fiitgame.Player.Mage;
import io.github.fiitgame.Projectiles.Projectile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EventListenerTest {

    static class StubEnemy extends Enemy {
        public StubEnemy(int health, int damage, int lvl) {
            super(health, damage, lvl);
        }
        public void setBounds(float x, float y, float w, float h) {
            this.bounds = new Rectangle(x, y, w, h);
        }
    }

    static class StubProjectile extends Projectile {
        public StubProjectile(float x, float y, float w, float h, int damage) {
            super(x, y, w, h, damage); // тестовый конструктор Projectile
        }
    }

    @BeforeEach
    void setUp() {
        new EventListener();
        Main.player = new Mage(false);
    }

    @Test
    void constructor_listsNotNull() {
        assertNotNull(EventListener.projectiles);
        assertNotNull(EventListener.enemies);
    }

    @Test
    void constructor_listsAreEmpty() {
        assertTrue(EventListener.projectiles.isEmpty());
        assertTrue(EventListener.enemies.isEmpty());
    }

    @Test
    void collisionCheck_emptyLists_noException() {
        assertDoesNotThrow(EventListener::projectileCollisionCheck);
    }

    @Test
    void collisionCheck_projectileOnly_nothingHappens() {
        EventListener.projectiles.add(new StubProjectile(0, 0, 10, 10, 5));
        EventListener.projectileCollisionCheck();
        assertEquals(1, EventListener.projectiles.size());
    }

    @Test
    void collisionCheck_enemyOnly_nothingHappens() {
        EventListener.enemies.add(new StubEnemy(100, 5, 1));
        EventListener.projectileCollisionCheck();
        assertEquals(1, EventListener.enemies.size());
        assertEquals(100, EventListener.enemies.get(0).getHealth());
    }

    @Test
    void collisionCheck_noCollision_bothRemain() {
        StubEnemy e = new StubEnemy(100, 5, 1);
        e.setBounds(500, 500, 32, 32);
        EventListener.enemies.add(e);
        EventListener.projectiles.add(new StubProjectile(0, 0, 10, 10, 20));
        EventListener.projectileCollisionCheck();
        assertEquals(1, EventListener.enemies.size());
        assertEquals(1, EventListener.projectiles.size());
    }

    @Test
    void collisionCheck_hit_projectileRemoved() {
        StubEnemy e = new StubEnemy(100, 5, 1);
        e.setBounds(0, 0, 32, 32);
        EventListener.enemies.add(e);
        EventListener.projectiles.add(new StubProjectile(5, 5, 10, 10, 20));
        EventListener.projectileCollisionCheck();
        assertTrue(EventListener.projectiles.isEmpty());
    }

    @Test
    void collisionCheck_hit_damageDealt() {
        StubEnemy e = new StubEnemy(100, 5, 1);
        e.setBounds(0, 0, 32, 32);
        EventListener.enemies.add(e);
        EventListener.projectiles.add(new StubProjectile(5, 5, 10, 10, 30));
        EventListener.projectileCollisionCheck();
        assertEquals(70, EventListener.enemies.get(0).getHealth());
    }

    @Test
    void collisionCheck_killingHit_enemyRemoved() {
        StubEnemy e = new StubEnemy(10, 5, 1);
        e.setBounds(0, 0, 32, 32);
        EventListener.enemies.add(e);
        EventListener.projectiles.add(new StubProjectile(5, 5, 10, 10, 100));
        EventListener.projectileCollisionCheck();
        assertTrue(EventListener.enemies.isEmpty());
        assertTrue(EventListener.projectiles.isEmpty());
    }

    @Test
    void collisionCheck_killingHit_noException() {
        StubEnemy e = new StubEnemy(10, 5, 1);
        e.setBounds(0, 0, 32, 32);
        EventListener.enemies.add(e);
        EventListener.projectiles.add(new StubProjectile(5, 5, 10, 10, 100));
        assertDoesNotThrow(EventListener::projectileCollisionCheck);
    }

    @Test
    void collisionCheck_multipleEnemies_onlyFirstHit() {
        StubEnemy e1 = new StubEnemy(100, 5, 1);
        e1.setBounds(0, 0, 32, 32);
        StubEnemy e2 = new StubEnemy(100, 5, 1);
        e2.setBounds(500, 500, 32, 32);
        EventListener.enemies.add(e1);
        EventListener.enemies.add(e2);
        EventListener.projectiles.add(new StubProjectile(5, 5, 10, 10, 40));
        EventListener.projectileCollisionCheck();
        assertEquals(60, EventListener.enemies.get(0).getHealth());
        assertEquals(100, EventListener.enemies.get(1).getHealth());
    }

    @Test
    void collisionCheck_multipleProjectiles_bothHit() {
        StubEnemy e = new StubEnemy(100, 5, 1);
        e.setBounds(0, 0, 32, 32);
        EventListener.enemies.add(e);
        EventListener.projectiles.add(new StubProjectile(5, 5, 10, 10, 20));
        EventListener.projectiles.add(new StubProjectile(5, 5, 10, 10, 20));
        EventListener.projectileCollisionCheck();
        assertEquals(60, EventListener.enemies.get(0).getHealth());
        assertTrue(EventListener.projectiles.isEmpty());
    }
}

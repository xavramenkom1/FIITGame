package io.github.fiitgame;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {

    @Test
    void TakeDamageTest(){
        Enemy e = new Enemy(100, 10, 1);
        e.takeDamage(30);
        assertEquals(70, e.getHealth());
        e.takeDamage(100);
        assertEquals(0, e.getHealth());
    }
    @Test
    void IsDeadTest(){
        Enemy e = new Enemy(100, 10, 1);
        e.takeDamage(100);
        assertTrue(e.isDead());
    }

}

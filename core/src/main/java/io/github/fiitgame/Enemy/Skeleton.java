package io.github.fiitgame.Enemy;

/**
 *
 * Skeleton class, one of the enemies in the game.
 * It has higher stats than Slime.
 * It drops more XP than Slime.
 *
 */
public class Skeleton extends Enemy{

    public Skeleton(String texturePath, int health, int damage, int lvl) {
        super(texturePath, health, damage, lvl);
        this.speed = 50;
        setDamage(damage);
        droppedXp = 15 + (lvl - 1) * 5;
    }

}

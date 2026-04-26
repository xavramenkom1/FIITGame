package io.github.fiitgame.Enemy;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Slime class. One of the enemies in the game.
 * It has lower stats than skeleton.
 * It drops less XP than Skeleton.
 *
 *
 */
public class Slime extends Enemy implements EnemyInterface {



    public Slime(String texturePath, int health, int damage, int lvl) {
        super(texturePath, health, damage, lvl);
        this.speed = 30;
        droppedXp = 5 + (lvl - 1) * 2;
    }

    /**
     * move function. Slimes wobbles :3
     * @param delta time
     */
     @Override
     protected void move(float delta) {
        super.move(delta);
        position.x += (float) ((Math.random() - 0.5) * speed * delta * 3);
        position.y += (float) ((Math.random() - 0.5) * speed * delta * 3);
     }
}

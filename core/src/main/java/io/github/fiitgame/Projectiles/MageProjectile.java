package io.github.fiitgame.Projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Mage projectile that accelerates over time.
 * It starts with a lower speed, but becomes faster the longer it is active.
 */

public class MageProjectile extends Projectile {

    private float acceleration;

    /**
     * Main constructor
     * @param texture texture
     * @param position pos
     * @param direction dir
     * @param damage dmg
     */
    public MageProjectile(Texture texture, Vector2 position, Vector2 direction, int damage){
        super(texture, position, direction, damage);
        this.speed = 130;
        acceleration = 0.01f;
        sprite.setColor(0, 1f, 1f, 1f);
        setActive(true);
    }

    /**
     * Update method that calculates all logic depending on time.
     * increases the speed of the projectile over time.
     * @param delta timme
     */
    @Override
    public void update(float delta){
        super.update(delta);
        speed += acceleration * speed;
    }
}

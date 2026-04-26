package io.github.fiitgame.Projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Arrow that is faster than the mage projectile but has a limited range.
 * It negatively accelerates over time and disappears when it reaches a certain speed threshold.
 */

public class ArcherProjectile extends Projectile {

    private float acceleration;

    /**
     * Main constructor
     * @param texture texture
     * @param position pos
     * @param direction dir
     * @param damage dmg
     */
    public ArcherProjectile(Texture texture, Vector2 position, Vector2 direction, int damage){
        super(texture, position, direction, damage);
        this.speed = 700;
        acceleration = 0.01f;
        sprite.setColor(0, 1f, 1f, 1f);
        setActive(true);
    }

    /**
     * Method that calculates logic and negatively accelerates arrow
     * @param delta time
     */
    @Override
    public void update(float delta){
        super.update(delta);
        speed -= acceleration * speed;
        if(speed <= 100){
            setActive(false);
        }
    }
}

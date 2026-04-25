package io.github.fiitgame.Projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class MageProjectile extends Projectile {

    private float acceleration;

    public MageProjectile(Texture texture, Vector2 position, Vector2 direction, int damage){
        super(texture, position, direction, damage);
        this.speed = 130;
        acceleration = 0.01f;
        sprite.setColor(0, 1f, 1f, 1f);
    }
    @Override
    public void update(float delta){
        super.update(delta);
        speed += acceleration * speed;
    }
}

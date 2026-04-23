package io.github.fiitgame.Projectiles;

import com.badlogic.gdx.math.Vector2;

public class MageProjectile extends Projectile {

    private float acceleration;

    public MageProjectile(String texturePath, Vector2 position, Vector2 direction, int damage){
        super(texturePath, position, direction, damage);
        this.speed = 130;
        acceleration = 0.01f;
    }
    @Override
    public void update(float delta){
        super.update(delta);
        speed += acceleration * speed;
    }
}

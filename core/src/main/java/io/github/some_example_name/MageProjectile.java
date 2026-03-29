package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class MageProjectile extends Projectile{

    private float acceleration;

    public MageProjectile(String texturePath, Vector2 position, Vector2 direction, int damage){
        super(texturePath, position, direction, damage);
        this.speed = 200;
        acceleration = 0.02f;
    }

    public void update(float delta){
        super.update(delta);
        speed += acceleration * speed;
    }
}

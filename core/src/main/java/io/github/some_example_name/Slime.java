package io.github.some_example_name;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Slime extends Enemy {



    public Slime(String texturePath, int health, int damage, int lvl) {
        super(texturePath, health, damage, lvl);
        this.speed = 30;
        this.bounds = new Rectangle(x, y, getTexture().getWidth(), getTexture().getHeight() / 2);
    }

     @Override
     void move(float delta) {
            x += (float) ((Math.random() - 0.5) * speed * delta);
            y += (float) ((Math.random() - 0.5) * speed * delta);
     }
}

package io.github.fiitgame.Enemy;


import com.badlogic.gdx.math.Rectangle;

public class Slime extends Enemy {



    public Slime(String texturePath, int health, int damage, int lvl) {
        super(texturePath, health, damage, lvl);
        this.speed = 30;
        this.bounds = new Rectangle(position.x, position.y, getTexture().getWidth(), getTexture().getHeight() / 2);
        droppedXp = 5 + (lvl - 1) * 2;
    }

     @Override
     void move(float delta) {
        super.move(delta);
        position.x += (float) ((Math.random() - 0.5) * speed * delta * 3);
        position.y += (float) ((Math.random() - 0.5) * speed * delta * 3);
     }
}

package io.github.fiitgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Player {

    protected Vector2 position;
    protected float speed;

    protected int damage;
    protected int health;
    protected int maxHealth;
    protected int lvl;
    protected int xp;
    protected int neededXp;

    protected boolean flip;

    protected Sprite sprite;

    public Player(boolean initialiseGraphics) {
        if (initialiseGraphics) {
            // текстуру задаёт уже конкретный класс
        }

        maxHealth = 100;
        health = 100;
        damage = 5;
        lvl = 1;
        xp = 0;
        neededXp = 100;

        this.speed = 70;
        this.position = new Vector2(200, 200);
        flip = false;
    }

    public void update(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed * delta;
            flip = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed * delta;
            flip = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W))
            position.y += speed * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.S))
            position.y -= speed * delta;

        if (sprite != null) {
            if (!flip && !sprite.isFlipX()) {
                sprite.flip(true, false);
            } else if (flip && sprite.isFlipX()) {
                sprite.flip(true, false);
            }
        }
    }

    public void render(SpriteBatch batch) {
        if (sprite != null) {
            batch.draw(sprite, position.x, position.y);
        }
    };

    public abstract Projectile attack() throws AttackException;

    public Vector2 getPosition() {
        return position;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    private void levelUp() {
        damage += 2 * lvl;
        maxHealth += 8 * lvl;
        health = maxHealth;
        xp = xp - neededXp;
        neededXp += 10 * lvl;

        lvl++;
    }

    public void gainXp(int droppedXp) {
        if (droppedXp < 0) throw new IllegalArgumentException("Xp cant be <0");

        xp += droppedXp;
        if (xp >= neededXp) {
            levelUp();
        }
    }

    public int getXp() {
        return xp;
    }
    public int getLvl(){
        return lvl;
    }
    public int getNeededXp() {
        return neededXp;
    }
}

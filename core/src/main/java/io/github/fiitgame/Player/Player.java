package io.github.fiitgame.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.github.fiitgame.Exceptions.AttackException;
import io.github.fiitgame.Projectiles.Projectile;

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
    protected Rectangle bounds;

    protected float invulnerabilityTimer = 0f;
    protected static final float invulnerabilityDur = 1.0f; // 1 sec invul
    protected boolean isInvulnerable = false;

    public Player() {

        lvl = 1;
        xp = 0;
        neededXp = 100;
        this.position = new Vector2(200, 200);
        this.bounds = new Rectangle(position.x, position.y, 32, 32);
        flip = false;
    }

    public void update(float delta) {
        handleMovementInput(delta);
        handleAttackInput();

        bounds.setPosition(position.x, position.y);

        if (isInvulnerable) {
            invulnerabilityTimer -= delta;
            if (invulnerabilityTimer <= 0) {
                isInvulnerable = false;
            }
        }

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
            if (!isInvulnerable || (int)(invulnerabilityTimer * 10) % 2 == 0) {
                batch.draw(sprite, position.x, position.y);
            }
        }
    };

    protected void handleMovementInput(float delta){
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
    }

    protected abstract void handleAttackInput();

    public abstract Projectile attack() throws AttackException;

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

    public void takeDamage(int damage) {
        if (isInvulnerable) {
            return;
        }

        health -= damage;
        if (health < 0) {
            health = 0;
        }

        isInvulnerable = true;
        invulnerabilityTimer = invulnerabilityDur;
    }

    public boolean isDead() { return health <= 0;}
    public int getXp() {
        return xp;
    }
    public int getLvl(){
        return lvl;
    }
    public int getNeededXp() {
        return neededXp;
    }
    public Vector2 getPosition() {
        return position;
    }
    public int getDamage() {
        return damage;
    }
    public int getHealth() {
        return health;
    }
    public float getMaxHealth() { return maxHealth; }
    public Rectangle getBounds() { return bounds; }
}

package io.github.fiitgame.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.github.fiitgame.Exceptions.AttackException;
import io.github.fiitgame.Projectiles.Projectile;


/**
 * Abstract player class. Has functionalit for inheritor classes
 * to move, attack, gain xp and level up. Also has invulnerability frames after taking damage.
 */

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
    protected static final float invulnerabilityDur = 0.5f;
    protected boolean isInvulnerable = false;

    /**
     * Main constructor
     */
    public Player() {

        lvl = 1;
        xp = 0;
        neededXp = 100;
        this.position = new Vector2(200, 200);
        this.bounds = new Rectangle(position.x, position.y, 16, 28);
        flip = false;
    }

    /**
     * Update function that calculates player logic and reads all inputs
     * @param delta time
     */
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

    /**
     * Render function thatt renders player graphics
     * @param batch batch
     */
    public void render(SpriteBatch batch) {
        if (sprite != null) {
            if (!isInvulnerable || (int)(invulnerabilityTimer * 10) % 2 == 0) {
                batch.draw(sprite, position.x, position.y);
            }
        }
    };

    /**
     * Reads pressed buttons and moves player accordingly.
     * Flips player texture when moving left or right.
     * @param delta time
     */
    protected void handleMovementInput(float delta){
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed * delta;
            flip = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed * delta;
            flip = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W))
            position.y += speed * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.S))
            position.y -= speed * delta;
    }

    /**
     * Abstract mathod for attack input reading. Each player class has different attack logic, so this is implemented in inheritor classes.
     */
    protected abstract void handleAttackInput();

    /**
     * Attack method. Different for each class
     * @return returns projectile that player throws
     * @throws AttackException exception for cooldowns, not enough mana, etc. depending on player class
     */
    public abstract Projectile attack() throws AttackException;


    /**
     * Function that applies level up bonuses to player stats and increases level. Called when player has enough xp to level up.
     */

    protected void levelUp() {
        damage += 2 * lvl;
        maxHealth += 8 * lvl;
        health = maxHealth;
        xp = xp - neededXp;
        neededXp += 10 * lvl;

        lvl++;
    }
    /**
     * Function that adds xp to player and checks if player has enough xp to level up.
     * @param droppedXp xp that dropped enemy and has to be aplied to player
     */
    public void gainXp(int droppedXp) {
        if (droppedXp < 0) throw new IllegalArgumentException("Xp cant be <0");

        xp += droppedXp;
        if (xp >= neededXp) {
            levelUp();
        }
    }

    /**
     * Applies damage to player
     * @param damage ammount of damage that has to be aplied
     */
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

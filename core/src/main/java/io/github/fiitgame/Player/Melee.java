package io.github.fiitgame.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.fiitgame.Exceptions.CoolDownException;
import io.github.fiitgame.Exceptions.MeleeException;
import io.github.fiitgame.Listeners.EventListener;
import io.github.fiitgame.Projectiles.MeleeProjectile;
import io.github.fiitgame.Projectiles.Projectile;

import static io.github.fiitgame.Listeners.EventListener.projectiles;

/**
 * Melee player class. Has close range attacks and high health, but low damage and speed.
 * Can attack by left-clicking, which creates a melee projectile that damages enemies in a small area around the player.
 */


public class Melee extends Player {

    private float attackRange;
    private float attackCooldown;
    private float cooldownTimer;

    /**
     * MMain constructor. Has highest speed among all classes and more health that other classes, but lower damage.
     * Can attack by left-clicking, which creates a melee projectile that damages enemies in a small area around the player.
     * @param initialiseGraphics for testing purposes
     */
    public Melee(boolean initialiseGraphics) {
        super();

        health = 100;
        maxHealth = 100;
        damage = 15;
        speed = 85f;

        if (initialiseGraphics) {
            sprite = new Sprite(new Texture("textures/Player/melle-texture.png"));
        }

        attackRange = 40f;
        attackCooldown = 0.5f;
        cooldownTimer = 0f;

    }

    /**
     * Update method that calculates logic depending on time
     * @param delta time
     */
    @Override
    public void update(float delta) {
        super.update(delta);

        if (cooldownTimer > 0) {
            cooldownTimer -= delta;
        }
    }

    /**
     * Function to handle attack input.
     * If left mouse button is pressed, it tries to attack and add the Melee (slash) projectile to projectiles.
     */

    @Override
    protected void handleAttackInput() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            try {
                Projectile proj = attack();
                projectiles.add(proj);
            } catch (CoolDownException e) {

            }
        }
    }

    /**
     * Attack function for close ranges
     * @return returns melee projectile that lives for 0.2 sec
     * @throws CoolDownException Being thrown if attack is on cooldown
     */
    public Projectile attack() throws CoolDownException {

        if (cooldownTimer > 0) {
            throw new CoolDownException("Cooldown");
        }

        cooldownTimer = attackCooldown;

        return new MeleeProjectile(
            new Texture("textures/projectiles/mage-projectile.png"),
            this,
            attackRange,
            damage
        );
    }
}

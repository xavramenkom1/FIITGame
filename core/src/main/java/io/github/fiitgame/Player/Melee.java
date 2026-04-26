package io.github.fiitgame.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.fiitgame.Exceptions.MeleeException;
import io.github.fiitgame.Listeners.EventListener;
import io.github.fiitgame.Projectiles.MeleeProjectile;
import io.github.fiitgame.Projectiles.Projectile;

import static io.github.fiitgame.Listeners.EventListener.projectiles;

public class Melee extends Player {

    private float attackRange;
    private float attackCooldown;
    private float cooldownTimer;

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

    @Override
    public void update(float delta) {
        super.update(delta);

        if (cooldownTimer > 0) {
            cooldownTimer -= delta;
        }
    }

    @Override
    protected void handleAttackInput() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            try {
                Projectile proj = attack();
                projectiles.add(proj);
            } catch (MeleeException e) {
                // todo cooldown
            }
        }
    }
    public Projectile attack() throws MeleeException {

        if (cooldownTimer > 0) {
            throw new MeleeException("Cooldown");
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

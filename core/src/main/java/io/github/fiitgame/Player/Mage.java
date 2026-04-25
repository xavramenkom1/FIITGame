package io.github.fiitgame.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.github.fiitgame.Exceptions.AttackException;
import io.github.fiitgame.Listeners.EventListener;
import io.github.fiitgame.Main;
import io.github.fiitgame.Projectiles.MageProjectile;
import io.github.fiitgame.Exceptions.NoManaException;
import io.github.fiitgame.Projectiles.Projectile;

import static io.github.fiitgame.Main.assets;

public class Mage extends Player {

    private float mana;
    private float maxMana;
    private float manaRegeneration;

    public Mage(boolean initialiseGraphics) {
        super();

        health = 70;
        maxHealth = 70;
        damage = 12;
        speed = 65f;

        if (initialiseGraphics) {
            sprite = new Sprite(assets.get("textures/Player/mage-texture.png", Texture.class));
        }
        maxMana = 100f;
        mana = 100f;
        manaRegeneration = 12f;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        mana += manaRegeneration * delta;
        mana = Math.min(mana, maxMana);
    }

    public Projectile attack() throws NoManaException {
        float cost = 20;

        if (mana < cost) throw new NoManaException("No mana");
        mana -= cost;

        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

        Vector2 direction = new Vector2(mouseX - position.x, mouseY - position.y);
        direction.nor();

        return new MageProjectile(
            assets.get("textures/projectiles/mage-projectile.png", Texture.class),
            position,
            direction,
            damage
        );
    }

    @Override
    protected void handleAttackInput() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            try {
                EventListener.projectiles.add(attack());
            } catch (AttackException e) {
                System.out.println("Not enough mana!");
            }
        }
    }
    public int getMana() {
        return (int) mana;
    }

    public float getMaxMana() {
        return maxMana;
    }
}

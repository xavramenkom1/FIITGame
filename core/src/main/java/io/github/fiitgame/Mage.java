package io.github.fiitgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Mage extends Player {

    private float mana;
    private float maxMana;
    private float manaRegeneration;

    public Mage(boolean initialiseGraphics) {
        super(initialiseGraphics);

        if (initialiseGraphics) {
            sprite = new Sprite(new Texture("textures/Player/mage-skin.png"));
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
            "textures/projectiles/mage-projectile.png",
            position,
            direction,
            damage
        );
    }


    public int getMana() {
        return (int) mana;
    }
}

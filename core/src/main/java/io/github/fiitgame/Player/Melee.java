package io.github.fiitgame.Player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import io.github.fiitgame.Exceptions.MeleeException;
import io.github.fiitgame.Projectiles.Projectile;

public class Melee extends Player {

    private float attackRange;
    private float attackCooldown;
    private float cooldownTimer;

    public Melee(boolean initialiseGraphics) {
        super(initialiseGraphics);

        if (initialiseGraphics) {
            sprite = new Sprite(new Texture("textures/Player/melee-skin.png"));
        }

        attackRange = 40f;
        attackCooldown = 0.5f; // полсекунды
        cooldownTimer = 0f;

        damage = 10; // сильнее мага
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
        // Todo
    }

    public Projectile attack() throws MeleeException {
        if (cooldownTimer > 0) return null;

        cooldownTimer = attackCooldown;

        // направление удара (куда смотрит игрок)
        Vector2 direction = new Vector2(flip ? -1 : 1, 0);

        // позиция удара перед игроком
        Vector2 attackPos = new Vector2(
            position.x + direction.x * attackRange,
            position.y
        );
        return new Projectile(
            "textures/projectiles/melee-hit.png",
            attackPos,
            direction,
            damage
        );

//        return new MeleeProjectile(
//            "textures/projectiles/melee-hit.png",
//            attackPos,
//            direction,
//            damage
//        );
    }
}

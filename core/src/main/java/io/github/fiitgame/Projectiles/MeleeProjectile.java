package io.github.fiitgame.Projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import io.github.fiitgame.Enemy.Enemy;
import io.github.fiitgame.Player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Melee projectile, that is spawned neat player and represents "melee slash".
 * It has very short lifetime and it doesn't move,
 * but it can impact multiple enemies if they are close enough to player.
 */

public class MeleeProjectile extends Projectile {

    private float lifetime = 0.2f;
    private float timer = 0f;

    private Player player;
    private float distanceFromPlayer;

    /**
     * Impacted enemies so it doesnt damage one enemy multiple times during its lifetime.
     */

    private List<Enemy> impactedEnemies;

    public MeleeProjectile(Texture texture, Player player, float distance, int damage) {
        super(texture, new Vector2(), new Vector2(), damage);

        setDirection(new Vector2(Gdx.input.getX() - player.getPosition().x, Gdx.graphics.getHeight() - Gdx.input.getY() - player.getPosition().y).nor());

        this.player = player;
        this.distanceFromPlayer = distance;

        setSize(3f);
        setActive(true);


    }

    /**
     * Update method that calculates all logic depending on time.
     * It updates the position of the projectile to be in front of the player, and checks if its lifetime has expired.
     * @param delta timme
     */
    @Override
    public void update(float delta) {
        timer += delta;

        Vector2 pos = player.getPosition().cpy().add(getDirection().scl(distanceFromPlayer));

        sprite.setPosition(pos.x, pos.y);
        setPosition(pos);

        if (timer >= lifetime) {
            setActive(false);
        }
    }

    /**
     * Function to add impacted enemy so it doesnt damage one enemy multiple times during its lifetime.
     * @param enemy enemy that has to be added
     */
    public void addImpactedEnemy(Enemy enemy){
        if (impactedEnemies == null) {
            impactedEnemies = new ArrayList<>();
        }
        impactedEnemies.add(enemy);
    }

    /**
     * Checks if the enemy can by damaged depending on if it was already impacted by this projectile during its lifetime.
     * @param enemy enemmy to check
     * @return return if the enemy can be impacted or not
     */
    public boolean enemyCanBeImpacted(Enemy enemy){
        if(impactedEnemies == null) return true;
        for(Enemy e : impactedEnemies){
            if(e == enemy){
                return false;
            }
        }
        return true;
    }
}

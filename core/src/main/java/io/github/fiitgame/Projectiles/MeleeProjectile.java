package io.github.fiitgame.Projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import io.github.fiitgame.Enemy.Enemy;
import io.github.fiitgame.Player.Player;

import java.util.ArrayList;
import java.util.List;

public class MeleeProjectile extends Projectile {

    private float lifetime = 0.2f;
    private float timer = 0f;

    private Player player;
    private float distanceFromPlayer;

    private List<Enemy> impactedEnemies;

    public MeleeProjectile(Texture texture, Player player, float distance, int damage) {
        super(texture, new Vector2(), new Vector2(), damage);

        setDirection(new Vector2(Gdx.input.getX() - player.getPosition().x, Gdx.graphics.getHeight() - Gdx.input.getY() - player.getPosition().y).nor());

        this.player = player;
        this.distanceFromPlayer = distance;

        setSize(3f);
        setActive(true);


    }

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
    public void addImpactedEnemy(Enemy enemy){
        if (impactedEnemies == null) {
            impactedEnemies = new ArrayList<>();
        }
        impactedEnemies.add(enemy);
    }
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

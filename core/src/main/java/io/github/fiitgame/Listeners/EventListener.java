package io.github.fiitgame.Listeners;

import com.badlogic.gdx.math.Vector2;
import io.github.fiitgame.Enemy.Enemy;
import io.github.fiitgame.Projectiles.Projectile;

import java.util.ArrayList;
import java.util.List;

import static io.github.fiitgame.Main.player;

public class EventListener {

    public static List<Projectile> projectiles;
    public static List<Enemy> enemies;

    public EventListener() {
        projectiles = new ArrayList<>();
        enemies = new ArrayList<>();
    }

    public static void projectileCollisionCheck() {
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile projectile = projectiles.get(i);
            for (int j = 0; j < enemies.size(); j++) {
                Enemy enemy = enemies.get(j);

                if (projectile.collides(enemy.getBounds())) {
                    enemy.takeDamage(projectile.getDamage());

                    if (enemy.isDead()) {
                        enemies.remove(j);
                        player.gainXp(enemy.getDroppedXp());
                    }
                    projectiles.remove(i);
                    i--;
                    break;
                }
            }
        }
    }

}

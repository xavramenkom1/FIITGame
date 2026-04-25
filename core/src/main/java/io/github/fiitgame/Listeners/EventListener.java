package io.github.fiitgame.Listeners;

import com.badlogic.gdx.math.Vector2;
import io.github.fiitgame.Enemy.Enemy;
import io.github.fiitgame.Player.Player;
import io.github.fiitgame.Projectiles.Projectile;

import java.util.ArrayList;
import java.util.List;

import static io.github.fiitgame.Screens.GameScreen.player;

public class EventListener {


    public static List<Projectile> projectiles;
    public static List<Projectile> enemyProjectiles;
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
    public static void enemyPlayerCollisionCheck() {
        if (player == null || player.isDead()) {
            return;
        }

        for (Enemy enemy : enemies) {
            if (player.getBounds().overlaps(enemy.getBounds())) {
                player.takeDamage(enemy.getDamage());

                if (player.isDead()) {
                    System.out.println("Player died!");
                }
            }
        }
    }
    public static void enemyProjectileCollisionCheck() {
//        if (player == null || player.isDead()) {
//            return;
//        }
//
//        for (int i = 0; i < enemyProjectiles.size(); i++) {
//            Projectile projectile = enemyProjectiles.get(i);
//
//            if (projectile.collides(player.getBounds())) {
//                player.takeDamage(projectile.getDamage());
//                enemyProjectiles.remove(i);
//                i--;
//            }
//        }
    }

}

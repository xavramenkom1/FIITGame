package io.github.fiitgame.Listeners;

import com.badlogic.gdx.math.Vector2;
import io.github.fiitgame.Enemy.Enemy;
import io.github.fiitgame.Player.Melee;
import io.github.fiitgame.Player.Player;
import io.github.fiitgame.Projectiles.MeleeProjectile;
import io.github.fiitgame.Projectiles.Projectile;

import java.util.ArrayList;
import java.util.List;

import static io.github.fiitgame.Screens.GameScreen.player;

/**
 * Main listener class that calculates the whole collision logic in game.
 */

public class EventListener {


    public static List<Projectile> projectiles;
    public static List<Enemy> enemies;

    public EventListener() {
        projectiles = new ArrayList<>();
        enemies = new ArrayList<>();
    }

    /**
     * Method, that checks for collisions between projectiles and enemies.
     * If a collision is detected, the enemy takes damage and the projectile is removed from the game.
     * If the enemy's health drops to zero or below, it is removed from the game and the player gains experience points.
     *
     */
    public static void projectileCollisionCheck() {
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile projectile = projectiles.get(i);
            for (int j = 0; j < enemies.size(); j++) {
                Enemy enemy = enemies.get(j);

                if(projectile instanceof MeleeProjectile meleeProj){
                    if(!meleeProj.enemyCanBeImpacted(enemy)){
                        continue;
                    }
                }

                if (projectile.collides(enemy.getBounds())) {
                    enemy.takeDamage(projectile.getDamage());
                    if(projectile instanceof MeleeProjectile meleeProj){
                        meleeProj.addImpactedEnemy(enemy);
                    }

                    if (enemy.isDead()) {
                        enemies.remove(j);
                        player.gainXp(enemy.getDroppedXp());
                    }
                    if(!(projectile instanceof MeleeProjectile)){
                        projectiles.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Method that checks for collisions between the player and enemies.
     * If a collision is detected, the player takes damage from the enemy.
     */

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

    /**
     * Method that checks for non-active projectiles and deletes all of their kind
     */
    public static void checkActiveProjectiles(){
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile projectile = projectiles.get(i);
            if(!projectile.isActive()){
                projectiles.remove(i);
                i--;
            }
        }
    }

}

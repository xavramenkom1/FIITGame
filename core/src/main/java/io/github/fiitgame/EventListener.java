package io.github.fiitgame;

import com.badlogic.gdx.math.Vector2;

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

    public static void projectileCollisionCheck(){
        for(int i = 0; i < projectiles.size(); i++){
            Projectile projectile = projectiles.get(i);
            for(int j = 0; j < enemies.size(); j++){
                Enemy enemy = enemies.get(j);

                if(projectile.collides(enemy.getBounds())){
                    enemy.takeDamage(projectile.getDamage());
                    projectile.setPosition(new Vector2(-100, -100));
                    projectiles.remove(i);
                    i--;

                    if(enemy.isDead()){
                        enemies.remove(j);
                        player.gainXp(enemy.getDroppedXp());
                    }
                    break;
                }
            }
        }

    }


}

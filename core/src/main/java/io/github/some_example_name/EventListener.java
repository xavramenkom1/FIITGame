package io.github.some_example_name;

import com.badlogic.gdx.math.Vector2;

import java.util.List;

import static io.github.some_example_name.Main.player;

public class EventListener {

    public static void projectileCollisionCheck(List<Projectile> projectiles, List<Enemy> enemies){
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

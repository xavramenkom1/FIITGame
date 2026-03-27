package io.github.some_example_name;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    Player player;
    SpriteBatch spriteBatch;

    List<Projectile> projectiles = new ArrayList<Projectile>();
    List<Enemy> enemies = new ArrayList<Enemy>();

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        player = new Player();
        enemies.add(new Slime("textures/Enemies/slime.png", 30, 5, 1));
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1, true);
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            projectiles.add(player.attack());
        }
        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)){
            enemies.add(new Slime("textures/Enemies/slime.png", 30, 5, 1));
        }
        float delta = Gdx.graphics.getDeltaTime();

        player.update(delta);
        spriteBatch.begin();
        player.render(spriteBatch);

        for(Enemy enemy :  enemies){
            enemy.update(delta);
            enemy.render(spriteBatch);
        }

        for(Projectile projectile : projectiles){
            projectile.update(delta);
            projectile.render(spriteBatch);
        }
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
                        j--;
                        player.gainXp(enemy.getDroppedXp());
                    }
                    break;
                }
            }
        }
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}

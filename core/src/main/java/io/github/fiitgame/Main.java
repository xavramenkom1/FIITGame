package io.github.fiitgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.fiitgame.Enemy.Enemy;
import io.github.fiitgame.Enemy.Slime;
import io.github.fiitgame.Exceptions.AttackException;
import io.github.fiitgame.Listeners.EventListener;
import io.github.fiitgame.Player.Mage;
import io.github.fiitgame.Player.Player;
import io.github.fiitgame.Projectiles.Projectile;

import static io.github.fiitgame.Listeners.EventListener.*;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public static Player player;
    SpriteBatch spriteBatch;
    EventListener eventListener;
    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        player = new Mage(true);
        eventListener = new EventListener();
        enemies.add(new Slime("textures/Enemies/slime.png", 30, 5, 1));
    }

    @Override
    public void render() {
        ScreenUtils.clear(255, 255, 255, 1, true);
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            try{
                projectiles.add(player.attack());
            }
            catch (AttackException e){
                System.out.println("Not enough mana!");
            }
        }
        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)){
            enemies.add(new Slime("textures/Enemies/slime.png", 30, 5, 1));
        }
        float delta = Gdx.graphics.getDeltaTime();

        // Player
        player.update(delta);
        spriteBatch.begin();
        player.render(spriteBatch);

        // Enemies

        for(Enemy enemy : enemies){
            enemy.update(delta);
            enemy.render(spriteBatch);
        }

        // Projectiles

        for(Projectile projectile : projectiles){
            projectile.update(delta);
            projectile.render(spriteBatch);
        }
        projectileCollisionCheck();

        spriteBatch.end();

        // UI
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }
}

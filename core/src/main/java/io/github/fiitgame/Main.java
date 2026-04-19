package io.github.fiitgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

import static io.github.fiitgame.EventListener.*;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    static Player player;
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
            catch (NoManaException e){
                System.out.println("Not enough mana!");
            }
        }
        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)){
            enemies.add(new Slime("textures/Enemies/slime.png", 30, 5, 1));
        }
        float delta = Gdx.graphics.getDeltaTime();

        // Player
        System.out.println(String.format("%d/%d", player.getMana(), player.getMaxMana()));
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

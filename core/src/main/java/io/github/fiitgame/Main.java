package io.github.fiitgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.fiitgame.Enemy.Enemy;
import io.github.fiitgame.Enemy.Slime;
import io.github.fiitgame.Exceptions.AttackException;
import io.github.fiitgame.Listeners.EventListener;
import io.github.fiitgame.Player.Mage;
import io.github.fiitgame.Player.Player;
import io.github.fiitgame.Projectiles.Projectile;
import io.github.fiitgame.UI.StatisticBar;
import io.github.fiitgame.WaveSystem.Wave;

import static io.github.fiitgame.Listeners.EventListener.*;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {

    //======= Fields =======

    public static Player player;
    public static AssetManager assets;
    StatisticBar statisticBar;

    SpriteBatch spriteBatch;
    EventListener eventListener;
    Wave currentWave;
    float betweenWavesTimer = 0f;
    final float betweenWavesPause = 3f;


    //======= Create =======


    @Override
    public void create() {

        assets = new AssetManager();
        assets.load("textures/Enemies/slime.png", Texture.class);
        assets.load("textures/Player/mage-skin.png", Texture.class);
        assets.load("textures/projectiles/mage-projectile.png", Texture.class);
        assets.finishLoading();

        spriteBatch = new SpriteBatch();
        player = new Mage(true);
        eventListener = new EventListener();
        currentWave = new Wave(1);

        statisticBar = new StatisticBar(player);
    }


    //======= Render =======
    @Override
    public void render() {

        float delta = Gdx.graphics.getDeltaTime();

        ScreenUtils.clear(255, 255, 255, 1, true);


        if (currentWave.isFinished()) {
            betweenWavesTimer += delta;
            if (betweenWavesTimer >= betweenWavesPause) {
                currentWave = new Wave(currentWave.getWaveNumber() + 1);
                betweenWavesTimer = 0f;
            }
        } else {
            currentWave.update(delta);
        }


        // ======= Player =======
        player.update(delta);
        spriteBatch.begin();
        player.render(spriteBatch);

        // ======= Enemies =======

        for(Enemy enemy : enemies){
            enemy.update(delta);
            enemy.render(spriteBatch);
        }

        // ======= Projectiles =======

        for(Projectile projectile : projectiles){
            projectile.update(delta);
            projectile.render(spriteBatch);
        }
        projectileCollisionCheck();


        spriteBatch.end();


        // UI
        statisticBar.render();

    }

    //======= Dispose =======


    @Override
    public void dispose() {
        spriteBatch.dispose();

        assets.dispose();

        statisticBar.dispose();
    }
}

package io.github.fiitgame.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.fiitgame.Enemy.Enemy;
import io.github.fiitgame.Enemy.Slime;
import io.github.fiitgame.Player.*;
import io.github.fiitgame.Projectiles.Projectile;
import io.github.fiitgame.UI.StatisticBar;

import java.util.ArrayList;
import io.github.fiitgame.Listeners.EventListener;
import io.github.fiitgame.WaveSystem.Wave;

import java.util.List;

import static io.github.fiitgame.Listeners.EventListener.*;

public class GameScreen implements Screen {

    private final Game game;
    private final PlayerClass playerClass;

    private SpriteBatch spriteBatch;

    public static Player player;
    private EventListener eventListener;

    private Wave currentWave;

    StatisticBar statisticBar;

    private float spawnTimer = 0f;

    public GameScreen(Game game, PlayerClass playerClass) {
        this.game = game;
        this.playerClass = playerClass;
    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();

        switch (playerClass) {
            case MAGE:
                player = new Mage(true);
                break;
            case WARRIOR:
                player = new Melee(true);
                break;
            case ARCHER:
                player = new Archer(true);
                break;
        }
        statisticBar = new StatisticBar(player);
        eventListener = new EventListener();
        currentWave = new Wave(1);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);

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
        spriteBatch.end();

        // ======= Collision Checks =======
        projectileCollisionCheck();
        enemyPlayerCollisionCheck();
        enemyProjectileCollisionCheck();
        checkActiveProjectiles();

        // ======= UI =======
        statisticBar.render();


        // ========= PAUSE =========
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new PauseScreen(game, this));
        }

        // ======= Game Over =======
        if (player.isDead()) {
            game.setScreen(new GameOverScreen(game));
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            enemies.add(new Slime("textures/Enemies/slime.png", 20 + player.getLvl() * 10, 3 + player.getLvl(), player.getLvl()));
        }
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        if(spriteBatch != null) spriteBatch.dispose();
        if(statisticBar != null) statisticBar.dispose();
    }
}

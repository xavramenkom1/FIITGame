package io.github.fiitgame.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import io.github.fiitgame.Player.PlayerClass;
import io.github.fiitgame.Player.Player;
import io.github.fiitgame.Player.Mage;
import io.github.fiitgame.Player.Melee;

public class GameScreen implements Screen {

    private final Game game;
    private Player player;

    public GameScreen(Game game, PlayerClass playerClass) {
        this.game = game;

        if (playerClass == PlayerClass.MAGE) {
            player = new Mage(true);
        } else {
            player = new Melee(true);
        }
    }

    @Override
    public void render(float delta) {
        player.update(delta);
    }

    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}

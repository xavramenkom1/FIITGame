package io.github.fiitgame.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import io.github.fiitgame.Player.Player; // поправь путь если у тебя другой пакет
import io.github.fiitgame.Player.PlayerClass;

public class ClassSelectScreen implements Screen {

    private final Game game;
    private final SpriteBatch batch;
    private final BitmapFont font;

    public ClassSelectScreen(Game game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();
        font.draw(batch, "Choose class:", 100, 300);
        font.draw(batch, "1 - Mage", 100, 250);
        font.draw(batch, "2 - Melee", 100, 200);
        font.draw(batch, "3 - Archer", 100, 150);
        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            game.setScreen(new GameScreen(game, PlayerClass.MAGE));
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            game.setScreen(new GameScreen(game, PlayerClass.WARRIOR));
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            game.setScreen(new GameScreen(game, PlayerClass.ARCHER));
        }
    }

    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}

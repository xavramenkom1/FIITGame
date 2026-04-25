package io.github.fiitgame.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.fiitgame.Screens.ClassSelectScreen;

public class PauseScreen implements Screen {

    private final Game game;
    private final Screen previous;

    private SpriteBatch batch;
    private BitmapFont font;

    public PauseScreen(Game game, Screen previous) {
        this.game = game;
        this.previous = previous;
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);

        batch.begin();
        font.draw(batch, "PAUSED", 100, 300);
        font.draw(batch, "ESC - Continue", 100, 250);
        font.draw(batch, "Q - Exit to menu", 100, 200);
        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(previous);
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            game.setScreen(new ClassSelectScreen(game));
        }
    }

    @Override public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override public void show() {}
    @Override public void resize(int w,int h){}
    @Override public void pause(){}
    @Override public void resume(){}
    @Override public void hide(){}
}

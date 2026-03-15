package io.github.some_example_name;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    Player player;
    SpriteBatch spriteBatch;

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        player = new Player();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1); // цвет фона
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();

        player.update(delta);
        spriteBatch.begin();
        player.render(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}

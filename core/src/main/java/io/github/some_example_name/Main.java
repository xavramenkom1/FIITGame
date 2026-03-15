package io.github.some_example_name;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;


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
        ScreenUtils.clear(0, 0, 0, 1, true);

        float delta = Gdx.graphics.getDeltaTime();

        player.update(delta);
        spriteBatch.begin();
        player.render(spriteBatch);
        if(player.isAttacking()){
            spriteBatch.draw(player.texture, player.attackX, player.attackY);
        }
        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}

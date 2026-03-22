package io.github.some_example_name;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    Player player;
    SpriteBatch spriteBatch;

    List<Projectile> projectiles = new ArrayList<Projectile>();

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        player = new Player();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1, true);

        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            projectiles.add(player.attack());
        }

        float delta = Gdx.graphics.getDeltaTime();

        player.update(delta);
        spriteBatch.begin();
        player.render(spriteBatch);

        for(Projectile projectile : projectiles){
            projectile.update(delta);
            projectile.render(spriteBatch);
        }

        spriteBatch.end();
    }

    @Override
    public void dispose() {

    }
}

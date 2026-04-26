package io.github.fiitgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import io.github.fiitgame.Screens.ClassSelectScreen;



/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */

/**
 * Main class that loads all assetts, sets starting screen and disposes all assets when the game is closed.
 */
public class Main extends Game {

    public static AssetManager assets;


    @Override
    public void create() {
        loadAssets();
        setScreen(new ClassSelectScreen(this));
    }


    @Override
    public void render() {
        super.render();
    }


    private void loadAssets() {
        assets = new AssetManager();
        assets.load("textures/Enemies/slime.png", Texture.class);
        assets.load("textures/Player/mage-texture.png", Texture.class);
        assets.load("textures/Player/archer-texture.png", Texture.class);
        assets.load("textures/projectiles/arrow.png", Texture.class);
        assets.load("textures/projectiles/mage-projectile.png", Texture.class);
        assets.load("textures/Enemies/skeleton.png", Texture.class);

        assets.finishLoading();
    }


    @Override
    public void dispose() {
        assets.dispose();
    }

}

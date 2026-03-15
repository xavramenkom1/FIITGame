package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;

import java.awt.*;

public class Player  {
    float x, y;
    float speed = 70;

    Texture texture;

    public Player() {

        Pixmap pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        texture = new Texture(pixmap);
        pixmap.dispose();

        x = 200;
        y = 200;
    }
    public void update(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.A))
            x -= speed * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.D))
            x += speed * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.W))
            y += speed * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.S))
            y -= speed * delta;
    }
    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }
}

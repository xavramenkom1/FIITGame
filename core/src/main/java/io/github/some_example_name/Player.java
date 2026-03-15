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
    private float x, y;
    private float speed = 70;

    public float attackX;
    public float attackY;

    private  boolean attacking;

    public Texture texture;

    public Player() {

        Pixmap pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        texture = new Texture(pixmap);
        pixmap.dispose();

        attacking = false;

        x = 200;
        y = 200;
    }

    public void attack(){

        System.out.println("Attacked");
    };

    public void update(float delta) {
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            attackX = Gdx.input.getX();
            attackY = Gdx.graphics.getHeight() - Gdx.input.getY();
            attacking = true;
        }
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

    public boolean isAttacking(){
        return attacking;
    }


}

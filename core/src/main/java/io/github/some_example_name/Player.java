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
    private float speed;

    private int damage;
    private int health;
    private int maxHealth;
    private int lvl;
    private int xp;

    private float attackX;
    private float attackY;

    public Texture texture;

    public Player() {

        Pixmap pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        texture = new Texture(pixmap);
        pixmap.dispose();

        maxHealth = 100;
        health = 100;
        damage = 15;
        xp = 0;

        this.speed = 70;

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

    public Projectile attack(){
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

        float dirX = mouseX - x;
        float dirY = mouseY - y;

        float length = (float)Math.sqrt(dirX * dirX + dirY * dirY);

        dirX /= length;
        dirY /= length;

        return new Projectile("textures/projectiles/mage-projectile.png", x, y, dirX, dirY , damage);
    }

    private void levelUp(){
        lvl++;
        damage += 6 * lvl;
        maxHealth += 8 * lvl;
        health = maxHealth;
        xp = 0;
    }

}

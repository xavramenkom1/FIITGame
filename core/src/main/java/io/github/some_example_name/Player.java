package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;


public class Player  {
    private Vector2 position;
    private float speed;

    private int damage;
    private int health;
    private int maxHealth;
    private int lvl;
    private int xp;
    private int neededXp;


    public Texture texture;

    public Player() {

        Pixmap pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        texture = new Texture(pixmap);
        pixmap.dispose();

        maxHealth = 100;
        health = 100;
        damage = 5;
        xp = 0;
        neededXp = 100;

        this.speed = 70;

        this.position = new Vector2(200, 200);
    }


    public void update(float delta) {


        if (Gdx.input.isKeyPressed(Input.Keys.A))
            position.x -= speed * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.D))
            position.x += speed * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.W))
            position.y += speed * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.S))
            position.y -= speed * delta;
    }
    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }
    

    public Projectile attack(){
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

        float dirX = mouseX - position.x;
        float dirY = mouseY - position.y;

        float length = (float)Math.sqrt(dirX * dirX + dirY * dirY);

        dirX /= length;
        dirY /= length;

        return new Projectile("textures/projectiles/mage-projectile.png", position.x, position.y, dirX, dirY , damage);
    }

    public int getHealth(){
        return health;
    }

    private void levelUp(){
        lvl++;
        damage += 6 * lvl;
        maxHealth += 8 * lvl;
        health = maxHealth;
        xp = xp - neededXp;
        neededXp += 10 * lvl;


    }



    public void gainXp(int droppedXp){
        xp += droppedXp;
        if(xp >= neededXp){
            levelUp();
        }
    }

}

package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    private boolean flip;


    private int mana;
    private int maxMana;


    public Sprite sprite;

    public Player() {

        sprite = new Sprite(new Texture("textures/Player/mage-skin.png"));
        maxHealth = 100;
        health = 100;
        damage = 5;
        xp = 0;
        neededXp = 100;

        maxMana = 100;
        mana = 100;

        this.speed = 70;

        this.position = new Vector2(200, 200);
        flip = false;
    }


    public void update(float delta) {


        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.x -= speed * delta;
            flip = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.x += speed * delta;
            flip = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W))
            position.y += speed * delta;

        if (Gdx.input.isKeyPressed(Input.Keys.S))
            position.y -= speed * delta;

        if(!flip && !sprite.isFlipX()){
            sprite.flip(true, false);
        }
        else if (flip && sprite.isFlipX()){
            sprite.flip(true, false);
        }
    }
    public void render(SpriteBatch batch) {
        batch.draw(sprite, position.x, position.y);
    }


    public Projectile attack() throws NoManaException {
        if(mana < 20) throw new NoManaException("No mana");
        mana -= 20;
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

    public int getMana(){
        return mana;
    }
    public int getMaxMana(){
        return maxMana;
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

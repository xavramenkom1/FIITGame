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


    private float mana;
    private float maxMana;
    private float manaRegeneration;


    public Sprite sprite;

    public Player() {

        sprite = new Sprite(new Texture("textures/Player/mage-skin.png"));
        maxHealth = 100;
        health = 100;
        damage = 5;
        xp = 0;
        neededXp = 100;

        maxMana = 100f;
        mana = 100f;
        manaRegeneration = 12f;

        this.speed = 70;

        this.position = new Vector2(200, 200);
        flip = false;
    }


    public void update(float delta) {

        // Inputs

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

        if (!flip && !sprite.isFlipX()) {
            sprite.flip(true, false);
        } else if (flip && sprite.isFlipX()) {
            sprite.flip(true, false);
        }

        // Logic

        mana += manaRegeneration * delta;
        mana = Math.min(mana, maxMana);

    }
    public void render(SpriteBatch batch) {
        batch.draw(sprite, position.x, position.y);
    }


    public Projectile attack() throws NoManaException {
        if(mana < 20) throw new NoManaException("No mana");
        mana -= 20;
        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

        Vector2 direction = new Vector2(mouseX - position.x, mouseY - position.y);

        float length = (float)Math.sqrt(direction.x * direction.x + direction.y * direction.y);

        direction.x /= length;
        direction.y /= length;

        return new MageProjectile("textures/projectiles/mage-projectile.png", position, direction, damage);

    }

    public int getHealth(){
        return health;
    }

    public int getMana(){
        return (int)mana;
    }
    public int getMaxMana(){
        return (int)maxMana;
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

package io.github.fiitgame.Enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.github.fiitgame.Main;

import static io.github.fiitgame.Screens.GameScreen.player;


public abstract class Enemy implements EnemyInterface {

    private int health;
    private int maxHealth;
    private int damage;
    private int lvl;
    protected Vector2 position;
    protected float speed;
    protected int droppedXp;

    private Texture texture;
    protected Rectangle bounds;

    public Enemy(int health, int damage, int lvl) { // ONLY FOR TESTING PURPOSES
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
        this.lvl = lvl;
        position = new Vector2(0, 0);
        this.bounds = new Rectangle(position.x, position.y, 32, 32);
        this.texture = null;
    }

    public Enemy(String texturePath, int health, int damage, int lvl) {
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
        this.lvl = lvl;
        this.texture = Main.assets.get(texturePath, Texture.class);


        float x = 0;
        float y = 0;

        int side = MathUtils.random(3);

        switch (side) {
            case 0: // Left.
                x = (float) (Math.random() * Gdx.graphics.getWidth() / 5f);
                y = (float) (Math.random() * Gdx.graphics.getHeight());
                break;
            case 1: // Right
                x = Gdx.graphics.getWidth() - (float) (Math.random() * Gdx.graphics.getWidth() / 5f);
                y = (float) (Math.random() * Gdx.graphics.getHeight());
                break;
            case 2: // Down
                x = (float) (Math.random() * Gdx.graphics.getWidth());
                y = (float) (Math.random() * Gdx.graphics.getHeight() / 5f);
                break;
            case 3: // Up

                x = (float) (Math.random() * Gdx.graphics.getWidth());
                y = Gdx.graphics.getHeight() - (float) (Math.random() * Gdx.graphics.getHeight() / 5f);
                break;
        }
        this.position = new Vector2(x, y);
        this.bounds = new Rectangle(position.x, position.y, getTexture().getWidth(), getTexture().getHeight());
    }

    public void update(float delta) {
        move(delta);
        bounds.setPosition(position);
    }
    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y);
    }


    @Override
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }


    protected void move(float delta) {
        Vector2 playerPos = player.getPosition();
        Vector2 direction = new Vector2(playerPos.x - position.x, playerPos.y - position.y).nor();
        position.x += direction.x * speed * delta;
        position.y += direction.y * speed * delta;
        bounds.setPosition(position.x, position.y);

    }


    public int getHealth() {
        return health;
    }

    public int getDroppedXp() {
        return droppedXp;
    }

    public Texture getTexture() {
        return texture;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isDead() {
        return health <= 0;
    }

    private static int calculateXp(int lvl) {
        return 10 * lvl;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }


}


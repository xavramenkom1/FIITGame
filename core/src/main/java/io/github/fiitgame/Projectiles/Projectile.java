package io.github.fiitgame.Projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import io.github.fiitgame.Main;


public class Projectile {
    private Vector2 position;

    private Vector2 direction;
    protected float speed;

    private int damage;

    protected Sprite sprite;
    private Rectangle bounds;

    private boolean active;


    public Projectile(float x, float y, float w, float h, int damage) { // Only for testing purposes
        this.position = new Vector2(x, y);
        this.direction = new Vector2(1, 0);
        this.damage = damage;
        this.bounds = new Rectangle(x, y, w, h);
    }


    public Projectile(Texture texture, Vector2 position, Vector2 direction, int damage) {
        sprite = new Sprite(texture);
        this.position = new Vector2(position);

        this.direction = new Vector2(direction).nor();

        this.damage = damage;

        this.bounds = new Rectangle(position.x, position.y, this.sprite.getWidth(), this.sprite.getHeight());
    }

    public void setPosition(Vector2 position) {
        this.position = new Vector2(position);
    }
    public void setDirection(Vector2 direction){
        this.direction = new Vector2(direction);
    }
    public void setSpeed(float speed) {this.speed = speed;}
    public void setSize(float ratio) { this.sprite.setSize(ratio * sprite.getWidth(), ratio * sprite.getHeight()); }

    public Vector2 getPosition(){
        return new Vector2(position);
    }
    public Vector2 getDirection(){
        return new Vector2(direction);
    }
    public int getDamage(){
        return damage;
    }

    public void update(float delta){
        position.x += direction.x * delta * speed;
        position.y += direction.y * delta * speed;
        bounds.setPosition(position.x, position.y);
    }

    public void render(SpriteBatch spriteBatch){
        sprite.setPosition(position.x, position.y);
        sprite.draw(spriteBatch);

    }

    public boolean collides(Rectangle enemy){
        return bounds.overlaps(enemy);
    }

    public boolean isActive(){
        return active;
    }
    public void setActive(boolean active){
        this.active = active;
    }

}

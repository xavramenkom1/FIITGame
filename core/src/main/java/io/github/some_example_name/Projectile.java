package io.github.some_example_name;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class Projectile {
    private Vector2 position;

    private Vector2 direction;
    private float speed;

    private int damage;

    private Texture texture;
    private Rectangle bounds;

    public Projectile(String texturePath, float x, float y, float directionX, float directionY, int damage) {
        texture = new Texture(texturePath);
        speed = 200;
        this.position = new Vector2(x, y);

        this.direction = new Vector2(directionX, directionY).nor();

        this.damage = damage;

        this.bounds = new Rectangle(position.x, position.y, this.texture.getWidth(), this.texture.getHeight());
    }

    public void setPosition(Vector2 position) {
        this.position = new Vector2(position);
    }
    public void setDirection(Vector2 direction){
        this.direction = new Vector2(direction);
    }

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
        spriteBatch.draw(texture, position.x, position.y);

    }

    public boolean collides(Rectangle enemy){
        return bounds.overlaps(enemy);
    }

}

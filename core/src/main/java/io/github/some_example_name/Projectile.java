package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;


public class Projectile {
    private float x, y;

    private float dirrectionX, dirrectionY;
    private float speed;

    Texture texture;

    public Projectile(String texturePath, float x, float y, float dirrectionX, float dirrectionY) {
        texture = new Texture(texturePath);
        speed = 200;
        this.x = x;
        this.y = y;
        this.dirrectionX = dirrectionX;
        this.dirrectionY = dirrectionY;
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }
    public void setDirrection(float x, float y){
        dirrectionX = x;
        dirrectionY = y;
    }

    public Vector2 getPosition(){
        return new Vector2(x,y);
    }
    public Vector2 getDirrection(){
        return new Vector2(dirrectionX,dirrectionY);
    }

    public void update(float delta){
        x += dirrectionX * delta * speed;
        y += dirrectionY * delta * speed;
    }

    public void render(SpriteBatch spriteBatch){
        spriteBatch.draw(texture, x, y);
    }

}

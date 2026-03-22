package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {

    private int health;
    private int maxHealth;
    private int damage;
    private int lvl;
    protected float x, y;
    private boolean isDead;
    protected float speed;

    private Texture texture;
    protected Rectangle bounds;



        public Enemy(String texturePath ,int health, int damage, int lvl) {
            this.health = health;
            this.maxHealth = health;
            this.damage = damage;
            this.lvl = lvl;
            this.texture = new Texture(texturePath);
            this.isDead = false;

            x = Gdx.graphics.getWidth() * (2f/3f) + (float)(Math.random() * (Gdx.graphics.getWidth() - Gdx.graphics.getWidth() * (2f/3f)));
            y = Gdx.graphics.getWidth() * (2f/3f) + (float)(Math.random() * (Gdx.graphics.getWidth() - Gdx.graphics.getWidth() * (2f/3f)));

            // From the side of screen to 2/3 of the screen
        }

        public void update(float delta){

            if(isDead){
                // die();
            }
            move(delta);
            bounds.setPosition(x, y);

        }





        public void takeDamage(int damage){
            health -= damage;
            if(health < 0){
                health = 0;
                isDead = true;
            }
        }
        void move(float delta){};


        // void die(){}
        public int getHealth() {
            return health;
        }
        public Texture getTexture() {
            return texture;
        }

}


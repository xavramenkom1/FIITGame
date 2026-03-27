package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy {

    private int health;
    private int maxHealth;
    private int damage;
    private int lvl;
    protected Vector2 position;
    protected float speed;
    private int droppedXp;

    private Texture texture;
    protected Rectangle bounds;



        public Enemy(String texturePath ,int health, int damage, int lvl) {
            this.health = health;
            this.maxHealth = health;
            this.damage = damage;
            this.lvl = lvl;
            this.texture = new Texture(texturePath);

            float x = 0;
            float y = 0;

            int side = MathUtils.random(3);

            switch(side) {
                case 0: // левый край
                    x = (float)(Math.random() * Gdx.graphics.getWidth() / 5f);
                    y = (float)(Math.random() * Gdx.graphics.getHeight());
                    break;
                case 1: // правый край
                    x = Gdx.graphics.getWidth() - (float)(Math.random() * Gdx.graphics.getWidth() / 5f);
                    y = (float)(Math.random() * Gdx.graphics.getHeight());
                    break;
                case 2: // нижний край
                    x = (float)(Math.random() * Gdx.graphics.getWidth());
                    y = (float)(Math.random() * Gdx.graphics.getHeight() / 5f);
                    break;
                case 3: // верхний край
                    x = (float)(Math.random() * Gdx.graphics.getWidth());
                    y = Gdx.graphics.getHeight() - (float)(Math.random() * Gdx.graphics.getHeight() / 5f);
                    break;
            }
            this.position = new Vector2(x, y);
        }

        public void update(float delta){

            if(isDead()){
                // die();
            }
            move(delta);
            bounds.setPosition(position);

        }


        public void takeDamage(int damage){
            health -= damage;
            if(health < 0){
                health = 0;
            }
        }
        void move(float delta){
            bounds.setPosition(position.x, position.y);
        };


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

        public boolean isDead(){
            return health <= 0;
        };

        private void die(){

        }

        public void render(SpriteBatch batch){
            batch.draw(texture, position.x, position.y);
        }
}


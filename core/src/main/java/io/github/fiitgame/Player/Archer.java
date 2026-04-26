package io.github.fiitgame.Player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import io.github.fiitgame.Exceptions.AttackException;
import io.github.fiitgame.Exceptions.CoolDownException;
import io.github.fiitgame.Listeners.EventListener;
import io.github.fiitgame.Projectiles.ArcherProjectile;
import io.github.fiitgame.Projectiles.Projectile;

import static io.github.fiitgame.Main.assets;

public class Archer extends Player {

    private float attackRange;
    private float attackCooldown;
    private float cooldownTimer;
    private int arrowCount;
    private int maxArrowCount;
    private float reloadTime;
    private boolean isReloading;

    public Archer(boolean initialiseGraphics) {
        super();

        health = 80;
        maxHealth = 80;
        damage = 13;
        speed = 100f;

        if (initialiseGraphics) {
            sprite = new Sprite(assets.get("textures/Player/archer-texture.png", Texture.class));
        }

        attackRange = 100f;
        attackCooldown = 0.3f;
        cooldownTimer = 0f;
        arrowCount = 8;
        maxArrowCount = 8;
        reloadTime = 2.5f;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (cooldownTimer > 0) {
            cooldownTimer -= delta;
        }
        else{
            isReloading = false;
        }
        if(arrowCount <= 0 || Gdx.input.isKeyJustPressed(Input.Keys.R)){
            reload();
        }
    }

    @Override
    protected void handleAttackInput() {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            try {
                Projectile proj = attack();
                if (proj != null) {
                    EventListener.projectiles.add(proj);
                }
            } catch (AttackException e) {
                System.out.println("Archer attack failed: " + e.getMessage());
            }
        }
    }

    public Projectile attack() throws AttackException {
        if (cooldownTimer > 0) throw new CoolDownException("Attack is on cooldown");
        if(arrowCount <= 0){
            throw new CoolDownException("Out of arrows, reloading...");
        }

        cooldownTimer = attackCooldown;

        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

        Vector2 direction = new Vector2(mouseX - position.x, mouseY - position.y);
        direction.nor();

        Vector2 attackPos = new Vector2(position.x + direction.x * attackRange, position.y + direction.y * attackRange);

        Projectile proj = new ArcherProjectile(
            assets.get("textures/projectiles/arrow.png", Texture.class),
            position,
            direction,
            damage
        );
        arrowCount--;
        return proj;
    }
    private void reload(){
        arrowCount = maxArrowCount;
        cooldownTimer = reloadTime;
        isReloading = true;
    }
    @Override
    protected void levelUp(){
        super.levelUp();
        maxArrowCount += (int) (1.5f * lvl);
    }

    public int getArrowCount(){ return arrowCount; }
    public int getMaxArrowCount(){ return maxArrowCount; }
    public boolean isReloading(){ return isReloading; }
}

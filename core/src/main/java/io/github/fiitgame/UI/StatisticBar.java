package io.github.fiitgame.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.fiitgame.Player.Mage;
import io.github.fiitgame.Player.Player;

public class StatisticBar {

    private final ShapeRenderer shapeRenderer;
    private final Player player;

    private static final float width = 100f;
    private static final float height = 7f;
    private static final float barPosX = 20f;
    private static final float barPosY = Gdx.graphics.getHeight() - 20f;

    public StatisticBar(Player player) {
        this.player = player;
        this.shapeRenderer = new ShapeRenderer();
    }

    public void render() {
        float hpRatio = (float) player.getHealth() / player.getMaxHealth();
        float xpRatio = (float) player.getXp() / player.getNeededXp();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(barPosX, barPosY, width, height);

        // HP bar
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(barPosX, barPosY, width * hpRatio, height);

        // XP bar

        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(barPosX, barPosY - 20f, width, height);

        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(barPosX, barPosY - 20f, width * xpRatio, height);

        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(barPosX, barPosY - 20f, width, height);

        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(barPosX, barPosY - 40f, width * xpRatio, height);

        if((player instanceof Mage mage)) renderMana(shapeRenderer, mage);

        shapeRenderer.end();
    }

    private void renderMana(ShapeRenderer shapeRenderer, Mage mage) {

        float manaRatio = (float) mage.getMana() / mage.getMaxMana();
        // Mana bar
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(barPosX, barPosY - 40f, width, height);

        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(barPosX, barPosY - 40f, width * manaRatio, height);
    }


    public void dispose() {
        shapeRenderer.dispose();
    }
}

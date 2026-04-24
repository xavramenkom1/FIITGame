package io.github.fiitgame.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.fiitgame.Player.Player;

public class StatisticBar {

    private final ShapeRenderer shapeRenderer;
    private final Player player;

    private static final float width = 100f;
    private static final float height = 7f;
    private static final float barPosX = 20f;
    private static final float barPosY = 2f;

    public StatisticBar(Player player) {
        this.player = player;
        this.shapeRenderer = new ShapeRenderer();
    }

    public void render() {
        float hpRatio = (float) player.getHealth() / player.getMaxHealth();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(barPosX, barPosY, width, height);

        shapeRenderer.setColor(
            hpRatio > 0.5f ? Color.GREEN :
                hpRatio > 0.25f ? Color.ORANGE :
                    Color.RED
        );
        shapeRenderer.rect(barPosX, barPosY, width * hpRatio, height);

        shapeRenderer.end();
    }

    public void dispose() {
        shapeRenderer.dispose();
    }
}

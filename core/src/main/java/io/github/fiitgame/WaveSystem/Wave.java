package io.github.fiitgame.WaveSystem;

import com.badlogic.gdx.graphics.Texture;
import io.github.fiitgame.Enemy.Skeleton;
import io.github.fiitgame.Enemy.Slime;
import io.github.fiitgame.Listeners.EventListener;

import static com.badlogic.gdx.math.MathUtils.random;


/**
 * The Wave class manages enemy waves in the game, controlling the timing and difficulty of enemy spawns.
 *
 *
 * */


public class Wave {

    private final int waveNumber;
    private float spawnTimer;

    /**
     * Creates a new wave instance.
     *
     * @param waveNumber current wave number, affects difficulty
     */

    public Wave(int waveNumber) {
        this.waveNumber = waveNumber;
        spawnTimer = 8f;
    }
    /**
     * Updates wave state and handles enemy spawning.
     *
     * @param delta time since last frame
     */
    public void update(float delta) {
        spawnTimer += delta;

        float spawnInterval = 10f;
        if (spawnTimer >= spawnInterval) {
            spawnTimer = 0f;
            spawnEnemies();
        }

    }
    /**
     * Makes random Waves
     *
     *
     */
    private void spawnEnemies() {
        int lvl = waveNumber;

        int enemyCount = random.nextInt(waveNumber + 1, waveNumber * 3 + 2);

        for(int i = 0; i < enemyCount; i++) {
            int enemy = random.nextInt(1, 3);
            switch (enemy) {
                case 1 -> EventListener.enemies.add(new Slime("textures/Enemies/slime.png", 20 + lvl * 5, 5 + lvl, lvl));
                case 2 -> EventListener.enemies.add(new Skeleton("textures/Enemies/skeleton.png", 30 + lvl * 10, 10 + lvl * 2, lvl));
            }
        }

    }

    public int getWaveNumber() {
        return waveNumber;
    }
}

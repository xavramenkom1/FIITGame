package io.github.fiitgame.WaveSystem;

import io.github.fiitgame.Enemy.Slime;
import io.github.fiitgame.Listeners.EventListener;

public class Wave {

    private final int waveNumber;
    private final int enemyCount;
    private float spawnTimer = 0f;
    private final float spawnInterval = 1.5f; // секунд между спавнами
    private int spawned = 0;
    private boolean finished = false;

    public Wave(int waveNumber) {
        this.waveNumber = waveNumber;
        this.enemyCount = 3 + waveNumber * 2; // волна 1 = 5 врагов, волна 2 = 7, ...
    }

    public void update(float delta) {
        if (finished) return;

        spawnTimer += delta;
        if (spawnTimer >= spawnInterval && spawned < enemyCount) {
            spawnEnemy();
            spawnTimer = 0f;
            spawned++;
        }

        if (spawned >= enemyCount && EventListener.enemies.isEmpty()) {
            finished = true;
        }
    }

    private void spawnEnemy() {
        int lvl = waveNumber; // уровень врага = номер волны
        EventListener.enemies.add(new Slime("textures/Enemies/slime.png", 20 + lvl * 10, 3 + lvl, lvl));
    }

    public boolean isFinished() {
        return finished;
    }

    public int getWaveNumber() {
        return waveNumber;
    }
}

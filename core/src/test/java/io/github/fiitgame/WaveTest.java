package io.github.fiitgame;

import io.github.fiitgame.WaveSystem.Wave;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WaveTest {

    @Test
    void constructor_wave1_waveNumber() {
        Wave wave = new Wave(1);
        assertEquals(1, wave.getWaveNumber());
    }

    @Test
    void constructor_wave5_waveNumber() {
        Wave wave = new Wave(5);
        assertEquals(5, wave.getWaveNumber());
    }

    @Test
    void initiallyNotFinished() {
        Wave wave = new Wave(1);
        assertFalse(wave.isFinished());
    }

    @Test
    void wave0_notFinished() {
        Wave wave = new Wave(0);
        assertFalse(wave.isFinished());
    }

    @Test
    void differentWaveNumbers_storedCorrectly() {
        for (int i = 1; i <= 10; i++) {
            Wave wave = new Wave(i);
            assertEquals(i, wave.getWaveNumber());
        }
    }
}

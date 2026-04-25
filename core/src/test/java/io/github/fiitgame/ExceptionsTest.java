package io.github.fiitgame;

import io.github.fiitgame.Exceptions.AttackException;
import io.github.fiitgame.Exceptions.MeleeException;
import io.github.fiitgame.Exceptions.NoManaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionsTest {

    // ===== AttackException ======

    @Test
    void attackException_message() {
        AttackException ex = new AttackException("test message");
        assertEquals("test message", ex.getMessage());
    }

    @Test
    void attackException_isException() {
        AttackException ex = new AttackException("err");
        assertInstanceOf(Exception.class, ex);
    }

    // ===== NoManaException ======

    @Test
    void noManaException_message() {
        NoManaException ex = new NoManaException("No mana");
        assertEquals("No mana", ex.getMessage());
    }

    @Test
    void noManaException_isAttackException() {
        NoManaException ex = new NoManaException("No mana");
        assertInstanceOf(AttackException.class, ex);
    }

    @Test
    void noManaException_thrownAndCaught() {
        assertThrows(NoManaException.class, () -> {
            throw new NoManaException("no mana");
        });
    }

    @Test
    void noManaException_caughtAsAttackException() {
        assertThrows(AttackException.class, () -> {
            throw new NoManaException("no mana");
        });
    }

    // ====== MeleeException ======

    @Test
    void meleeException_message() {
        MeleeException ex = new MeleeException("on cooldown");
        assertEquals("on cooldown", ex.getMessage());
    }

    @Test
    void meleeException_isAttackException() {
        MeleeException ex = new MeleeException("cd");
        assertInstanceOf(AttackException.class, ex);
    }

    @Test
    void meleeException_thrownAndCaught() {
        assertThrows(MeleeException.class, () -> {
            throw new MeleeException("cooldown");
        });
    }

    @Test
    void meleeException_caughtAsAttackException() {
        assertThrows(AttackException.class, () -> {
            throw new MeleeException("cooldown");
        });
    }

    // ====== NoManaException vs MeleeException ======

    @Test
    void noManaException_notMeleeException() {
        Exception ex = new NoManaException("mana");
        assertFalse(ex instanceof MeleeException);
    }

    @Test
    void meleeException_notNoManaException() {
        Exception ex = new MeleeException("melee");
        assertFalse(ex instanceof NoManaException);
    }
}

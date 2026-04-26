package io.github.fiitgame.Exceptions;

/**
 * Exception, that is being thrown when player attack is on cooldown
 */

public class CoolDownException extends AttackException {
    public CoolDownException(String message) {
        super(message);
    }
}

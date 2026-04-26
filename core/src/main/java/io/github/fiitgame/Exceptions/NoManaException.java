package io.github.fiitgame.Exceptions;

/**
 * Exception, that is being thrown when player has no mana to perform attack
 */

public class NoManaException extends AttackException {
    public NoManaException(String message) {
        super(message);
    }
}

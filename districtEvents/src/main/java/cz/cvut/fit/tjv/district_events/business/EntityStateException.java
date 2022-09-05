/*
 * Project Social Network. Created for Java Technology course at Czech Technical University in Prague,
 * Faculty of Information Technology.
 *
 * Author: Ondřej Guth (ondrej.guth@fit.cvut.cz)
 *
 * This code is intended for educational purposes only.
 */

package cz.cvut.fit.tjv.district_events.business;

/**
 * A checked exception indicating problem related to existence of an entity.
 */
public class EntityStateException extends Exception {
    public <E> EntityStateException(E entity) {
        super("Illegal state of entity " + entity);
    }
}

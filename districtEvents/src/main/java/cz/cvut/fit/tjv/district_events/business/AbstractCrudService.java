package cz.cvut.fit.tjv.district_events.business;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * Common superclass for business logic of all entities supporting operations Create, Read, Update, Delete.
 *
 * @param <E> Type of entity
 * @param <K> Type of (primary) key.
 */
public abstract class AbstractCrudService<E, K> {
    /**
     * Reference to data (persistence) layer.
     */
    protected final JpaRepository<E, K> repository;

    protected AbstractCrudService(JpaRepository<E, K> repository) {
        this.repository = repository;
    }

    public void create(E entity) throws EntityStateException {
        if (exists(entity)) {
            throw new EntityStateException(entity);
        }
        repository.save(entity);
    }

    public Optional<E> readById(K id) {
        return repository.findById(id);
    }

    public Collection<E> readAll() {
        return repository.findAll();
    }

    public abstract boolean exists(E entity);

    /**
     * Attempts to replace an already stored entity.
     *
     * @param entity the new state of the entity to be updated; the instance must contain a key value
     * @throws EntityStateException if the entity cannot be found
     */
    public void update(E entity) throws EntityStateException {
        if (exists(entity))
            repository.save(entity);
        else
            throw new EntityStateException(entity);
    }

    public void deleteById(K id) {
        repository.deleteById(id);
    }
}

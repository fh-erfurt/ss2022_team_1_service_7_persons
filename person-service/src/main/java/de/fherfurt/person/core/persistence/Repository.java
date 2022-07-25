package de.fherfurt.person.core.persistence;

/**
 * Basic API definition for a component that handles the persisting if a specific type. Special operations should be
 * defined and implemented in the explicit repository.
 *
 * @param <ENTITY> Generic type of entity
 * @author Tobias Kärst <tobias.kärst@fh-erfurt.de>
 */
public interface Repository<ENTITY extends BaseBusinessEntity> {
    /**
     * Save an entity to the underlying storage. It doesn't matter, if the entity is new or already saved.
     * In case of update the changes are written too.
     *
     * @param entity Instance to save
     */
    void save(ENTITY entity);

    /**
     * Find an entity by its id.
     *
     * @param id The id of the searched entity
     * @return The found entity
     */
    ENTITY findBy(long id);

    /**
     * Deletes a given entity.
     *
     * @param entity Instance to delete
     */
    void delete(ENTITY entity);
}
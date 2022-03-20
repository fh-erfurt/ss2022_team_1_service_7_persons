package de.fherfurt.person.core.persistence;

import java.util.Optional;

/**
 * Basic API definition for a component that handles the persisting if a specific type. Special operations should be
 * defined and implemented in the explicit repository.
 *
 * @param <ENTITY> Generic type of entity
 * @author Michael Rhoese <michael.rhoese@fh-erfurt.de>
 */
public interface Repository<ENTITY extends BaseBusinessEntity> {
    /**
     * Save an entity to the underlying storage. It doesn't matter, if the entity is new or already saved. In case of update
     * the changes are written too.
     *
     * @param entity Instance to save
     */
    void save(ENTITY entity);

    /**
     * Find an entity by its id. If no entity is available, an empty {@link Optional} is returned.
     *
     * @param id Id of the searched entity
     * @return The entity or empty
     */
    Optional<ENTITY> findBy(int id);

    /**
     * Deletes a given entity.
     *
     * @param entity Instance to delete
     */
    void delete(ENTITY entity);
}
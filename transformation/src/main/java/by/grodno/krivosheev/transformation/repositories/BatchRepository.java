package by.grodno.krivosheev.transformation.repositories;

import by.grodno.krivosheev.transformation.entities.BatchEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * This interface for {@link BatchEntity} extends pagination and sorting.
 */
public interface BatchRepository extends PagingAndSortingRepository<BatchEntity, Long> {

    /**
     * Get info about {@link BatchEntity} to id.
     *
     * @param id number batch.
     * @return info about batch.
     */
    BatchEntity getById(long id);
}

package by.grodno.krivosheev.transformation.repositories;

import by.grodno.krivosheev.transformation.entities.ItemEntity;

import org.springframework.data.domain.Pageable;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * This interface for {@link ItemEntity} extends pagination and sorting.
 */
public interface ItemRepository extends PagingAndSortingRepository<ItemEntity, Long> {

    /**
     * Find all items to idBatch and return paging and/or sorting list.
     * @param idBatch number upload Batch;
     * @param pageable page, size and sort (see {@link Pageable}).
     * @return list found item which paging and/or sorted.
     */
    List<ItemEntity> findAllByIdBatch(long idBatch, Pageable pageable);
}

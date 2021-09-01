package by.grodno.krivosheev.transformation.services;

import by.grodno.krivosheev.transformation.entities.ItemEntity;

import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * This interface for work with item get of xml file.
 */
public interface ItemService {

    /**
     * Save all item of the list to database.
     * @param list items (see {@link ItemEntity})
     */
    void saveAll(List<ItemEntity> list);

    /**
     * Find all items to idBatch and return paging and/or sorting list.
     * @param idBatch number upload Batch;
     * @param pageable page, size and sort (see {@link Pageable}).
     * @return list found item which paging and/or sorted.
     */
    List<ItemEntity> findAllByIdBatch(long idBatch, Pageable pageable);
}

package by.grodno.krivosheev.transformation.elastic.services;

import by.grodno.krivosheev.transformation.elastic.pojo.ItemDocument;
import by.grodno.krivosheev.transformation.entities.ItemEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * This interface for work elasticsearch.
 */
public interface ItemDocumentService {

    /**
     * Save list {@link ItemDocument} for indexation.
     *
     * @param itemsEntity list with (see {@link ItemEntity}).
     */
    void createItemsDocumentList(List<ItemEntity> itemsEntity);

    /**
     * Get items by subject containing.
     *
     * @param subject  search param.
     * @param pageable page, size and sort (see {@link Pageable}).
     * @return list found items document.
     */
    List<ItemDocument> getItemsBySubjectContaining(String subject, Pageable pageable);
}

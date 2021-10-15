package by.grodno.krivosheev.transformation.elastic.repositories;

import by.grodno.krivosheev.transformation.elastic.pojo.ItemDocument;

import org.springframework.data.domain.Pageable;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * This interface for {@link ItemDocument} extends {@link ElasticsearchRepository}.
 */
public interface ItemDocumentRepository extends ElasticsearchRepository<ItemDocument, String> {

    List<ItemDocument> findBySubjectContaining(String subject, Pageable pageable);
}

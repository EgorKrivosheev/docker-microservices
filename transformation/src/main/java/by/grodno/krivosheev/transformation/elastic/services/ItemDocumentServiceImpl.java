package by.grodno.krivosheev.transformation.elastic.services;

import by.grodno.krivosheev.transformation.elastic.pojo.ItemDocument;

import by.grodno.krivosheev.transformation.elastic.repositories.ItemDocumentRepository;

import by.grodno.krivosheev.transformation.entities.ItemEntity;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemDocumentServiceImpl implements ItemDocumentService {
    private final ItemDocumentRepository itemDocumentRepository;

    @Override
    public void createItemsDocumentList(List<ItemEntity> itemsEntity) {
        itemDocumentRepository.saveAll(itemsEntity.stream()
                .map(this::getItemDocument)
                .collect(Collectors.toList()));
    }

    @Override
    public List<ItemDocument> getItemsBySubjectContaining(String subject, Pageable pageable) {
        return itemDocumentRepository.findBySubjectContaining(subject, pageable);
    }

    private ItemDocument getItemDocument(ItemEntity itemEntity) {
        return ItemDocument.builder()
                .idBatch(itemEntity.getIdBatch())
                .idItem(itemEntity.getId())
                .subject(itemEntity.getSubject())
                .body(itemEntity.getBody())
                .build();
    }
}

package by.grodno.krivosheev.transformation.services;

import by.grodno.krivosheev.transformation.elastic.services.ItemDocumentService;

import by.grodno.krivosheev.transformation.entities.ItemEntity;

import by.grodno.krivosheev.transformation.repositories.ItemRepository;

import com.google.common.collect.Lists;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemDocumentService itemDocumentService;

    @Override
    public void saveAllAndPushElasticsearch(List<ItemEntity> list) {
        itemDocumentService.createItemsDocumentList(Lists.newArrayList(itemRepository.saveAll(list)));
    }

    @Override
    public List<ItemEntity> findAllByIdBatch(long idBatch, Pageable pageable) {
        return itemRepository.findAllByIdBatch(idBatch, pageable);
    }
}

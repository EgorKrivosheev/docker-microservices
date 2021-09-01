package by.grodno.krivosheev.transformation.services;

import by.grodno.krivosheev.transformation.entities.ItemEntity;

import by.grodno.krivosheev.transformation.repositories.ItemRepository;

import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void saveAll(List<ItemEntity> list) {
        itemRepository.saveAll(list);
    }

    @Override
    public List<ItemEntity> findAllByIdBatch(long idBatch, Pageable pageable) {
        return itemRepository.findAllByIdBatch(idBatch, pageable);
    }
}

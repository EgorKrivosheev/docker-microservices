package by.grodno.krivosheev.transformation.mappers;

import by.grodno.krivosheev.transformation.dto.UnifiedFormatDTO;
import by.grodno.krivosheev.transformation.entities.ItemEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(config = MapperAbstractFactory.class)
public interface ItemMapper extends MapperAbstractFactory<UnifiedFormatDTO, ItemEntity> {

    /**
     * This mapper gets {@code id}, {@code subject}, {@code body} of the {@link ItemEntity}.
     *
     * @param itemEntity it is item save to database.
     */
    @Override
    UnifiedFormatDTO entityToDTO(ItemEntity itemEntity);

    /**
     * Transformation items in unified format.
     *
     * @param itemEntities items (see {@link ItemEntity}).
     * @return list {@link UnifiedFormatDTO} items.
     */
    @Override
    List<UnifiedFormatDTO> listEntityToListDTO(List<ItemEntity> itemEntities);
}

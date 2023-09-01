package by.grodno.krivosheev.transformation.mappers;

import by.grodno.krivosheev.transformation.dto.BatchInfoDTO;
import by.grodno.krivosheev.transformation.entities.BatchEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(config = MapperAbstractFactory.class)
public interface BatchMapper extends MapperAbstractFactory<BatchInfoDTO, BatchEntity> {

    /**
     * This mapper gets {@code id}, {@code size}, {@code uploadDate} of the {@link BatchEntity}.
     *
     * @param batchEntity it is info about Batch in database.
     * @return information about upload batch {@link BatchInfoDTO}.
     */
    @Override
    BatchInfoDTO entityToDTO(BatchEntity batchEntity);

    @Override
    List<BatchInfoDTO> listEntityToListDTO(List<BatchEntity> listEntity);
}

package by.grodno.krivosheev.transformation.mappers;

import by.grodno.krivosheev.transformation.dto.BatchInfoDTO;

import by.grodno.krivosheev.transformation.entities.BatchEntity;

import org.mapstruct.Mapper;

import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface BatchMapper {

    /**
     * This mapper is gets {@code id}, {@code size}, {@code uploadDate} of the {@link BatchEntity}.
     * @param batchEntity it is info about Batch in database.
     * @return information about upload batch {@link BatchInfoDTO}.
     */
    BatchInfoDTO batchEntityToBatchInfoDTO(BatchEntity batchEntity);

    List<BatchInfoDTO> listBatchEntityToListBatchInfoDTO(List<BatchEntity> batchEntities);
}

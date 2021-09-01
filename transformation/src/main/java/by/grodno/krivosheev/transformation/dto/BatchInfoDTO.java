package by.grodno.krivosheev.transformation.dto;

import by.grodno.krivosheev.transformation.entities.BatchEntity;

import lombok.Getter;

/**
 * This class contains information about upload batch. <p>
 *
 * Information: <p>
 *  - id = number by which you can get his items; <p>
 *  - size = size file in byte; <p>
 *  - uploadDate = date upload file.
 */
@Getter
public class BatchInfoDTO {
    private final Long id;
    private final long size;
    private final String uploadDate;

    /**
     * This constructor is gets {@code id}, {@code size}, {@code uploadDate} of the {@link BatchEntity}.
     * @param entity it is info about Batch in database.
     */
    public BatchInfoDTO(BatchEntity entity) {
        this.id = entity.getId();
        this.size = entity.getSize();
        this.uploadDate = entity.getUploadDate();
    }
}

package by.grodno.krivosheev.transformation.dto;

import lombok.Getter;

/**
 * This class contains information about upload batch. <p>
 * Information: <p>
 * - id = number by which you can get his items; <p>
 * - size = size file in byte; <p>
 * - uploadDate = date upload file.
 */
@Getter
public class BatchInfoDTO extends AbstractDTO {
    private final long size;
    private final String uploadDate;

    public BatchInfoDTO(Long id, long size, String uploadDate) {
        super(id);
        this.size = size;
        this.uploadDate = uploadDate;
    }
}

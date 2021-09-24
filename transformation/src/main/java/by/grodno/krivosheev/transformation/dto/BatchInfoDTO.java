package by.grodno.krivosheev.transformation.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This class contains information about upload batch. <p>
 *
 * Information: <p>
 *  - id = number by which you can get his items; <p>
 *  - size = size file in byte; <p>
 *  - uploadDate = date upload file.
 */
@Getter
@RequiredArgsConstructor
public class BatchInfoDTO {
    private final Long id;
    private final long size;
    private final String uploadDate;
}

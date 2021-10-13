package by.grodno.krivosheev.transformation.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This class abstract DTO. <p>
 *
 * Where: <p>
 *  - id = unique identification.
 */
@Getter
@RequiredArgsConstructor
public abstract class AbstractDTO {
    private final Long id;
}

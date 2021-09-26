package by.grodno.krivosheev.transformation.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This class is unified format for item of the items in xml file. <p>
 *
 * Where: <p>
 *  - id = tag id item; <p>
 *  - subject = tag topic item; <p>
 *  - body = tag content item.
 */
@Getter
@RequiredArgsConstructor
public class UnifiedFormatDTO {
    private final long id;
    private final String subject;
    private final String body;
}

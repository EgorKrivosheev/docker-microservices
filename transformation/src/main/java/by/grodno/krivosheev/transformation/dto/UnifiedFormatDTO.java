package by.grodno.krivosheev.transformation.dto;

import lombok.Getter;

/**
 * This class is unified format for item of the items in xml file. <p>
 * Where: <p>
 * - id = tag id item; <p>
 * - subject = tag topic item; <p>
 * - body = tag content item.
 */
@Getter
public class UnifiedFormatDTO extends AbstractDTO {
    private final String subject;
    private final String body;

    public UnifiedFormatDTO(Long id, String subject, String body) {
        super(id);
        this.subject = subject;
        this.body = body;
    }
}

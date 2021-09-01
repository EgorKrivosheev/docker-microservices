package by.grodno.krivosheev.transformation.dto;

import by.grodno.krivosheev.transformation.entities.ItemEntity;

import lombok.Getter;

/**
 * This class is unified format for item of the items in xml file. <p>
 *
 * Where: <p>
 *  - id = tag id item; <p>
 *  - subject = tag topic item; <p>
 *  - body = tag content item.
 */
@Getter
public class UnifiedFormatDTO {
    private final long id;
    private final String subject;
    private final String body;

    /**
     * This constructor gets {@code id}, {@code subject}, {@code body} of the {@link ItemEntity}.
     * @param entity it is item save to database.
     */
    public UnifiedFormatDTO(ItemEntity entity) {
        this.id = entity.getId();
        this.subject = entity.getSubject();
        this.body = entity.getBody();
    }
}

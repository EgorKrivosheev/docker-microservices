package by.grodno.krivosheev.transformation.elastic.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

/**
 * This pojo for elasticsearch.
 */
@Setter
@Getter
@Builder
@Document(indexName = "item")
public class ItemDocument {
    @Id
    private String id;

    @Field(type = FieldType.Long, name = "id_batch")
    private Long idBatch;

    @Field(type = FieldType.Long, name = "id_item")
    private Long idItem;

    @Field(type = FieldType.Text, name = "subject")
    private String subject;

    @Field(type = FieldType.Text, name = "body")
    private String body;
}

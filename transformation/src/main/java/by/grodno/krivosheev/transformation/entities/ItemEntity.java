package by.grodno.krivosheev.transformation.entities;

import by.grodno.krivosheev.transformation.pojo.Item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ITEM")
@Getter
@Setter
@NoArgsConstructor
public class ItemEntity extends AbstractEntity{
    @Column
    private String subject;

    @Column
    private String body;

    @Column(name = "id_batch", nullable = false)
    private long idBatch;

    public ItemEntity(Item item, long idBatch) {
        this.subject = item.getTopic();
        this.body = item.getContent();
        this.idBatch = idBatch;
    }
}

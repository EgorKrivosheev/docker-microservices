package by.grodno.krivosheev.transformation.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BATCH")
@Getter
@Setter
@NoArgsConstructor
public class BatchEntity extends AbstractEntity {
    @Column(nullable = false)
    private long size;

    @Column(name = "upload_date")
    private String uploadDate;
}

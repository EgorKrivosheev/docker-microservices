package by.grodno.krivosheev.transformation.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "BATCH")
@Getter
@Setter
@NoArgsConstructor
public class BatchEntity {
    @Id
    @Column(name = "id_batch")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    @Column(nullable = false)
    private long size;

    @Column(name = "upload_date")
    private String uploadDate;
}

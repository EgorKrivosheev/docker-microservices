package by.grodno.krivosheev.transformation.services;

import by.grodno.krivosheev.transformation.entities.BatchEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * This interface for work with batch. Batch, it is zip file contains xml file.
 */
public interface BatchService {

    /**
     * Save batch and add info to a database.
     *
     * @param file which need upload.
     * @return upload file.
     * @throws IOException if an I/O error occurs.
     */
    File save(MultipartFile file) throws IOException;

    /**
     * Get all batches and return a paging and/or sorting list.
     *
     * @param pageable page, size and sort (see {@link Pageable}).
     * @return list found batches which paging and/or sorted.
     */
    List<BatchEntity> getAll(Pageable pageable);

    /**
     * Get batch by id.
     *
     * @param id number batch.
     * @return info about batch (see {@link BatchEntity}).
     */
    BatchEntity getBatch(Long id);
}

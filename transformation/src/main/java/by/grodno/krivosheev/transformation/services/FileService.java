package by.grodno.krivosheev.transformation.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * This interface for work with file.
 */
public interface FileService {

    /**
     * Upload the file under the name and send name in kafka.
     *
     * @param file which need upload;
     * @param name file's name.
     * @return upload file.
     * @throws IOException if an I/O error occurs.
     */
    File uploadAndSendKafka(MultipartFile file, String name) throws IOException;
}

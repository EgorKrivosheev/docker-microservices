package by.grodno.krivosheev.transformation.services;

import by.grodno.krivosheev.transformation.entities.ItemEntity;

import by.grodno.krivosheev.transformation.dto.UnifiedFormatDTO;

import java.io.File;
import java.io.IOException;

import java.util.List;

/**
 * This interface for transformation item of the xml file in {@link UnifiedFormatDTO}.
 */
public interface TransformationService {

    /**
     * Get items of xml file, transformation them in unified format and save in the database.
     * @param file uploaded zip file.
     * @throws IOException if an I/O error occurs.
     */
    void transformation(File file) throws IOException;

    /**
     * Transformation items in unified format.
     * @param list items (see {@link ItemEntity}).
     * @return list {@link UnifiedFormatDTO} items.
     */
    List<UnifiedFormatDTO> toUnifiedFormat(List<ItemEntity> list);
}

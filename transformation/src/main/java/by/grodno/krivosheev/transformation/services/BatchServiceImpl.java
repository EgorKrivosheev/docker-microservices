package by.grodno.krivosheev.transformation.services;

import by.grodno.krivosheev.transformation.entities.BatchEntity;

import by.grodno.krivosheev.transformation.repositories.BatchRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchServiceImpl implements BatchService {
    private final BatchRepository batchRepository;
    private final FileService fileService;

    @Override
    public File save(MultipartFile file) throws IOException {
        BatchEntity batchEntity = batchRepository.save(new BatchEntity());
        String name = batchEntity.getId() + ".zip";
        File uploadFile = fileService.uploadAndSendKafka(file, name);
        batchEntity.setSize(Files.size(uploadFile.toPath()));
        batchEntity.setUploadDate(Files.getLastModifiedTime(uploadFile.toPath()).toString());
        batchRepository.save(batchEntity);
        return uploadFile;
    }

    @Override
    public List<BatchEntity> getAll(Pageable pageable) {
        Page<BatchEntity> page = batchRepository.findAll(pageable);
        return page.toList();
    }

    @Override
    public BatchEntity getBatch(Long id) {
        return batchRepository.getById(id);
    }
}

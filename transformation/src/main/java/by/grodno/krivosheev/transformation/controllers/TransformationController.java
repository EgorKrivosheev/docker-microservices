package by.grodno.krivosheev.transformation.controllers;

import by.grodno.krivosheev.transformation.dto.BatchInfoDTO;
import by.grodno.krivosheev.transformation.dto.UnifiedFormatDTO;
import by.grodno.krivosheev.transformation.elastic.services.ItemDocumentService;
import by.grodno.krivosheev.transformation.entities.BatchEntity;
import by.grodno.krivosheev.transformation.entities.ItemEntity;
import by.grodno.krivosheev.transformation.mappers.MapperAbstractFactory;
import by.grodno.krivosheev.transformation.response.*;
import by.grodno.krivosheev.transformation.services.BatchService;
import by.grodno.krivosheev.transformation.services.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "Transformation", description = "The rest service which consumes the data and just transforms it to a unified format")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/batches")
public class TransformationController {
    private final BatchService batchService;
    private final ItemService itemService;
    private final ItemDocumentService itemDocumentService;
    private final MapperAbstractFactory<BatchInfoDTO, BatchEntity> batchMapperFactory;
    private final MapperAbstractFactory<UnifiedFormatDTO, ItemEntity> itemMapperFactory;

    @Operation(summary = "Ingest endpoint")
    @PostMapping(produces = "application/octet-stream")
    public ResponseEntity<AbstractResponse> uploadBatch(MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new IllegalArgumentException("Don't upload file because he is empty!");
        }

        var uploadFile = batchService.save(file);
        var id = Long.parseLong(uploadFile.getName().substring(0, uploadFile.getName().length() - 4));
        return responseWithHeader(new BatchResponse(HttpStatus.OK, batchMapperFactory.entityToDTO(batchService.getBatch(id))), new HttpHeaders());
    }

    @Operation(summary = "List of all uploads (ordered by date)")
    @GetMapping(produces = "application/json")
    public ResponseEntity<AbstractResponse> getBatches(@ParameterObject @PageableDefault(size = 25, sort = "uploadDate",
            direction = Sort.Direction.DESC) Pageable pageable) {
        return responseList(new ListBatchResponse(HttpStatus.OK, batchMapperFactory.listEntityToListDTO(batchService.getAll(pageable))));
    }

    @Operation(summary = "Batch content with pagination (in the order from original file)")
    @GetMapping(value = "/{batchId}/items", produces = "application/json")
    public ResponseEntity<AbstractResponse> getItemsBatch(@PathVariable Long batchId,
                                                          @ParameterObject @PageableDefault(size = 25) Pageable pageable) {
        return responseList(new ListUnifiedResponse(HttpStatus.OK, itemMapperFactory.listEntityToListDTO(itemService.findAllByIdBatch(batchId, pageable))));
    }

    @Operation(summary = "Batch content with pagination and elasticsearch")
    @GetMapping(value = "/items", produces = "application/json")
    public ResponseEntity<AbstractResponse> getItems(String subject,
                                                     @ParameterObject @PageableDefault(size = 25) Pageable pageable) {
        return responseList(new ListDocumentResponse(HttpStatus.OK, itemDocumentService.getItemsBySubjectContaining(subject, pageable)));
    }

    private <T> ResponseEntity<T> responseList(T response) {
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private ResponseEntity<AbstractResponse> responseWithHeader(AbstractResponse response, HttpHeaders headers) {
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
